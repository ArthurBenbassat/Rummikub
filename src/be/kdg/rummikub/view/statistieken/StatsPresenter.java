package be.kdg.rummikub.view.statistieken;

import be.kdg.rummikub.model.Spel;

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
        int zetterSpeler=model.getSpelers()[0].getAantalGezettenZetten();
        int zettenComputer=model.getSpelers()[1].getAantalGezettenZetten();
    }

    private void addEventHandlers() {

    }
}
