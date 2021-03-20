package be.kdg.rummikub.model;

import be.kdg.rummikub.Console.Console;
import be.kdg.rummikub.model.steen.Steen;

import java.util.ArrayList;

public class Spelbord {
    private ArrayList<Rij> rijen;

    public Spelbord(){
        rijen = new ArrayList<>();
    }


    public boolean checkEersteZet() {
        return false;
    }

    public void addRij(Steen steen) {
        rijen.add(new Rij(steen, 0));
    }

    public ArrayList<Rij> getRijen() {
        return rijen;
    }

    public void plaatsSteen(int locatieX, int locatieY, Steen steen) {

        for (Rij rij : getRijen()) {
            if (rij.getLocatieY() == locatieY) {
                if (rij.getMaxLocatie() == locatieX - 1 || rij.getMinLocatie() == locatieX + 1) {

                } else if (rij.getMinLocatie() >= locatieX && rij.getMaxLocatie() <= locatieX) {
                    //TODO rijen plaatsen en opschuiven
                    addRij(steen);
                }
            }

        }
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


}
