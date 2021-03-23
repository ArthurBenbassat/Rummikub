package be.kdg.rummikub.view.spelregels;

import be.kdg.rummikub.model.Spel;

import be.kdg.rummikub.model.Spelregels;
import be.kdg.rummikub.view.start.StartPresenter;
import be.kdg.rummikub.view.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.LightBase;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class SpelregelsPresenter {
    private Spel model;
    private SpelregelsView view;

    public SpelregelsPresenter(Spel model, SpelregelsView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnTerug().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StartView startView = new StartView();
                new StartPresenter(model, startView);
                view.getScene().setRoot(startView);
            }
        });
    }
    private void updateView() {
        Label titelTijd = new Label("tijdslimietInSeconden:");
        titelTijd.setFont(new Font(20));
        view.getSpelregels().add(titelTijd, 0, 0);
        Label tijd = new Label(String.valueOf(Spelregels.getTijdslimietInSeconden()));
        tijd.setFont(new Font(20));
        view.getSpelregels().add(tijd, 1, 0);

        Label titelPunten = new Label("Aantal Punten minimaal voor de eerste zet:");
        titelPunten.setFont(new Font(20));
        view.getSpelregels().add(titelPunten, 0, 1);
        Label punten = new Label(String.valueOf(Spelregels.getMinimunAantalPuntenEersteZet()));
        punten.setFont(new Font(20));
        view.getSpelregels().add(punten, 1, 1);

        Label titelMin = new Label("Aantal steentje waarmee je moet starten:");
        titelMin.setFont(new Font(20));
        view.getSpelregels().add(titelMin, 0, 2);
        Label minimum = new Label(String.valueOf(Spelregels.getstartAantalSteentejes()));
        minimum.setFont(new Font(20));
        view.getSpelregels().add(minimum, 1, 2);

        for (Node lbl : view.getSpelregels().getChildren()) {
            GridPane.setMargin(lbl, new Insets(10));
        }

    }
}
