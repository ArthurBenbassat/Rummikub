package be.kdg.rummikub.view.einde;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class eindeView extends BorderPane {
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

    public eindeView() {
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
        this.imgEindscherm = new ImageView("/fotos/img-winner-loser.jpg");
        this.btnOpnieuw = new Button("Opnieuw spelen");
        this.btnStoppen = new Button("Stop spel");
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("File", null, statistiekenMI, new SeparatorMenuItem(), new javafx.scene.control.SeparatorMenuItem(), exitMI);
        Menu menuHelp = new Menu("Help", null, overOnsMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile, menuHelp);
        setTop(menuBar);


        this.setCenter(this.imgEindscherm);
        this.imgEindscherm.setFitWidth(612);
        this.imgEindscherm.setFitHeight(502);
        VBox.setMargin(imgEindscherm, new Insets(20));

        this.setCenter(this.lblWinner);
        BorderPane.setAlignment(btnOpnieuw, Pos.CENTER_LEFT);
        BorderPane.setMargin(lblWinner, new Insets(10, 10, 10, 10));
        this.lblWinner.setFont(new Font("Arial", 16));
        lblWinner.setTextFill(Color.BLUE);

        this.setCenter(this.lblVerliezer);
        BorderPane.setAlignment(btnOpnieuw, Pos.CENTER_RIGHT);
        BorderPane.setMargin(lblVerliezer, new Insets(10, 10, 10, 10));
        this.lblVerliezer.setFont(new Font("Arial", 16));
        lblVerliezer.setTextFill(Color.BLUE);

        this.setBottom(this.btnOpnieuw);
        BorderPane.setAlignment(btnOpnieuw, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(btnOpnieuw, new Insets(10, 10, 10, 10));

        this.setBottom(this.btnStoppen);
        BorderPane.setAlignment(btnStoppen, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(btnStoppen, new Insets(10, 10, 10, 10));




    }

    public MenuItem getExitMI() { return exitMI; }

    public MenuItem getInfoMI() { return infoMI; }

    public MenuItem getOverOnsMI() { return overOnsMI; }

    public MenuItem getStatistiekenMI() { return statistiekenMI; }
}
