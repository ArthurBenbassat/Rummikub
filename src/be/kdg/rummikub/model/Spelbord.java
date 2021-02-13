package be.kdg.rummikub.model;

import be.kdg.rummikub.Console.Console;
import be.kdg.rummikub.model.steen.Steen;

import java.util.ArrayList;

public class Spelbord {
    private ArrayList<Rij> rijen;

    public Spelbord(){
        rijen = new ArrayList<>();
    }


    public String controleerRij(Rij rij) {
        if (getRijen().get(rijen.indexOf(rij)).controleRij()) {
            return "De rij is goed gekeurd";
        } else {
            return "De rij is (nog) niet goed gekeurd";
        }
    }

    public boolean checkEersteZet() {
        return false;
    }

    public void addRij(Steen steen) {
        rijen.add(new Rij(steen));
    }

    public ArrayList<Rij> getRijen() {
        return rijen;
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
