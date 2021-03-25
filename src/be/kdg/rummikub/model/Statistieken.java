package be.kdg.rummikub.model;

import be.kdg.rummikub.view.spel.SpelPresenter;
import be.kdg.rummikub.view.spelregels.SpelregelsView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/**
 * In deze klasse kan men de statistieken van de laatste 10 games zien.
 *@author Wouter Selis & Arthur Benbassat
 *@version 1.0
 * */
public class Statistieken {
    static class Stats {
        Date datum;
        int aantalZetten;
        String winnaar;

        Stats(Date datum, int aantalZetten, String winnaar) {
            this.datum = datum;
            this.aantalZetten = aantalZetten;
            this.winnaar = winnaar;
        }
    }



    /**
     * Gegevens uit een binair bestand lezen
     * */
    public static ArrayList<Integer> getStatistieken() throws IOException{
        ArrayList<Integer> stats = new ArrayList<>();

        long fileSize = new File("resources/statsFile.bin").length();
        FileInputStream filesIs = new FileInputStream("resources/statsFile.bin");

        ObjectInputStream is = new ObjectInputStream(filesIs);
            for (int i = 7; i < fileSize; i+=4) {
                stats.add(is.readInt());
            }

        is.close();
        Collections.sort(stats);
        return stats;
    }


    /**
     * Gegevens naar een binair bestand schrijven
     * */
    public static void setStatistieken(int aantalZetten) throws IOException{
        ArrayList<Integer> stats;
        stats = getStatistieken();

        FileOutputStream fileOs = new FileOutputStream("resources/statsFile.bin");
        ObjectOutputStream os = new ObjectOutputStream(fileOs);
        stats.add(aantalZetten);
        Collections.sort(stats);
        boolean maxAantalStats = false;
        while (!maxAantalStats){
            if (stats.size() > 10 ){
                System.out.println(stats.get(stats.size() - 1 ));
                stats.remove(stats.size() - 1 );
            } else{
                maxAantalStats = true;
            }
        }

        for (Integer stat : stats) {
            os.writeInt(stat);
        }

        os.close();

    }
}