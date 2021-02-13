package be.kdg.rummikub.model;

//import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Spelregels {
    public String getSpelregels() {
        StringBuilder tekst = new StringBuilder();
        Path bestand = Paths.get("src" + File.separator + "be" + File.separator + "kdg" + File.separator + "rummikub" + File.separator + "spelregels" + File.separator + "spelregels.json");

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
        return tekst.toString();
    }

    public int getTijdslimietInSeconden() {
        /*
        JSONObject jsonObj = new JSONObject(getSpelregels());
        return jsonObj.getInt("tijdslimietInSeconden");

         */return 0;
    }

}
