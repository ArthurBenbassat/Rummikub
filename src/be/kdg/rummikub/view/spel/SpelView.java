package be.kdg.rummikub.view.spel;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class SpelView extends BorderPane {
    private GridPane gdpEigenStenen;
    private GridPane gdpSpelbord;
    private MenuItem exitMI;
    private MenuItem infoMI;
    private MenuItem overOnsMI;
    private MenuItem statistiekenMI;
    private Button btnVraagExtraSteen;
    private Button btnEindeBeurt;
    private int veldRijen = 11;
    private int veldKolommen = 29;
    private final int eigenRijen = 2;
    private final int eigenKolommen = 24;

    public SpelView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        gdpEigenStenen = new GridPane();
        this.btnVraagExtraSteen = new Button("Extra steen");
        this.btnEindeBeurt = new Button("Einde beurt");
        gdpSpelbord = new GridPane();
        this.exitMI = new MenuItem("Exit");
        this.infoMI = new MenuItem("Info");
        this.overOnsMI = new MenuItem("Over ons");
        this.statistiekenMI = new MenuItem("Statistieken");
    }

    private void layoutNodes() {
        for (int i = 0; i < eigenRijen; i++) {
            for (int j = 0; j < eigenKolommen; j++) {
                AfbeeldingSteen afbeeldingSteen = new AfbeeldingSteen("/fotos/TransparantImage.png", j, i);
                afbeeldingSteen.setFitHeight(75);
                afbeeldingSteen.setFitWidth(50);
                afbeeldingSteen.setPickOnBounds(true);
                gdpEigenStenen.add(afbeeldingSteen,j,i);
                gdpEigenStenen.setGridLinesVisible(true);
            }
        }
        gdpEigenStenen.setAlignment(Pos.CENTER);
        setBottom(gdpEigenStenen);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        System.out.println(screenBounds.getWidth());

        veldRijen = (int) Math.floor((screenBounds.getHeight() - 200) / 75 );
        veldKolommen = (int) Math.floor((screenBounds.getWidth() - 150) / 50);

        for (int i = 0; i < veldRijen; i++) {
            for (int j = 0; j < veldKolommen; j++) {
                AfbeeldingSteen afbeeldingSteen = new AfbeeldingSteen("/fotos/TransparantImage.png", j, i);
                afbeeldingSteen.setFitHeight(75);
                afbeeldingSteen.setFitWidth(50);
                afbeeldingSteen.setPickOnBounds(true);
                gdpSpelbord.add(afbeeldingSteen, j, i);
                gdpSpelbord.setGridLinesVisible(true);
            }
        }
        this.setCenter(gdpSpelbord);

        Menu menuFile = new Menu("File", null, statistiekenMI, new SeparatorMenuItem(), new SeparatorMenuItem(), exitMI);
        Menu menuHelp = new Menu("Help", null, overOnsMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile, menuHelp);
        setTop(menuBar);
        btnVraagExtraSteen.setPadding(new Insets(10));
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(btnVraagExtraSteen,btnEindeBeurt);
        setRight(vBox);
    }

    public GridPane getGdpEigenStenen() {
        return gdpEigenStenen;
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

    public int getVeldKolommen() {
        return veldKolommen;
    }

    public int getEigenKolommen() {
        return eigenKolommen;
    }

    public Button getBtnEindeBeurt() {
        return btnEindeBeurt;
    }
}
