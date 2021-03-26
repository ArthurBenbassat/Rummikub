package be.kdg.rummikub.view.inputNaam;

import be.kdg.rummikub.model.Spel;
import be.kdg.rummikub.model.deelnemer.Speler;
import be.kdg.rummikub.view.spel.SpelPresenter;
import be.kdg.rummikub.view.spel.SpelView;

public class InputNaamPresenter {
    private Spel model;
    private InputNaamView view;

    public InputNaamPresenter(Spel spel, InputNaamView inputNaamView) {
        this.model = spel;
        this.view = inputNaamView;
        this.addEventHandlers();

    }

    private void addEventHandlers() {
        view.getBtnDoorgaan().setOnAction(actionEvent -> {
           if (view.getTxfInputNaam().getCharacters().length() != 0) {
                if (model.getSpelers()[0] instanceof Speler){
                    ((Speler) model.getSpelers()[0]).setNaam(view.getTxfInputNaam().toString());
                }
                SpelView spelView = new SpelView();
                new SpelPresenter(model, spelView);
                view.getScene().setRoot(spelView);
            } else {
               view.getLblFout().setVisible(true);
           }
        });
    }
}
