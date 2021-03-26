package be.kdg.rummikub.model;

import be.kdg.rummikub.model.steen.Steen;

import java.util.*;


/**
 * In deze klasse bevinden zich de methoden voor de rijen.
 * @author Wouter Selis & Arthur Benbassat
 * @version 1.0
 * */
public class Rij {
    private ArrayList<Steen> steenInRij;
    private int locatieY;

    //TODO minstens 3 stenen meegeven om rij te maken
    public Rij(Steen steen, int locatieY) {
        steenInRij = new ArrayList<>();
        addSteen(steen);
        this.locatieY = locatieY;
    }

    public void addSteen(Steen steen) {
        steenInRij.add(steen);
    }

    public void addSteen(Steen steen, int positie) {
        steenInRij.add(positie, steen);
    }

    public ArrayList<Steen> getSteenInRij() {
        return steenInRij;
    }

    /**
     * Controle op gelegde rijen
     * */
    public boolean controleRij() {
        boolean controle = true;
        if (steenInRij.size() >= 3) {
            for (int i = 0; i < steenInRij.size(); i++) {
                if (i != 0) {
                    if( (steenInRij.get(i).getKleur() == steenInRij.get(i - 1).getKleur() && steenInRij.get(i).getWaarde() == (steenInRij.get(i - 1).getWaarde() + 1)) || (steenInRij.get(i).getWaarde() == 0) || steenInRij.get(i - 1).getWaarde() == 0) {
                        controle = true;
                    } else if ((steenInRij.get(i).getWaarde() == getSteenInRij().get(i - 1).getWaarde() && steenInRij.get(i).getKleur() != steenInRij.get(i - 1).getKleur()  || (steenInRij.get(i).getWaarde() == 0) || steenInRij.get(i - 1).getWaarde() == 0)) {
                        controle = true;
                    } else {
                        controle = false;
                    }
                }
            }

        } else {
            controle = false;
        }

        return controle;

    }

    @Override
    public String toString() {
        String uitkomst = "";
        for (Steen steen : steenInRij) {
            uitkomst += steen + " ";
        }
        return uitkomst;
    }

}


