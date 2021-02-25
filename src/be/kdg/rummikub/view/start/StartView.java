package be.kdg.rummikub.view.start;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class StartView extends BorderPane {
    // private Node attributen (controls)
    private Button btnStart;
    private Button btnSpelregels;
    ImageView afbeelding;

    public StartView() {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() {
        // Initialisatie van de Nodes
        this.btnStart = new Button("Start spel");
        this.btnSpelregels = new Button("Spelregels");
        this.afbeelding = new ImageView();
    }
    private void layoutNodes() {
        // Layout van de Nodes
        this.setTop(this.afbeelding);
        this.setCenter(btnStart);
        this.setBottom(btnSpelregels);
        BorderPane.setAlignment(btnSpelregels, Pos.CENTER);
        BorderPane.setMargin(btnSpelregels,new Insets(10,10,10,10));
        //btnStart heeft rode tekst
        btnStart.setTextFill(Color.RED);
    }
    public ImageView getAfbeelding() {
        return afbeelding;
    }
    // implementatie van de nodige
    // package-private Getters
    public Button getBtnStart() {
        return this.btnStart;
    }

    public Button getBtnSpelregels() {
        return this.btnSpelregels;
    }
}
