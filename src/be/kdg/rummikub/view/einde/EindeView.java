package be.kdg.rummikub.view.einde;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class EindeView extends BorderPane {
    // private Node attributen (controls)
    private MenuItem exitMI;
    private MenuItem infoMI;
    private MenuItem overOnsMI;
    private MenuItem statistiekenMI;
    private Label lblWinner;
    private Label lblVerliezer;
    ImageView imgEindscherm;
    private Button btnOpnieuw;
    private Button btnStoppen;

    public EindeView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Exit");
        this.infoMI = new MenuItem("Info");
        this.overOnsMI = new MenuItem("Over ons");
        this.statistiekenMI = new MenuItem("Statistieken");

        this.lblWinner = new Label("Winnaar!");
        this.lblVerliezer = new Label("Verliezer");
        this.imgEindscherm = new ImageView("/fotos/img-winner-loser.png");
        this.btnOpnieuw = new Button("Opnieuw spelen");
        this.btnStoppen = new Button("Stop spel");
    }

    private void layoutNodes() {

        Menu menuFile = new Menu("Bestand", null, statistiekenMI, exitMI);
        Menu menuHelp = new Menu("Help", null, overOnsMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile, menuHelp);
        setTop(menuBar);

        this.imgEindscherm.setFitWidth(612);
        this.imgEindscherm.setFitHeight(502);
        VBox.setMargin(imgEindscherm, new Insets(20));

        BorderPane.setMargin(lblWinner, new Insets(10, 10, 10, 10));
        this.lblWinner.setFont(new Font("Arial", 16));
        lblWinner.setTextFill(Color.WHITE);

        BorderPane.setMargin(lblVerliezer, new Insets(10, 10, 10, 10));
        this.lblVerliezer.setFont(new Font("Arial", 16));
        lblVerliezer.setTextFill(Color.WHITE);

        BorderPane.setMargin(btnOpnieuw, new Insets(10, 10, 10, 10));
        this.btnOpnieuw.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white; -fx-padding: 10;");

        BorderPane.setMargin(btnStoppen, new Insets(10, 10, 10, 10));
        this.btnStoppen.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white; -fx-padding: 10;");


        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        this.setCenter(hBox);
        hBox.getChildren().addAll(lblWinner, imgEindscherm, lblVerliezer);
        HBox.setMargin(lblWinner, new Insets(10));
        HBox.setMargin(lblVerliezer, new Insets(10));
        HBox.setMargin(imgEindscherm, new Insets(10));

        this.lblWinner.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-insets: 0; -fx-text-fill: white; -fx-padding:  20 60;");
        this.lblVerliezer.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-insets: 0; -fx-text-fill: white; -fx-padding: 20 60;");

        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.BOTTOM_CENTER);
        this.setBottom(hBox2);
        hBox2.getChildren().addAll(btnOpnieuw, btnStoppen);
        HBox.setMargin(btnOpnieuw, new Insets(10));
        HBox.setMargin(btnStoppen, new Insets(10));


        this.setBackground(new Background(new BackgroundFill(Color.web("#086ea8", 1), new CornerRadii(0), Insets.EMPTY)));


    }

    public MenuItem getExitMI() {
        return exitMI;
    }

    public MenuItem getInfoMI() {
        return infoMI;
    }

    public MenuItem getOverOnsMI() {
        return overOnsMI;
    }

    public MenuItem getStatistiekenMI() {
        return statistiekenMI;
    }

    public Button getBtnOpnieuw() {
        return btnOpnieuw;
    }

    public Button getBtnStoppen() {
        return btnStoppen;
    }

    public Label getLblWinner() {
        return lblWinner;
    }

    public Label getLblVerliezer() {
        return lblVerliezer;
    }
}

