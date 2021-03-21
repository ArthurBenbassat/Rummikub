package be.kdg.rummikub.view.spel;

import be.kdg.rummikub.model.Rij;
import be.kdg.rummikub.model.Spel;
import be.kdg.rummikub.model.steen.Kleur;
import be.kdg.rummikub.model.steen.Steen;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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

        this.updateView();
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnVraagExtraSteen().setOnMouseClicked(mouseEvent -> {
            model.volgendeSpeler();
            Steen extraSteen = model.getPot().getRandomSteen();
            model.getSpelers()[0].addSteen(extraSteen);
            model.getPot().getStenen().remove(extraSteen);
            this.updateView();
        });

        view.getGdpEigenStenen().setOnMouseClicked(mouseEvent -> {
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
                    spelerAfbeelding.setId("");
                    updateView();
                }
            }
        });

        view.getGdpSpelbord().setOnMouseClicked(mouseEvent -> {

            if  (verplaatsing != null){
                veldAfbeelding = (AfbeeldingSteen) mouseEvent.getPickResult().getIntersectedNode();

                try {
                    model.getSpelbord().plaatsSteen(veldAfbeelding.getXGrid(), veldAfbeelding.getYGrid(), verplaatsing);
                    verplaatsing = null;
                    veldAfbeelding.setId("1,1");

                    //this.updateGridSpelBord();
                    view.getGdpSpelbord().add(updateSpelBord(veldAfbeelding), veldAfbeelding.getXGrid(), veldAfbeelding.getYGrid());

                    view.setCursor(Cursor.DEFAULT);
                    spelerAfbeelding = null;
                    //veldAfbeelding = null;
               } catch (RuntimeException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.showAndWait();
                }

            } else {
                veldAfbeelding = (AfbeeldingSteen) mouseEvent.getPickResult().getIntersectedNode();
                if (veldAfbeelding != null) {
                    model.getSpelbord().verwijderSteen(veldAfbeelding.getXGrid(),veldAfbeelding.getYGrid());
                    this.updateGridSpelBord();
                    //view.getGdpSpelbord().add(updateTransparteSteen(veldAfbeelding), veldAfbeelding.getXGrid(),veldAfbeelding.getYGrid());

                    view.setCursor(new ImageCursor(new Image("/fotos/stenen/" + veldAfbeelding.getUrl() + ".png")));

                    verplaatsing = new Steen(veldAfbeelding.getUrl());

                    spelerAfbeelding = veldAfbeelding;

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.showAndWait();
                }

            }
        });

        view.getBtnEindeBeurt().setOnMouseClicked(mouseEvent -> {
            //TODO logica met alle checks
        });



    }

    //ToDo
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
        for (int i =0 ; i < model.getSpelers()[0].getStenen().size(); i++) {
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

