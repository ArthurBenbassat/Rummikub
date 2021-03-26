package be.kdg.rummikub.view.statistieken;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StatsView extends BorderPane {

    // private Node attributen (controls)
    private Label lblStatistieken;
    private Label lblTitel;

    public StatsView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.lblStatistieken = new Label();
        this.lblTitel = new Label("De 10 beste resultaten:");
    }

    private void layoutNodes() {
        BorderPane.setAlignment(lblTitel, Pos.CENTER);
        this.setTop(lblTitel);
        lblTitel.setPadding(new Insets(10));
        lblTitel.setFont(new Font(20));
        lblTitel.setStyle("-fx-font-weight: bold");

        this.setCenter(lblStatistieken);

        lblStatistieken.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-radius: 2px");
        lblStatistieken.setPadding(new Insets(20));
        this.setBackground(new Background(new BackgroundFill(Color.web("#086ea8", 1), new CornerRadii(0), Insets.EMPTY)));

    }

    public Label getLblStatistieken() {
        return lblStatistieken;
    }
}
