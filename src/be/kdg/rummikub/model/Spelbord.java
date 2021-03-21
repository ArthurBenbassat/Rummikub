package be.kdg.rummikub.model;

import be.kdg.rummikub.Console.Console;
import be.kdg.rummikub.model.steen.Steen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Spelbord {
    private ArrayList<Rij> rijen;
    private Steen speelVeld[][];
    public Spelbord(){
        rijen = new ArrayList<>();
    }


    public boolean checkEersteZet() {
        return false;
    }

    public Rij addRij(Steen steen, int locatieY) {
        rijen.add(new Rij(steen, locatieY));
        return rijen.get(rijen.size() - 1);
    }

    public ArrayList<Rij> getRijen() {
        return rijen;
    }

    public void updateSpeelveld(int x, int y) {
        speelVeld = new Steen[x][y];
    }
    public void plaatsSteen(int locatieX, int locatieY, Steen steen) {

        if (rijen.size() == 0 ) {
            Rij nieuweRij = addRij(steen, locatieY);
            nieuweRij.setMaxLocatie(locatieX - 1);
            nieuweRij.setMinLocatie(locatieX + 1);

        } else {
            boolean nieuweRij = true;

            int plaats;
            int rijNr;
            for (int i= 0; i < rijen.size(); i++) {
                System.out.println(i + rijen.get(i).getMaxLocatie());
                if (rijen.get(i).getLocatieY() == locatieY) {
                    if (rijen.get(i).getMinLocatie() >= locatieX && rijen.get(i).getMaxLocatie() <= locatieX) {
                        nieuweRij = false;
                        System.out.println("tges");
                    } else if (rijen.get(i).getMaxLocatie() == locatieX - 1 || rijen.get(i).getMinLocatie() == locatieX + 1) {
                        System.out.println("test");
                        rijNr = i;
                        nieuweRij = false;


                    }
                }
            }
            if (nieuweRij) {
                Rij nieuw = addRij(steen, locatieY);
                nieuw.setMaxLocatie(locatieX);
                nieuw.setMinLocatie(locatieX);

            } else {
                //rijen.get(plaats).addSteen();

            }
            speelVeld[locatieX][locatieY] = steen;
        }
            //for (Rij rij : rijen) {

            /*
            if (rij.getLocatieY() == locatieY) {
                if (rij.getMaxLocatie() == locatieX - 1 || rij.getMinLocatie() == locatieX + 1) {
                    rij.addSteen(steen);
                } else if (rij.getMinLocatie() >= locatieX && rij.getMaxLocatie() <= locatieX) {
                    //TODO rijen plaatsen en opschuiven
                    Rij nieuweRij = addRij(steen, locatieY);
                    nieuweRij.setMaxLocatie(locatieX - 1);
                    nieuweRij.setMinLocatie(locatieX + 1);
                }
            } else {
                Rij nieuweRij = addRij(steen, locatieY);
                nieuweRij.setMaxLocatie(locatieX - 1);
                nieuweRij.setMinLocatie(locatieX + 1);            }*/
        //}

        System.out.println(rijen.size());
    }

    private int getPlaats(Rij rij, Steen steen, int locatieX) {
        for (int j = 0; j < rij.getSteenInRij().size(); j++) {
            //if (rij)

        }
        return 0;
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
}
