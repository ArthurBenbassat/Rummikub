package be.kdg.rummikub.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;


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

        for (Integer stat : stats) {
            os.writeInt(stat);
        }
        os.writeInt(aantalZetten);
        os.close();

    }
}