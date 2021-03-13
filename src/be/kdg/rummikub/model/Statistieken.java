package be.kdg.rummikub.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

public class Statistieken {
    static class Stats {
        int duur;
        int aantalZetten;
        String winnaar;

        Stats(int duur, int aantalZetten, String winnaar) {
            this.duur = duur;
            this.aantalZetten = aantalZetten;
            this.winnaar = winnaar;
        }
    }
    public void voegSpelToe(Spel spel) throws IOException, RuntimeException{
        ObjectMapper mapper = new ObjectMapper();
        try {
            Stats stats = new Stats(1, spel.getSpelers()[0].getAantalGezettenZetten(), "Naam");
            String jsonString = mapper.writeValueAsString(stats);

            try {
                Formatter fm = new Formatter("resources/jsonBestanden/stats.json");
                fm.format(jsonString);
                fm.close();
            } catch (IOException e) {
                throw new IOException(e);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getStats() throws IOException {
        StringBuilder tekst = new StringBuilder();
        Path bestand = Paths.get( "resources/jsonBestanden/stats.json");

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
        return tekst.toString();
    }
}
