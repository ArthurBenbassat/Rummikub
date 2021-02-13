package be.kdg.rummikub.model.steen;

import java.util.*;

public class Pot {
    private List<Steen> stenen;

    public Pot() {
        stenen = new ArrayList<>();
        randomize();
    }

    public void randomize() {
        Collections.shuffle(stenen);
    }


    public void addSteen(Steen steen) {
        stenen.add(steen);
    }

    public List<Steen> getStenen() {
        return stenen;
    }

    public Steen getRandomSteen() {

        Random randomNummer = new Random();
        return stenen.get(randomNummer.nextInt(stenen.size()));
    }
}
