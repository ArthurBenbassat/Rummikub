package be.kdg.rummikub.model;

import be.kdg.rummikub.model.deelnemer.Deelnemer;
import be.kdg.rummikub.model.deelnemer.Speler;
import be.kdg.rummikub.model.steen.Kleur;
import be.kdg.rummikub.model.steen.Pot;
import be.kdg.rummikub.model.steen.Steen;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

public class Spel {
    private int beurt;
    private Pot pot;
    private Deelnemer[] spelers;
    private Spelbord spelbord;

    public Spel(int beurt, Pot pot, Deelnemer[] spelers, Spelbord spelbord) {
        this.beurt = beurt;
        this.pot = pot;
        this.spelers = spelers;
        this.spelbord = spelbord;
    }

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
        for (Deelnemer speler : spelers) {
            for (int j = 0; j < 10; j++) {
                Steen steen = pot.getRandomSteen();
                speler.addSteen(steen);
                pot.getStenen().remove(steen);
            }
        }

    }

    public void zetAllesTerug() {
        StringBuilder tekst = new StringBuilder();
        Path bestand = Paths.get( "resources/jsonBestanden/spel.json");

        if (Files.exists(bestand)){
            try {

                Scanner fileScanner = new Scanner(bestand);
                while (fileScanner.hasNext()) {
                    tekst.append(fileScanner.nextLine());
                }
            } catch (IOException e){
                System.out.println(e.toString());
            }
        }
        Gson gson = new Gson();
        Spel spel = gson.fromJson(tekst.toString(), Spel.class);
        beurt = spel.getBeurt();
        spelers = spel.getSpelers();
        pot = spel.getPot();
        spelbord = spel.getSpelbord();

    }

    public void zetAllesInJson() throws IOException, RuntimeException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Spel spel = new Spel(beurt, pot, spelers, spelbord);
            String jsonString = mapper.writeValueAsString(spel);

            try {
                Formatter fm = new Formatter("resources/jsonBestanden/spel.json");
                fm.format(jsonString);
                fm.close();
            } catch (IOException e) {
                throw new IOException(e);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

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
