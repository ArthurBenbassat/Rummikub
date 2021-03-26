package be.kdg.rummikub.view.spel;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
    private Label lblEersteZet;

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
        this.lblEersteZet = new Label("Goedgekeurd");
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
        gdpEigenStenen.setStyle("-fx-background-color: #654321 ;");
        gdpEigenStenen.setAlignment(Pos.CENTER);
        setBottom(gdpEigenStenen);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        veldRijen = (int) Math.floor((screenBounds.getHeight() - 200) / 75 );
        veldKolommen = (int) Math.floor((screenBounds.getWidth() - 150) / 50);

        for (int i = 0; i < veldRijen; i++) {
            for (int j = 0; j < veldKolommen; j++) {
                AfbeeldingSteen afbeeldingSteen = new AfbeeldingSteen("/fotos/wit.png", j, i);
                afbeeldingSteen.setFitHeight(75);
                afbeeldingSteen.setFitWidth(50);
                afbeeldingSteen.setPickOnBounds(true);
                gdpSpelbord.add(afbeeldingSteen, j, i);
            }
        }
        gdpSpelbord.setStyle("-fx-background-color: #d69a57");



        this.setCenter(gdpSpelbord);

        Menu menuFile = new Menu("Bestand", null, statistiekenMI, exitMI);
        Menu menuHelp = new Menu("Help", null, overOnsMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile, menuHelp);
        setTop(menuBar);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        btnVraagExtraSteen.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white; -fx-padding: 10;");
        btnEindeBeurt.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30;-fx-background-insets: 0;-fx-text-fill: white; -fx-padding: 10;");
        lblEersteZet.setFont(new Font(17));
        lblEersteZet.setStyle("-fx-background-color: #39d624; -fx-background-radius: 30;-fx-background-insets: 0;-fx-text-fill: white; -fx-padding: 10;");

        lblEersteZet.setVisible(false);

        vBox.getChildren().addAll(btnVraagExtraSteen,btnEindeBeurt, lblEersteZet);
        VBox.setMargin(btnEindeBeurt, new Insets(10));
        VBox.setMargin(btnVraagExtraSteen, new Insets(10));
        VBox.setMargin(lblEersteZet, new Insets(10));

        vBox.setStyle("-fx-background-color: #d69a57");
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

    public int getVeldRijen() { return veldRijen; }

    public int getEigenKolommen() {
        return eigenKolommen;
    }

    public Button getBtnEindeBeurt() {
        return btnEindeBeurt;
    }

    public Label getLblEersteZet(){return lblEersteZet;}
}
