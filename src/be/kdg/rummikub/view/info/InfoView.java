package be.kdg.rummikub.view.info;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class InfoView extends BorderPane {
    private Label lblInfo;

    public InfoView() {
        initialiseNodes();
        layoutNodes();
    }



    private void initialiseNodes() {
        lblInfo = new Label("Hallo!\nWij zijn twee studenten van KdG Hogeschool Antwerpen.\nDit is ons project voor het vak Programeren 1.\nDit is versie 1 van ons spel.\nAlle voor rechten voor behouden.\n©Wouter Selis & Arthur Benbassat");
    }

    private void layoutNodes() {
        this.setCenter(lblInfo);
        lblInfo.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-radius: 2px");
        lblInfo.setPadding(new Insets(20));
        this.setBackground(new Background(new BackgroundFill(Color.web("#086ea8", 1), new CornerRadii(0), Insets.EMPTY)));
    }
}
