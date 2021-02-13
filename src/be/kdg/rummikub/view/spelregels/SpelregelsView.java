package be.kdg.rummikub.view.spelregels;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class SpelregelsView extends BorderPane {
    private Label lblRegels;
    private Button btnTerug;

    public SpelregelsView() {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() {
        // Initialisatie van de Nodes
        this.btnTerug = new Button("Ga terug naar de home");
        this.lblRegels = new Label();
    }
    private void layoutNodes() {

        this.setBottom(btnTerug);

        this.setCenter(lblRegels);
        //lblRegels.setText(new SpelregelsPresenter());
    }

    // implementatie van de nodige
    // package-private Getters
    public Button getBtnTerug() {
        return this.btnTerug;
    }

    public void changeRegels(String regels) {
        lblRegels.setText(regels);
    }

}
