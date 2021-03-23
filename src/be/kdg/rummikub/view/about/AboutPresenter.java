package be.kdg.rummikub.view.about;

import be.kdg.rummikub.model.Spel;

public class AboutPresenter {
    private Spel model;
    private AboutView view;

    public AboutPresenter(Spel model, AboutView view) {
        this.model = model;
        this.view = view;
    }
}
