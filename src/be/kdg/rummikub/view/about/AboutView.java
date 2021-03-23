package be.kdg.rummikub.view.about;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class AboutView extends BorderPane {

    // private Node attributen (controls)
    private Label lblOverOns;

    public AboutView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.lblOverOns = new Label("Wij zijn twee eerstejaars studenten van Toegepaste Informatica aan de Karel de Grote Hogeschool Antwerpen.\nDit spel is een schoolproject voor ons examen van JavaFX.");
    }

    private void layoutNodes(){
        this.setCenter(lblOverOns);
        lblOverOns.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-radius: 2px");
        lblOverOns.setPadding(new Insets(20));
    }


}
