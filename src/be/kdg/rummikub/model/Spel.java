package be.kdg.rummikub.model;

import be.kdg.rummikub.model.deelnemer.Deelnemer;
import be.kdg.rummikub.model.deelnemer.Speler;
import be.kdg.rummikub.model.steen.Kleur;
import be.kdg.rummikub.model.steen.Pot;
import be.kdg.rummikub.model.steen.Steen;

import java.util.Random;

public class Spel {
    private int beurt;
    private Pot pot;
    private Deelnemer[] spelers;
    private Spelbord spelbord;

    public Spel(int spelersAantal) {
        spelbord = new Spelbord();
        pot = new Pot();

        spelers = new Deelnemer[spelersAantal];
        for (int i = 0; i<spelersAantal;i++){
            spelers[i] = new Speler("speler "+i);
        }

        startSpel();
    }

    public void maakStenenAan() {
        for (int i = 1; i <= 13; i++) {
            for (Kleur kleur : Kleur.values()) {
                pot.addSteen(new Steen(kleur, i));
            }
        }
        pot.addSteen(new Steen(Kleur.ZWART, 0));
        pot.addSteen(new Steen(Kleur.ROOD, 0));
    }

    public void startVerdeelStenen() {
        for (int i = 0; i < spelers.length ; i++ ) {
            for (int j = 0; j < 10; j++) {
                Steen steen = pot.getRandomSteen();
                spelers[i].addSteen(steen);
                pot.getStenen().remove(steen);
            }
        }

    }

    public void zetKeuze() {
        Steen steen = pot.getRandomSteen();
        spelers[getBeurt()].addSteen(steen);
        pot.getStenen().remove(steen);
    }



    public void startSpel(){
        maakStenenAan();
        Random randomBeurt = new Random();
        beurt = randomBeurt.nextInt(spelers.length);
        startVerdeelStenen();
    }
    
    public void volgendeSpeler(){
        beurt++;
        if (beurt == spelers.length){
            beurt = 0;
        }
    }

    public Pot getPot() {
        return pot;
    }

    public int getBeurt() {
        return beurt;
    }

    public Deelnemer[] getSpelers() {
        return spelers;
    }

    public Spelbord getSpelbord() {
        return spelbord;
    }

    public void setBeurt(int beurt) {
        this.beurt = beurt;
    }

}
