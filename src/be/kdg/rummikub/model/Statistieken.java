package be.kdg.rummikub.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public void voegSpelToe(Spel spel) throws IOException, RuntimeException {
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
        Path bestand = Paths.get("resources/jsonBestanden/stats.json");

        if (Files.exists(bestand)) {
            try {
                Scanner fileScanner = new Scanner(bestand);
                while (fileScanner.hasNext()) {
                    tekst.append(fileScanner.nextLine());
                }
            } catch (IOException e) {
                throw new IOException(e);
            }
        }
        return tekst.toString();
    }


    public int getStatistieken() {
        //aanmaken van binair bestand


        ArrayList<Integer> stats = new ArrayList<>();

        int x=0;
        try{
            FileInputStream filesIs = new FileInputStream("resources/statsFile.bin");
            ObjectInputStream is = new ObjectInputStream(filesIs);

            x= is.readInt();
            int y= is.readInt();
            System.out.println(x);
            System.out.println(y);
            is.close();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }


        return x;



    }



    public void setStatistieken(int aantalzetten) {


        try {
            FileOutputStream fileOs = new FileOutputStream("resources/statsFile.bin");
            ObjectOutputStream os = new ObjectOutputStream(fileOs);
            os.writeInt(5);
            os.close();
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch(IOException e){
            System.out.println(e);
        }

        System.out.println("gelukt");
    }
}