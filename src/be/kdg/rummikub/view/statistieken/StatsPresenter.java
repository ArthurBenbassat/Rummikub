package be.kdg.rummikub.view.statistieken;

import be.kdg.rummikub.model.Spel;
import be.kdg.rummikub.model.Statistieken;
import javafx.scene.control.Alert;

import java.io.IOException;

public class StatsPresenter {
    private Spel model;
    private StatsView view;

    public StatsPresenter(Spel model, StatsView view){
        this.model = model;
        this.view = view;
        this.updateView();
        this.addEventHandlers();
    }

    private void updateView() {
        try {
            StringBuilder statistiek = new StringBuilder("");
            for (int i = 0; i < Statistieken.getStatistieken().size(); i++) {
                statistiek.append(i + 1).append(". ").append(Statistieken.getStatistieken().get(i)).append("\n");
            }

            view.getLblStatistieken().setText(statistiek.toString());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Bestand kon niet worden ingelezen");

            alert.showAndWait();
        }

    }

    private void addEventHandlers() {

    }
}
