package be.kdg.rummikub.view.spelregels;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SpelregelsView extends BorderPane {
    private Button btnTerug;
    private GridPane spelregels;

    private MenuItem exitMI;
    private MenuItem infoMI;
    private MenuItem overOnsMI;
    private MenuItem statistiekenMI;


    public SpelregelsView() {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() {
        // Initialisatie van de Nodes
        this.btnTerug = new Button("Ga terug naar de home");


        this.exitMI = new MenuItem("Exit");
        this.infoMI = new MenuItem("Info");
        this.overOnsMI = new MenuItem("Over ons");
        this.statistiekenMI = new MenuItem("Statistieken");

        this.spelregels = new GridPane();
    }
    private void layoutNodes() {
        BorderPane.setAlignment(btnTerug, Pos.CENTER);
        this.setBottom(btnTerug);

        this.setCenter(spelregels);

        Menu menuFile = new Menu("File", null, statistiekenMI, new javafx.scene.control.SeparatorMenuItem(), new javafx.scene.control.SeparatorMenuItem(), exitMI);
        Menu menuHelp = new Menu("Help", null, overOnsMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile, menuHelp);
        setTop(menuBar);

        this.setBackground(new Background(new BackgroundFill(Color.web("#086ea8", 1), new CornerRadii(0), Insets.EMPTY)));



    }

    // implementatie van de nodige
    // package-private Getters
    public Button getBtnTerug() {
        return this.btnTerug;
    }

    public GridPane getSpelregels() { return this.spelregels; }

    public MenuItem getExitMI() { return exitMI; }

    public MenuItem getInfoMI() { return infoMI; }

    public MenuItem getOverOnsMI() { return overOnsMI; }

    public MenuItem getStatistiekenMI() { return statistiekenMI; }

}
