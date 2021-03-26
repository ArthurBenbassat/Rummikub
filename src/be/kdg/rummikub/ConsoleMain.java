package be.kdg.rummikub;

import be.kdg.rummikub.model.*;
import be.kdg.rummikub.model.deelnemer.Computer;
import be.kdg.rummikub.model.steen.Steen;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleMain {
    public static void main(String[] args) {
        try {
            //Statistieken.setStatistieken(7);
            //Statistieken.setStatistieken(15);
            //Statistieken.setStatistieken(10);
            Statistieken.setStatistieken(14);
        } catch (IOException e) {
            System.out.println("kkr");
        }

        System.exit(0);

        Spel spel = new Spel(2);
        Scanner keyboard = new Scanner(System.in);

        while(true){
            System.out.print("speler aan de beurt: "+ spel.getSpelers()[spel.getBeurt()]+ "\nstenen ("+ spel.getSpelers()[spel.getBeurt()].getStenen().size() + "): "+ spel.getSpelers()[spel.getBeurt()].getStenen());

            int eindeRonde = 2;
            do {
                System.out.print("\nWil je iets leggen of wil je een steentje nemen: [0: leggen; 1: nemen] ");
                eindeRonde = keyboard.nextInt();
            } while (eindeRonde != 0 && eindeRonde != 1);

            if (eindeRonde == 1){
                spel.volgendeSpeler();
                Steen extraSteen = spel.getPot().getRandomSteen();
                spel.getSpelers()[spel.getBeurt()].addSteen(extraSteen);
                spel.getPot().getStenen().remove(extraSteen);
            }
            else {

                boolean beurt = true;
                do {
                    System.out.print("Welke steen wil je verzetten van je bordje? [99 om beurt te beïndigen] ");
                    int steen = keyboard.nextInt();
                    if (steen == 99) {
                        beurt = false;
                    } else {
                        Steen steenKeuze = spel.getSpelers()[spel.getBeurt()].getStenen().get(steen);

                        int keuzeBestaandeRij = 2;
                        if (spel.getSpelbord().getRijen().size() != 0) {
                            do {
                                System.out.print("Wil je dit toevoegen aan bestaande rij? [0: ja; 1: nee] ");
                                keuzeBestaandeRij = keyboard.nextInt();
                            } while (keuzeBestaandeRij != 1 && keuzeBestaandeRij != 0);
                        }

                        if (keuzeBestaandeRij == 0 ) {
                            int i = 0;
                            for (Rij rijen : spel.getSpelbord().getRijen()) {
                                System.out.println(i + ") " + rijen);
                                i ++;
                            }
                            System.out.print("Naar welke rij wil je dit verplaatsen: ");
                            int rijKeuze = keyboard.nextInt();
                            System.out.print("Welke plaats in de rij wil het steentje leggen? ");
                            int plaatsKeuze = keyboard.nextInt();

                            spel.getSpelbord().getRijen().get(rijKeuze).addSteen(steenKeuze, plaatsKeuze);
                            //System.out.println(spel.getSpelbord().controleerRij(spel.getSpelbord().getRijen().get(rijKeuze)));

                        } else {
                            spel.getSpelbord().getRijen().add(new Rij(steenKeuze, 1));
                            System.out.println("De steen is toegevoegd");
                        }
                        spel.getSpelers()[spel.getBeurt()].getStenen().remove(steenKeuze);
                    }

                } while (beurt);

                System.out.println(spel.getSpelbord());
                spel.volgendeSpeler();

            }
        }

    }
}
