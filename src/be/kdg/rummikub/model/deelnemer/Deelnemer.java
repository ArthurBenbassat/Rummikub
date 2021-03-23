package be.kdg.rummikub.model.deelnemer;

import be.kdg.rummikub.model.Rij;
import be.kdg.rummikub.model.steen.Steen;
import be.kdg.rummikub.view.spel.AfbeeldingSteen;

import java.util.*;
/**
 * Deze klasse is een onderdeel van het spel
 * @author Wouter Selis & Arthur Benbassat
 * @version 1.0
 */
public class Deelnemer {
    private ArrayList<Steen> stenen;
    private int aantalGezettenZetten;

    public Deelnemer() {
        stenen = new ArrayList<>();
    }

    public ArrayList<Steen> getStenen() {
        return stenen;
    }

    public void setStenen(ArrayList<Steen> stenen) {
        this.stenen = stenen;
    }

    public int getAantalGezettenZetten() {
        return aantalGezettenZetten;
    }

    public void verhoogZet() {
        aantalGezettenZetten++;
    }

    public boolean isGewonnen() {
        return false;
    }

    public void speelZet(AfbeeldingSteen steen) {

    }


    public void addSteen(Steen steen) {
        stenen.add(steen);
    }


    public void verwijderSteen(int steenIndex) {
        stenen.remove(steenIndex);
    }

}
