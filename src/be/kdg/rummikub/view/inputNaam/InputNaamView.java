package be.kdg.rummikub.view.inputNaam;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InputNaamView extends BorderPane {
    private TextField txfInputNaam;
    private Button btnDoorgaan;
    private Label lblTitel;
    private ImageView imgWieBenIk;
    private Label lblFout;


    public InputNaamView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.txfInputNaam = new TextField();
        this.btnDoorgaan = new Button("Doorgaan");
        this.lblTitel = new Label("Geef je naam:");
        this.imgWieBenIk = new ImageView("/fotos/wie-ben-ik.png");
        this.lblFout = new Label("Geef een naam in!");
    }

    private void layoutNodes() {
        HBox hBox = new HBox();
        hBox.getChildren().addAll(txfInputNaam, btnDoorgaan);
        hBox.setAlignment(Pos.CENTER);
        HBox.setMargin(txfInputNaam, new Insets(10));
        HBox.setMargin(btnDoorgaan, new Insets(10));

        this.lblFout.setVisible(false);
        this.lblFout.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        this.lblFout.setFont(new Font("Arial", 18));


        VBox vBox = new VBox();
        vBox.getChildren().addAll(lblTitel, hBox,lblFout, imgWieBenIk);
        vBox.setAlignment(Pos.CENTER);
        this.setCenter(vBox);
        this.lblTitel.setFont(new Font("Arial", 20));
        this.lblTitel.setUnderline(true);
        VBox.setMargin(lblTitel, new Insets(0,115,0 ,0));
        VBox.setMargin(lblFout, new Insets(0, 100,0,0));
        lblTitel.setStyle("-fx-font-weight: bold");
        btnDoorgaan.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
        this.setBackground(new Background(new BackgroundFill(Color.web("#086ea8", 1), new CornerRadii(0), Insets.EMPTY)));
    }

    public Button getBtnDoorgaan() {
        return btnDoorgaan;
    }

    public TextField getTxfInputNaam() {
        return txfInputNaam;
    }

    public Label getLblFout() {
        return lblFout;
    }
}
