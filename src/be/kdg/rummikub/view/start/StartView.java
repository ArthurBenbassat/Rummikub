package be.kdg.rummikub.view.start;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartView extends BorderPane {
    // private Node attributen (controls)
    private Button btnStart;
    private Button btnSpelregels;
    ImageView imgAfbeelding;
    private Label lblTitelSpel;
    private VBox vbox;
    private MenuItem exitMI;
    private MenuItem infoMI;
    private MenuItem overOnsMI;
    private MenuItem statistiekenMI;

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

        this.exitMI = new MenuItem("Exit");
        this.infoMI = new MenuItem("Info");
        this.overOnsMI = new MenuItem("Over ons");
        this.statistiekenMI = new MenuItem("Statistieken");
    }

    private void layoutNodes() {
        // Layout van de Nodes

        //settings lblTitelSpel
        this.setTop(this.lblTitelSpel);
        BorderPane.setMargin(lblTitelSpel, new Insets(10, 10, 10, 10));
        BorderPane.setAlignment(lblTitelSpel, Pos.CENTER);
        this.lblTitelSpel.setFont(new Font("Arial", 50));
        lblTitelSpel.setTextFill(Color.BLUE);

        //settings btnStart
        btnStart.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");

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
        btnSpelregels.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
        BorderPane.setAlignment(btnSpelregels, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(btnSpelregels, new Insets(10, 10, 10, 10));

        Menu menuFile = new Menu("File", null, statistiekenMI, new SeparatorMenuItem(), new javafx.scene.control.SeparatorMenuItem(), exitMI);
        Menu menuHelp = new Menu("Help", null, overOnsMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile, menuHelp);
        setTop(menuBar);
        this.setBackground(new Background(new BackgroundFill(Color.web("#086ea8", 1), new CornerRadii(0), Insets.EMPTY)));
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

    public MenuItem getExitMI() { return exitMI; }

    public MenuItem getInfoMI() { return infoMI; }

    public MenuItem getOverOnsMI() { return overOnsMI; }

    public MenuItem getStatistiekenMI() { return statistiekenMI; }
}
