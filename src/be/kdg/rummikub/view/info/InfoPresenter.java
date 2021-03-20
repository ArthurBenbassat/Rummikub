package be.kdg.rummikub.view.info;

import be.kdg.rummikub.model.Spel;

public class InfoPresenter {
    private Spel model;
    private InfoView view;

    public InfoPresenter(Spel model, InfoView view) {
        this.model = model;
        this.view = view;
    }
}
