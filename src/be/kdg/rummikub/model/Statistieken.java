package be.kdg.rummikub.model;

import java.io.*;
import java.util.*;


/**
 * In deze klasse kan men de statistieken van de laatste 10 games zien.
 *@author Wouter Selis & Arthur Benbassat
 *@version 1.0
 * */
public class Statistieken {
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