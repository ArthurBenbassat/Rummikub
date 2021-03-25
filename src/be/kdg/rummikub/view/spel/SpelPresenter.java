package be.kdg.rummikub.view.spel;

import be.kdg.rummikub.model.Spel;
import be.kdg.rummikub.model.Spelregels;
import be.kdg.rummikub.model.deelnemer.Speler;
import be.kdg.rummikub.model.steen.Steen;
import be.kdg.rummikub.view.about.AboutPresenter;
import be.kdg.rummikub.view.about.AboutView;
import be.kdg.rummikub.view.info.InfoPresenter;
import be.kdg.rummikub.view.info.InfoView;
import be.kdg.rummikub.view.statistieken.StatsPresenter;
import be.kdg.rummikub.view.statistieken.StatsView;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.util.function.Consumer;

public class SpelPresenter {
    private final Spel model;
    private final SpelView view;
    private AfbeeldingSteen spelerAfbeelding;
    private AfbeeldingSteen veldAfbeelding;
    private Steen verplaatsing;

    public SpelPresenter(Spel model, SpelView view) {
        this.model = model;
        this.view = view;

        model.getSpelbord().updateSpeelveld(view.getVeldKolommen(), view.getVeldRijen());
        try {
            model.zetAllesInJson();
        } catch (IOException e) {
            this.setAlert("Fout met json bestand");
        }
        this.updateView();
        this.addEventHandlers();
    }

    private void addEventHandlers() {

        /**
         * Actie voor klikken op Extra steen button
         * */
        view.getBtnVraagExtraSteen().setOnMouseClicked(mouseEvent -> {
            try {
                boolean anders = false;
                Steen[][] vorigSpeelveld = Spel.getVorigSpeelveld();
                for (int i = 0; i < vorigSpeelveld.length; i++) {
                    for (int j = 0; j < vorigSpeelveld[i].length; j++) {
                        if (vorigSpeelveld[i][j] != model.getSpelbord().getSpeelVeld()[i][j]) {
                            anders = true;
                        }
                    }
                }

                if (anders) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Je hebt nog steen(en) op het veld staan.\nWil alles terug zetten?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == ButtonType.YES) {
                            try {
                                model.zetAllesTerug();
                                this.updateGridSpelBord();
                                this.updateView();

                                extraSteen();
                            } catch (IOException e) {
                                this.setAlert("Fout bij het restoren");
                            }
                        }
                    });
                } else {
                    extraSteen();
                }

            } catch (IOException e) {
                this.setAlert("Fout bij het restoren");
            }
        });


        /**
         * Acties voor verplaatsen van steenjes op eigen bordje
         * */
        view.getGdpEigenStenen().setOnMouseClicked(mouseEvent -> {
            if (verplaatsing == null) {
                spelerAfbeelding = null;
                if (mouseEvent.getPickResult().getIntersectedNode() instanceof AfbeeldingSteen) {
                    spelerAfbeelding = (AfbeeldingSteen) mouseEvent.getPickResult().getIntersectedNode();
                    if (!(spelerAfbeelding.getUrl().contains("B") || spelerAfbeelding.getUrl().contains("G") || spelerAfbeelding.getUrl().contains("R") || spelerAfbeelding.getUrl().contains("Z"))) {
                        spelerAfbeelding = null;
                    } else {
                        view.setCursor(new ImageCursor(new Image("/fotos/stenen/" + spelerAfbeelding.getUrl() + ".png")));
                        String id = spelerAfbeelding.getId();
                        verplaatsing = model.getSpelers()[0].getStenen().get(Integer.parseInt(id));
                        model.getSpelers()[0].getStenen().remove(Integer.parseInt(id));
                        updateView();
                    }
                }
            } else {
                if (mouseEvent.getPickResult().getIntersectedNode() != null) {
                    try {
                        model.getSpelers()[0].addSteen(verplaatsing);
                        verplaatsing = null;

                        updateView();

                        view.setCursor(Cursor.DEFAULT);
                        spelerAfbeelding = null;
                    } catch (RuntimeException e) {
                        this.setAlert(e.getMessage());
                    }
                }
            }

        });


        /**
         *Acties voor steentjes te verplaatsen op het spelbord
         **/
        view.getGdpSpelbord().setOnMouseClicked(mouseEvent -> {

            if (verplaatsing != null) {
                if (mouseEvent.getPickResult().getIntersectedNode() != null) {
                    veldAfbeelding = (AfbeeldingSteen) mouseEvent.getPickResult().getIntersectedNode();
                    try {
                        model.getSpelbord().plaatsSteen(veldAfbeelding.getXGrid(), veldAfbeelding.getYGrid(), verplaatsing);
                        verplaatsing = null;
                        view.getGdpSpelbord().add(updateSpelBord(veldAfbeelding), veldAfbeelding.getXGrid(), veldAfbeelding.getYGrid());

                        view.setCursor(Cursor.DEFAULT);
                        spelerAfbeelding = null;
                    } catch (RuntimeException e) {
                        this.setAlert(e.getMessage());
                    }
                }
            } else {
                if (veldAfbeelding != null) {
                    if (mouseEvent.getPickResult().getIntersectedNode() != null) {
                        veldAfbeelding = (AfbeeldingSteen) mouseEvent.getPickResult().getIntersectedNode();
                        if (veldAfbeelding.getUrl() != null && !veldAfbeelding.getUrl().contains("wi")) {

                            model.getSpelbord().verwijderSteen(veldAfbeelding.getXGrid(), veldAfbeelding.getYGrid());
                            this.updateGridSpelBord();

                            view.setCursor(new ImageCursor(new Image("/fotos/stenen/" + veldAfbeelding.getUrl() + ".png")));

                            verplaatsing = new Steen(veldAfbeelding.getUrl());

                            spelerAfbeelding = veldAfbeelding;
                        }
                    }
                } else {
                    setAlert("Dit is geen steen");
                }

            }
        });


        /**
         * Acties bij drukken op button eindebeurt
         **/
        view.getBtnEindeBeurt().setOnMouseClicked(mouseEvent -> {
            if (model.getSpelbord().checkSpeelveld()) {

                try {
                    int aantalPunten = 0;
                    if (!model.getSpelers()[0].isEersteZet()) {
                        Steen[][] vorigSpeelveld = Spel.getVorigSpeelveld();
                        for (int i = 0; i < vorigSpeelveld.length; i++) {
                            for (int j = 0; j < vorigSpeelveld[i].length; j++) {
                                if (vorigSpeelveld[i][j] != model.getSpelbord().getSpeelVeld()[i][j]) {
                                    aantalPunten += model.getSpelbord().getSpeelVeld()[i][j].getWaarde();
                                }
                            }
                        }

                        if (aantalPunten < Spelregels.getMinimunAantalPuntenEersteZet()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Voor de eerste zet moet je " + Spelregels.getMinimunAantalPuntenEersteZet() + " halen.\nWil alles terug zetten?", ButtonType.YES, ButtonType.NO);
                            alert.showAndWait().ifPresent(buttonType -> {
                                if (buttonType == ButtonType.YES) {
                                    try {
                                        model.zetAllesTerug();
                                        this.updateGridSpelBord();
                                        this.updateView();
                                    } catch (IOException e) {
                                        this.setAlert("Fout bij het restoren");
                                    }
                                }
                            });
                        } else {
                            model.getSpelers()[0].setEersteZet(true);
                            model.volgendeSpeler();
                            model.getSpelers()[0].verhoogZet();
                            model.zetAllesInJson();

                            view.getLblEersteZet().setVisible(true);
                            PauseTransition visiblePause = new PauseTransition( Duration.seconds(3)                            );
                            visiblePause.setOnFinished(
                                    event -> view.getLblEersteZet().setVisible(false)
                            );
                            visiblePause.play();

                        }

                    } else {
                        model.volgendeSpeler();
                        model.getSpelers()[0].verhoogZet();
                        model.zetAllesInJson();


                        view.getLblEersteZet().setVisible(true);
                        PauseTransition visiblePause = new PauseTransition(
                                Duration.seconds(3)
                        );
                        visiblePause.setOnFinished(
                                event -> view.getLblEersteZet().setVisible(false)
                        );
                        visiblePause.play();
                    }

                } catch (IOException e) {
                    this.setAlert("Fout bij het restoren");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Er zit een fout in.\nWil alles terug zetten?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == ButtonType.YES) {
                        try {
                            model.zetAllesTerug();
                            model.zetAllesTerug();
                            this.updateGridSpelBord();
                            this.updateView();
                        } catch (IOException e) {
                            this.setAlert("Fout bij het restoren");
                        }
                    }
                });
            }
        });


        /**
         *Actie bij klikken op menu item Info
         * */
        view.getInfoMI().setOnAction(actionEvent -> {
            InfoView infoView = new InfoView();
            new InfoPresenter(model, infoView);


            Stage infoStage = new Stage();
            infoStage.initOwner(view.getScene().getWindow());
            infoStage.initModality(Modality.APPLICATION_MODAL);

            infoStage.setScene(new Scene(infoView));

            infoStage.setX(view.getScene().getWindow().getX() + 100);
            infoStage.setY(view.getScene().getWindow().getY() + 100);

            infoStage.getScene().getWindow().setHeight(200);
            infoStage.getScene().getWindow().setWidth(450);
            infoStage.setTitle("Info - Rummikub");
            infoStage.getIcons().add(new Image("/fotos/logo.png"));
            infoStage.showAndWait();
        });


        /**
         *Actie bij klikken op menu item Over ons
         * */
        view.getOverOnsMI().setOnAction(actionEvent -> {
            AboutView aboutView = new AboutView();
            new AboutPresenter(model, aboutView);

            Stage aboutStage = new Stage();
            aboutStage.initOwner(view.getScene().getWindow());
            aboutStage.initModality(Modality.APPLICATION_MODAL);

            aboutStage.setScene(new Scene(aboutView));

            aboutStage.setX(view.getScene().getWindow().getX() + 100);
            aboutStage.setY(view.getScene().getWindow().getY() + 100);

            aboutStage.getScene().getWindow().setHeight(200);
            aboutStage.getScene().getWindow().setWidth(650);
            aboutStage.setTitle("About - Rummikub");
            aboutStage.getIcons().add(new Image("/fotos/logo.png"));
            aboutStage.showAndWait();
        });


        /**
         *Actie bij klikken op menu item Exit
         * */
        view.getExitMI().setOnAction(actionEvent -> {
            Platform.exit();
            System.exit(0);
        });


        /**
         *Actie bij klikken op menu item Statistieken
         * */
        view.getStatistiekenMI().setOnAction(actionEvent -> {
            StatsView statsView = new StatsView();
            new StatsPresenter(model, statsView);

            Stage statsStage = new Stage();
            statsStage.initOwner(view.getScene().getWindow());
            statsStage.initModality(Modality.APPLICATION_MODAL);

            statsStage.setScene(new Scene(statsView));

            statsStage.setX(view.getScene().getWindow().getX() + 100);
            statsStage.setY(view.getScene().getWindow().getY() + 100);

            statsStage.getScene().getWindow().setHeight(600);
            statsStage.getScene().getWindow().setWidth(600);
            statsStage.setTitle("Statistieken - Rummikub");
            statsStage.getIcons().add(new Image("/fotos/logo.png"));
            statsStage.showAndWait();
        });

    }

    private void extraSteen() throws IOException {
        Steen extraSteen = model.getPot().getRandomSteen();
        model.getSpelers()[0].addSteen(extraSteen);
        model.getPot().getStenen().remove(extraSteen);
        model.getSpelers()[0].verhoogZet();
        model.zetAllesInJson();
        this.updateView();
        model.volgendeSpeler();
    }

    private void setAlert(String bericht) {
        Alert alert = new Alert(Alert.AlertType.ERROR, bericht);
        alert.showAndWait();
    }

    private AfbeeldingSteen updateSpelBord(AfbeeldingSteen steenAfbeelding) {
        if (steenAfbeelding != null) {
            AfbeeldingSteen steen = new AfbeeldingSteen("/fotos/stenen/" + spelerAfbeelding.getUrl() + ".png", steenAfbeelding.getXGrid(), steenAfbeelding.getYGrid());
            steen.setFitHeight(75);
            steen.setFitWidth(50);
            return steen;
        } else {
            return null;
        }
    }

    private void updateGridSpelBord() {
        for (int i = 0; i < model.getSpelbord().getSpeelVeld().length; i++) {
            for (int j = 0; j < model.getSpelbord().getSpeelVeld()[i].length; j++) {
                if (model.getSpelbord().getSpeelVeld()[i][j] == null) {
                    AfbeeldingSteen afbeeldingSteen = new AfbeeldingSteen("/fotos/wit.png", i, j);
                    afbeeldingSteen.setFitHeight(75);
                    afbeeldingSteen.setFitWidth(50);
                    view.getGdpSpelbord().add(afbeeldingSteen, i, j);
                } else {
                    AfbeeldingSteen afbeeldingSteen = new AfbeeldingSteen(model.getSpelbord().getSpeelVeld()[i][j].getPad(), i, j);
                    afbeeldingSteen.setFitHeight(75);
                    afbeeldingSteen.setFitWidth(50);
                    view.getGdpSpelbord().add(afbeeldingSteen, i, j);
                }

            }

        }
    }

    private void updateSteenHouder() {
        int x = 0;
        int y = 0;
        for (int i = 0; i < model.getSpelers()[0].getStenen().size(); i++) {
            String url = model.getSpelers()[0].getStenen().get(i).getKleur().name().substring(0, 1) + model.getSpelers()[0].getStenen().get(i).getWaarde();
            AfbeeldingSteen afbeeldingSteen = new AfbeeldingSteen("/fotos/stenen/" + url + ".png", x, y);
            afbeeldingSteen.setId(Integer.toString(i));
            afbeeldingSteen.setFitWidth(50);
            afbeeldingSteen.setFitHeight(75);
            view.getGdpEigenStenen().add(afbeeldingSteen, x, y);
            x++;
            if (x > view.getEigenKolommen()) {
                x = 0;
                y++;
            }
        }
    }

    private void updateView() {

        view.getGdpEigenStenen().getChildren().clear();
        updateSteenHouder();
    }
}

