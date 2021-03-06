package be.kdg.rummikub.model;

import be.kdg.rummikub.Console.Console;
import be.kdg.rummikub.model.steen.Steen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * In deze klasse bevinden zich de methoden voor het spelbord.
 * @author Wouter Selis & Arthur Benbassat
 * @version 1.0
 * */
public class Spelbord {
    private List<Rij> rijen;
    private Steen speelVeld[][];


    public Spelbord(){
        rijen = new ArrayList<>();
    }


    public Spelbord(List<Rij> rijen, Steen speelVeld[][]) {
        this.rijen = rijen;
        this.speelVeld = speelVeld;
    }


    public Rij addRij(Steen steen, int locatieY) {
        rijen.add(new Rij(steen, locatieY));
        return rijen.get(rijen.size() - 1);
    }

    public List<Rij> getRijen() {
        return rijen;
    }

    public void updateSpeelveld(int x, int y) {
        speelVeld = new Steen[x][y];
    }

    public void plaatsSteen(int locatieX, int locatieY, Steen steen) throws RuntimeException {
        if (speelVeld[locatieX][locatieY] != null) {
            throw new RuntimeException("Er staat al een steen");
        }

        speelVeld[locatieX][locatieY] = steen;
    }

    public Steen[][] getSpeelVeld() {
        return speelVeld;
    }

    @Override
    public String toString() {
        Console console = new Console();
        String uitkomst = "";
        for (Rij rij : rijen) {
            uitkomst += "Rij "+ rijen.indexOf(rij) + ": " +  rij.toString() + "\n";
        }

        return console.consoleVorm("Spelbord", uitkomst);
    }


    public void verwijderSteen(int locatieX, int locatieY) {
        speelVeld[locatieX][locatieY] = null;
    }


    /**
     * kijkt speelbord na op rijen en vormt rijen samen tot een geheel.
     * */
    public boolean checkSpeelveld() {
        boolean goedKeuring = true;
        rijen.clear();
        for (int i = 0; i < speelVeld.length; i++) {
            for (int j = 0; j < speelVeld[i].length; j++) {
                if (speelVeld[i][j] != null) {
                    Rij rijIndex = this.addRij(speelVeld[i][j], i);
                    boolean extraSteenBijRij =false;
                    int x=i;
                    int aantalStenen = 0;
                    do {
                 x++;       
                 if (speelVeld[x][j] == null) {
                            extraSteenBijRij = true;
                       } else {
                            aantalStenen++;
                            rijIndex.addSteen(speelVeld[x][j], aantalStenen);
                        }
       
                    } while (!extraSteenBijRij);
         i = x;           
                }
            }

        }

        for (Rij rij : rijen) {
            if (!rij.controleRij()) {
                goedKeuring = false;
            }
        }
        return goedKeuring;
    }

    public ArrayList<Integer> getStartNieuweRij() {
        int aantalVrijKolommen = 0;
        ArrayList<Integer> plaats = new ArrayList<>();
        for (int i = 0; i < speelVeld[0].length; i++) {
            for (int j = 0; j < speelVeld.length; j++) {
                if (speelVeld[j][i] == null) {
                    aantalVrijKolommen++;
                } else {
                    aantalVrijKolommen=0;
                }
                if (aantalVrijKolommen==5) {
                    plaats.add(j - 3);
                    plaats.add(i);
                    return plaats;
                }
            }
            aantalVrijKolommen=0;
        }

        plaats.add(99);
        plaats.add(99);

        return plaats;

    }
}
