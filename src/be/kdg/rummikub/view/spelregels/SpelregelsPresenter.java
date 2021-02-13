package be.kdg.rummikub.view.spelregels;

import be.kdg.rummikub.model.Spel;

import be.kdg.rummikub.model.Spelregels;
import be.kdg.rummikub.view.start.StartPresenter;
import be.kdg.rummikub.view.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

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
        Spelregels spelregels = new Spelregels();
        view.changeRegels(spelregels.getSpelregels());
    }
}
