package be.kdg.rummikub.model;

import be.kdg.rummikub.model.deelnemer.Deelnemer;
import be.kdg.rummikub.model.deelnemer.MakkelijkeComputer;
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
import java.util.*;

/**
 * Deze klasse is de het hart van het spel. Deze stuurt alles aan.
 * @author Wouter Selis & Arthur Benbassat
 * @version 1.0
 **/
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
        for (int i = 1; i < spelersAantal; i++){
            spelers[i] = new MakkelijkeComputer();
        }
        spelers[0] = new Speler("Speler");


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
            for (int j = 0; j < 14; j++) {
                Steen steen = pot.getRandomSteen();
                speler.addSteen(steen);
                pot.getStenen().remove(steen);
            }
        }

    }

    /**
     * Je zet de hier alles in terug van de vorige zet. Hierbij wordt alles terug opgenomen van het json bestand
     * @author Wouter Selis & Arthur Benbassat
     **/
    public void zetAllesTerug() throws IOException{
        StringBuilder tekst = new StringBuilder();
        Path bestand = Paths.get( "resources/jsonBestanden/spel.json");

        if (Files.exists(bestand)){
            try {
                Scanner fileScanner = new Scanner(bestand);
                while (fileScanner.hasNext()) {
                    tekst.append(fileScanner.nextLine());
                }
            } catch (IOException e){
                throw new IOException(e);
            }
        }
        Gson gson = new Gson();
        Spel spel = gson.fromJson(tekst.toString(), Spel.class);
        beurt = spel.getBeurt();
        spelers = spel.getSpelers();
        pot = spel.getPot();
        spelbord = spel.getSpelbord();

    }

    /**
     * Je zet de hier alles json na een zet.
     * @author Wouter Selis & Arthur Benbassat
     */
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

    /**
     * De volgende speler wordt hier opgeroepen. ALs de coputer aanzet is worden zijn spelen ook hier gecreerd
     * @author Wouter Selis & Arthur Benbassat
     **/
    public void volgendeSpeler(){
        beurt++;
        if (beurt == spelers.length){
            beurt = 0;
        }
        if (beurt != 0) {
            if (spelers[beurt] instanceof MakkelijkeComputer) {
                ((MakkelijkeComputer) spelers[beurt]).berekenZet();
                System.out.println("aantal opties" + ((MakkelijkeComputer) spelers[beurt]).getZettenHand().size());
                for (List<Steen> zetten: ((MakkelijkeComputer) spelers[beurt]).getZettenHand()) {
                    System.out.println("Rij" + zetten);
                    for (int i = 0; i < zetten.size(); i++) {
                        //System.out.println(zetten.get(i));
                    }
                }
            }
            this.volgendeSpeler();
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
