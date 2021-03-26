package be.kdg.rummikub.model;


import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * In deze klasse bevinden zich de spelregels
 * @author Wouter Selis & Arthur Benbassat
 * @version 1.0
 * */
public class Spelregels {
    public static String getSpelregels() throws IOException{
        StringBuilder tekst = new StringBuilder();
        Path bestand = Paths.get( "resources/jsonBestanden/spelregels.json");

        if (Files.exists(bestand)){

                Scanner fileScanner = new Scanner(bestand);
                while (fileScanner.hasNext()) {
                    tekst.append(fileScanner.nextLine());
                }

        }
        return tekst.toString();
    }


    public static int getMinimunAantalPuntenEersteZet() throws IOException {
        JSONObject jsonObj = new JSONObject(getSpelregels());
        return jsonObj.getInt("aantalPuntenMinimaalEersteZet");
    }

    public static int getstartAantalSteentejes() throws IOException {
        JSONObject jsonObj = new JSONObject(getSpelregels());
        return jsonObj.getInt("startAantalSteentjes");
    }

    public static int getaantalStenenPerRij() throws IOException {
        JSONObject jsonObj = new JSONObject(getSpelregels());
        return jsonObj.getInt("aantalStenenPerRij");
    }
}
