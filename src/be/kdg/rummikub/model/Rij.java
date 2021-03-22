package be.kdg.rummikub.model;

import be.kdg.rummikub.model.steen.Steen;

import java.util.*;

public class Rij {
    private boolean rijColorType;
    private ArrayList<Steen> steenInRij;
    private int minLocatie;
    private int maxLocatie;
    private int locatieY;

    //TODO minstens 3 stenen meegeven om rij te maken
    public Rij(Steen steen, int locatieY) {
        steenInRij = new ArrayList<>();
        addSteen(steen);
        this.locatieY = locatieY;
    }

    public void addSteen(Steen steen) {
        steenInRij.add(steen);
        rijColorType = steenInRij.get(0).getKleur() == steen.getKleur();
    }

    public void addSteen(Steen steen, int positie) {
        steenInRij.add(positie, steen);
    }

    public ArrayList<Steen> getSteenInRij() {
        return steenInRij;
    }

    public boolean controleRij() {
        boolean controle = true;
        if (steenInRij.size() >= 3) {
            for (int i = 0; i < steenInRij.size(); i++) {
                if (i != 0) {
                    if( (steenInRij.get(i).getKleur() == steenInRij.get(i - 1).getKleur() && steenInRij.get(i).getWaarde() == (steenInRij.get(i - 1).getWaarde() + 1)) || (steenInRij.get(i).getWaarde() == 0) || steenInRij.get(i - 1).getWaarde() == 0) {
                        controle = true;
                        rijColorType = true;
                    } else if ((steenInRij.get(i).getWaarde() == getSteenInRij().get(i - 1).getWaarde() && steenInRij.get(i).getKleur() != steenInRij.get(i - 1).getKleur()  || (steenInRij.get(i).getWaarde() == 0) || steenInRij.get(i - 1).getWaarde() == 0)) {
                        controle = true;
                        rijColorType = false;
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

    public int getMinLocatie() {
        return minLocatie;
    }

    public int getMaxLocatie() {
        return maxLocatie;
    }

    public int getLocatieY() {
        return locatieY;
    }

    public void setMaxLocatie(int maxLocatie) {
        this.maxLocatie = maxLocatie;
    }

    public void setMinLocatie(int minLocatie) {
        this.minLocatie = minLocatie;
    }
}


