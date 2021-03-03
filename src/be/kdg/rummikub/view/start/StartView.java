package be.kdg.rummikub.view.start;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartView extends BorderPane {
    // private Node attributen (controls)
    private Button btnStart;
    private Button btnSpelregels;
    ImageView imgAfbeelding;
    private Label lblTitelSpel;
    private VBox vbox;

    public StartView() {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() {
        // Initialisatie van de Nodes
        this.btnStart = new Button("Start spel");
        this.btnSpelregels = new Button("Spelregels");
        this.imgAfbeelding = new ImageView("/fotos/achtergrond-foto.jpg");
        this.vbox = new VBox();
        this.lblTitelSpel = new Label("Rummikub");
    }
    private void layoutNodes() {
        // Layout van de Nodes
        
        //settings lblTitelSpel
        this.setTop(this.lblTitelSpel);
        BorderPane.setMargin(lblTitelSpel, new Insets(10,10,10,10));
        BorderPane.setAlignment(lblTitelSpel,Pos.CENTER);
        this.lblTitelSpel.setFont(new Font("Arial",50));
        lblTitelSpel.setTextFill(Color.BLUE);

        //settings btnStart
        btnStart.setTextFill(Color.RED);

        // settings image
        this.imgAfbeelding.setFitWidth(700);
        this.imgAfbeelding.setFitHeight(350);
        VBox.setMargin(imgAfbeelding, new Insets(20));

        // settings vbox
        vbox.getChildren().addAll(imgAfbeelding, btnStart);
        this.setCenter(vbox);
        vbox.setAlignment(Pos.CENTER);

        //setting btnSpelregels
        this.setBottom(btnSpelregels);
        BorderPane.setAlignment(btnSpelregels, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(btnSpelregels,new Insets(10,10,10,10));

    }
    public ImageView getAfbeelding() {
        return imgAfbeelding;
    }
    // implementatie van de nodige
    // package-private Getters
    public Button getBtnStart() {
        return this.btnStart;
    }

    public Button getBtnSpelregels() { return this.btnSpelregels; }

    public Label getLblTitelSpel() { return lblTitelSpel; }
}
