package be.kdg.rummikub.Console;

public class Console {
    public String consoleVorm(String titel, String inhoud) {
        String lijnen[] = inhoud.split("\n");
        int groote = 0;
        for (int i = 0; i < lijnen.length; i++) {
            if (groote < lijnen[i].length()) {
                groote = lijnen[i].length();
            }
        }
        String updateInhoud = "";
        for (String s : lijnen) {
            updateInhoud += " " + s + "\n";
        }
        String onderkant = "";
        for (int i = 0; i < groote + 2; i++) {
            onderkant += "-";
        }
        String bovenkant = "";
        int byteGetal = 0;
        for (int i = 0; i < groote + 2; i++) {
            if (i <= (((groote + 2 ) - titel.length()) / 2) || (i > (((groote + 2 ) + titel.length()) / 2))) {
                bovenkant += "-";
            } else {
                bovenkant += titel.substring(byteGetal, byteGetal + 1);
                byteGetal ++;
            }

        }
        return String.format("%s%n%s%s", bovenkant,updateInhoud, onderkant);
    }


}
