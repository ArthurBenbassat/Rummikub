package be.kdg.rummikub.view.about;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class AboutView extends BorderPane {

    // private Node attributen (controls)
    private Label lblOverOns;

    public AboutView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.lblOverOns = new Label("Wij zijn twee eerstejaarsstudenten van Toegepaste Informatica aan de Karel de Grote Hogeschool Antwerpen.\nDit spel is een schoolproject voor ons examen van JavaFX.");
    }

    private void layoutNodes(){
        this.setCenter(lblOverOns);
        lblOverOns.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-radius: 2px");
        lblOverOns.setPadding(new Insets(20));
        this.setBackground(new Background(new BackgroundFill(Color.web("#086ea8", 1), new CornerRadii(0), Insets.EMPTY)));
    }


}
