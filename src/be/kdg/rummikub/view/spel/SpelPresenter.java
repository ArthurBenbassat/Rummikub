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

                    //spelerAfbeelding.setId("selectedStone");
                    updateView();
                }
            }
        });

        view.getGdpSpelbord().setOnMouseClicked(mouseEvent -> {
            veldAfbeelding = (AfbeeldingSteen) mouseEvent.getPickResult().getIntersectedNode();
            if  (verplaatsing != null){
                model.getSpelbord().plaatsSteen(veldAfbeelding.getXGrid(), veldAfbeelding.getYGrid(), verplaatsing);
                verplaatsing = null;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.showAndWait();
            }

            view.getGdpSpelbord().add(updateSpelBord(veldAfbeelding), veldAfbeelding.getXGrid(), veldAfbeelding.getYGrid());
            view.setCursor(Cursor.DEFAULT);
            spelerAfbeelding = null;
            veldAfbeelding = null;

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

