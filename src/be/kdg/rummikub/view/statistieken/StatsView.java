package be.kdg.rummikub.view.statistieken;

import be.kdg.rummikub.model.deelnemer.Computer;
import be.kdg.rummikub.model.deelnemer.Deelnemer;
import be.kdg.rummikub.model.deelnemer.Speler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StatsView extends BorderPane {

    // private Node attributen (controls)
    private Label lblStatistieken;
    private Deelnemer deelnemer;


    public StatsView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.deelnemer = new Deelnemer();
    }

    private void initialiseNodes() {
        this.lblStatistieken = new Label("Speler1: "+ deelnemer.getAantalGezettenZetten());
    }

    private void layoutNodes() {

        this.setCenter(lblStatistieken);

        lblStatistieken.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-radius: 2px");
        lblStatistieken.setPadding(new Insets(20));
    }

}
