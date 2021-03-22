package be.kdg.rummikub.view.einde;

import be.kdg.rummikub.model.Spel;
import be.kdg.rummikub.view.start.StartView;

public class eindePresenter {

    private Spel model;
    private StartView view;

    public eindePresenter(Spel model, StartView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {

    }

    private void updateView() {

    }
}
