package be.kdg.rummikub.view.start;

import be.kdg.rummikub.model.Spel;
import be.kdg.rummikub.view.spel.SpelPresenter;
import be.kdg.rummikub.view.spel.SpelView;
import be.kdg.rummikub.view.spelregels.SpelregelsPresenter;
import be.kdg.rummikub.view.spelregels.SpelregelsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartPresenter {
    private Spel model;
    private  StartView view;

    public StartPresenter(Spel model, StartView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnSpelregels().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SpelregelsView spelregelsView = new SpelregelsView();
                new SpelregelsPresenter(model, spelregelsView);
                view.getScene().setRoot(spelregelsView);
            }
        });

        view.getBtnStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SpelView spelView = new SpelView();
                new SpelPresenter(model, spelView);
                view.getScene().setRoot(spelView);
            }
        });

    }
    private void updateView() {
        //view.imgAfbeelding.setImage(new Image("/fotos/stenen/B1.png"));
        //iew.imgAfbeelding.setImage(new Image(model.getPot().getStenen().get(0).getPad()));

    }
}
