package be.kdg.rummikub.view.spel;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SpelView extends BorderPane {
    private HBox hbxEigenStenen;
    private GridPane gdpSpelbord;
    private MenuItem exitMI;
    private MenuItem infoMI;
    private MenuItem overOnsMI;
    private MenuItem statistiekenMI;
    private Button btnVraagExtraSteen;

    public SpelView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        hbxEigenStenen = new HBox();
        this.btnVraagExtraSteen = new Button("Extra steen");
        this.exitMI = new MenuItem("Exit");
        this.infoMI = new MenuItem("Info");
        this.overOnsMI = new MenuItem("Over ons");
        this.statistiekenMI = new MenuItem("Statistieken");
    }

    private void layoutNodes() {
        this.setBottom(hbxEigenStenen);
        hbxEigenStenen.setAlignment(Pos.CENTER);
        btnVraagExtraSteen.setPadding(new Insets(20));

        hbxEigenStenen.getChildren().add(btnVraagExtraSteen);

        Menu menuFile = new Menu("File", null, statistiekenMI, new SeparatorMenuItem(), new javafx.scene.control.SeparatorMenuItem(), exitMI);
        Menu menuHelp = new Menu("Help", null, overOnsMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile, menuHelp);
        setTop(menuBar);
    }

    HBox getHbxEigenStenen() {
        return hbxEigenStenen;
    }

    GridPane getGdpSpelbord() {
        return gdpSpelbord;
    }

    public MenuItem getExitMI() { return exitMI; }

    public MenuItem getInfoMI() { return infoMI; }

    public MenuItem getOverOnsMI() { return overOnsMI; }

    public MenuItem getStatistiekenMI() { return statistiekenMI; }

    public Button getBtnVraagExtraSteen() {
        return btnVraagExtraSteen;
    }
}
