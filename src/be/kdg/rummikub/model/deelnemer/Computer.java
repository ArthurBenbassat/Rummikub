package be.kdg.rummikub.model.deelnemer;

import be.kdg.rummikub.model.Spelregels;
import be.kdg.rummikub.model.steen.Kleur;
import be.kdg.rummikub.model.steen.Pot;
import be.kdg.rummikub.model.steen.Steen;

import java.io.IOException;
import java.util.*;

/**
 * Deze klasse is een Deelnemer. Dit bedenkt zelf zetten
 *
 * @author Wouter Selis & Arthur Benbassat
 * @version 1.0
 */
public abstract class Computer extends Deelnemer {
    private int niveau;

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }

    public static boolean mogelijkheidZet() {
        Random rnd = new Random();


        if (rnd.nextInt(3) == 0) {
            return true;
        } else {
            return false;
        }


    }

    public static List<Steen> getMogelijkeZetten(Pot pot, boolean eersteZet) throws IOException {
        Random rnd = new Random();
        List<Steen> zetten;
        int waarde;
        if (eersteZet) {
            waarde = rnd.nextInt(13) + 1;
        } else {
            waarde = rnd.nextInt(4) + 10;
        }

        if (rnd.nextBoolean()) {
            zetten = getRijDezelfdeWaarde(pot, waarde);
        } else {
            zetten = getRijOpeenvolgdeWaarde(pot, waarde);
        }
        
        return zetten;
    }

    public static List<Steen> getRijDezelfdeWaarde(Pot pot, int waarde) throws IOException {
        List<Steen> tempSpeelbaar = new ArrayList<>();
        List<Steen> speelbaar = new ArrayList<>();
        for (int i = 0; i < pot.getStenen().size(); i++) {

            if (pot.getStenen().get(i).getWaarde() == waarde) {
                tempSpeelbaar.add(pot.getStenen().get(i));
            }
        }
        if (tempSpeelbaar.size() >= Spelregels.getaantalStenenPerRij()) {
            int verschillend = 0;
            for (int i = 0; i < tempSpeelbaar.size(); i++) {
                if (i != 0) {
                    if (tempSpeelbaar.get(i).getKleur() != tempSpeelbaar.get(i - 1).getKleur()) {
                        verschillend++;
                    }
                }
            }
            if (verschillend >= Spelregels.getaantalStenenPerRij()) {
                Kleur[] algeweest = new Kleur[3];
                int indexKleur = 0;
                for (int i = 0; i < tempSpeelbaar.size(); i++) {
                    if (algeweest[0] == null) {
                        algeweest[indexKleur] = tempSpeelbaar.get(i).getKleur();
                        indexKleur++;
                        speelbaar.add(tempSpeelbaar.get(i));
                    } else {
                        boolean nietGeweest = true;
                        for (Kleur kleur : algeweest) {
                            if (kleur == tempSpeelbaar.get(i).getKleur()) {
                                nietGeweest = false;
                            }
                        }
                        if (nietGeweest) {
                            if (indexKleur < 3) {
                                speelbaar.add(tempSpeelbaar.get(i));
                                algeweest[indexKleur] = tempSpeelbaar.get(i).getKleur();
                                indexKleur++;
                            }
                        }
                    }
                }
                if (speelbaar.size() < Spelregels.getaantalStenenPerRij()) {
                    speelbaar.add(tempSpeelbaar.get(0));
                }
            }
        }
        return speelbaar;
    }
    
    
    public static List<Steen> getRijOpeenvolgdeWaarde(Pot pot, int waarde) {
        Kleur kleur = pot.getStenen().get(0).getKleur();
        boolean onder = false;
        boolean boven = false;
        boolean juiste = false;
        List<Steen> speelbaar = new ArrayList<>();
        for (int i = 0; i < pot.getStenen().size(); i++) {
            if (pot.getStenen().get(i).getKleur() == kleur) {
                if (waarde == 12) {
                    if (pot.getStenen().get(i).getWaarde() == (waarde - 1) && !onder) {
                        speelbaar.add(pot.getStenen().get(i));
                        onder = true;
                    } else if (pot.getStenen().get(i).getWaarde() == (waarde + 1) && !boven) {
                        speelbaar.add(pot.getStenen().get(i));
                        boven = true;
                    } else if (pot.getStenen().get(i).getWaarde() == waarde && !juiste) {
                        speelbaar.add(pot.getStenen().get(i));
                        juiste = true;
                    }

                } else if (waarde == 13) {
                    if (pot.getStenen().get(i).getWaarde() == (waarde - 1) && !onder) {
                        speelbaar.add(pot.getStenen().get(i));
                        onder = true;
                    } else if (pot.getStenen().get(i).getWaarde() == (waarde - 2) && !boven) {
                        speelbaar.add(pot.getStenen().get(i));
                        boven = true;
                    } else if (pot.getStenen().get(i).getWaarde() == waarde && !juiste) {
                        speelbaar.add(pot.getStenen().get(i));
                        juiste = true;
                    }
                } else {
                    if (pot.getStenen().get(i).getWaarde() == (waarde + 1) && !onder) {
                        speelbaar.add(pot.getStenen().get(i));
                        onder = true;
                    } else if (pot.getStenen().get(i).getWaarde() == (waarde + 2) && !boven) {
                        speelbaar.add(pot.getStenen().get(i));
                        boven = true;
                    } else if (pot.getStenen().get(i).getWaarde() == waarde && !juiste) {
                        speelbaar.add(pot.getStenen().get(i));
                        juiste = true;
                    }
                }

            }
        }
        speelbaar.sort(new Comparator<Steen>() {
            @Override
            public int compare(Steen o1, Steen o2) {
                return o1.getWaarde() - o2.getWaarde();
            }
        });
        return speelbaar;
    }
}
