package be.kdg.rummikub.model.deelnemer;

import be.kdg.rummikub.model.steen.Steen;

import java.util.*;
/**
 * Deze klasse is een onderdeel van het spel
 * @author Wouter Selis & Arthur Benbassat
 * @version 1.0
 */
public class Deelnemer {
    private ArrayList<Steen> stenen;
    private int aantalGezettenZetten;
    private boolean eersteZet;

    public Deelnemer() {
        stenen = new ArrayList<>();
        eersteZet = false;
    }

    public boolean isEersteZet() {
        return eersteZet;
    }

    public void setEersteZet(boolean eersteZet) {
        this.eersteZet = eersteZet;
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




    public void addSteen(Steen steen) {
        stenen.add(steen);
    }



}
