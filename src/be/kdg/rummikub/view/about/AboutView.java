package be.kdg.rummikub.view.about;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

public class AboutView extends BorderPane {

    // private Node attributen (controls)
    private MenuItem exitMI;
    private MenuItem infoMI;
    private MenuItem overOnsMI;
    private MenuItem statistiekenMI;

    public AboutView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Exit");
        this.infoMI = new MenuItem("Info");
        this.overOnsMI = new MenuItem("Over ons");
        this.statistiekenMI = new MenuItem("Statistieken");
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("File", null, statistiekenMI, new SeparatorMenuItem(), new javafx.scene.control.SeparatorMenuItem(), exitMI);
        Menu menuHelp = new Menu("Help", null, overOnsMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile, menuHelp);
        setTop(menuBar);
    }

    public MenuItem getExitMI() { return exitMI; }

    public MenuItem getInfoMI() { return infoMI; }

    public MenuItem getOverOnsMI() { return overOnsMI; }

    public MenuItem getStatistiekenMI() { return statistiekenMI; }
}
