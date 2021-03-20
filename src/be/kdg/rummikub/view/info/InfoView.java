package be.kdg.rummikub.view.info;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class InfoView extends BorderPane {
    private Label lblInfo;

    public InfoView() {
        initialiseNodes();
        layoutNodes();
    }



    private void initialiseNodes() {
        lblInfo = new Label("Hallo!\nWij zijn twee studenten van KdG Hogeschool Antwerpen.\nDit is ons project voor het vak Programeren 1.\nAlle voor rechten voor behouden.\n©Wouter Selis & Arthur benbassat");
    }

    private void layoutNodes() {
        this.setCenter(lblInfo);
        lblInfo.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-radius: 2px");
        lblInfo.setPadding(new Insets(20));
    }
}
