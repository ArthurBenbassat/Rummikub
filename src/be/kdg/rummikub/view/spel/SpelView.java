package be.kdg.rummikub.view.spel;


import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SpelView extends BorderPane {
    private HBox hbxEigenStenen;
    private GridPane gdpSpelbord;
    Text test;

    public SpelView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        hbxEigenStenen = new HBox();
        test = new Text("dit is een test");
    }

    private void layoutNodes() {
        this.setBottom(hbxEigenStenen);
        hbxEigenStenen.setAlignment(Pos.CENTER);
        this.setTop(test);
    }

    HBox getHbxEigenStenen() {
        return hbxEigenStenen;
    }

    GridPane getGdpSpelbord() {
        return gdpSpelbord;
    }
}
