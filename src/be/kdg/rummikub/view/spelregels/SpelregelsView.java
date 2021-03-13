package be.kdg.rummikub.view.spelregels;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class SpelregelsView extends BorderPane {
    private Label lblRegels;
    private Button btnTerug;

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
        this.lblRegels = new Label();

        this.exitMI = new MenuItem("Exit");
        this.infoMI = new MenuItem("Info");
        this.overOnsMI = new MenuItem("Over ons");
        this.statistiekenMI = new MenuItem("Statistieken");
    }
    private void layoutNodes() {

        this.setBottom(btnTerug);

        this.setCenter(lblRegels);
        //lblRegels.setText(new SpelregelsPresenter());

        Menu menuFile = new Menu("File", null, statistiekenMI, new javafx.scene.control.SeparatorMenuItem(), new javafx.scene.control.SeparatorMenuItem(), exitMI);
        Menu menuHelp = new Menu("Help", null, overOnsMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile, menuHelp);
        setTop(menuBar);

    }

    // implementatie van de nodige
    // package-private Getters
    public Button getBtnTerug() {
        return this.btnTerug;
    }

    public void changeRegels(String regels) {
        lblRegels.setText(regels);
    }

    public MenuItem getExitMI() { return exitMI; }

    public MenuItem getInfoMI() { return infoMI; }

    public MenuItem getOverOnsMI() { return overOnsMI; }

    public MenuItem getStatistiekenMI() { return statistiekenMI; }

}
