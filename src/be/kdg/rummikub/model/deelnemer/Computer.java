package be.kdg.rummikub.model.deelnemer;

import be.kdg.rummikub.model.Spelregels;
import be.kdg.rummikub.model.steen.Steen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
/**
 * Deze klasse is een Deelnemer. Dit bedenkt zelf zetten
 * @author Wouter Selis & Arthur Benbassat
 * @version 1.0
 */
public abstract class Computer extends Deelnemer {
    private List<List <Steen>> zettenHand;
    private int niveau;

    public Computer() {
        zettenHand = new ArrayList<>();
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }


    public List<List<Steen>> getZettenHand() {
        return zettenHand;
    }

    public abstract void berekenZet();

    public void berekenNummersHand() {
        ArrayList<Steen> speelbaar = new ArrayList();
        int normaleI;
        int jokerGebruikt = 0;
        getStenen().sort(new Comparator<Steen>() {
            @Override
            public int compare(Steen o1, Steen o2) {
                return o1.getWaarde() - o2.getWaarde();
            }
        });

        for (int i = 0; i < getStenen().size(); i++) {
            normaleI = i;
            speelbaar.add(getStenen().get(i));;
            if (getStenen().get(i).getWaarde() != 0) {
                int groepjokers = jokerGebruikt;
                for (int j =0; j <getStenen().size(); j++) {
                    for (int afstand = 1; afstand <= 1 + groepjokers; afstand++) {
                        if (getStenen().get(i) != getStenen().get(j) && getStenen().get(i).getKleur() == getStenen().get(j).getKleur() && getStenen().get(i).getWaarde() == getStenen().get(j).getWaarde() - afstand) {
                            if (afstand > 1) {
                                speelbaar.add(getStenen().get(0));
                                groepjokers--;
                            }
                            speelbaar.add(getStenen().get(j));
                            i = j;
                            afstand += jokerGebruikt;
                        }
                    }
                }
                System.out.println(Spelregels.getstartAantalSteentejes());
                if (speelbaar.size() >= Spelregels.getstartAantalSteentejes()) {
                    System.out.println(speelbaar);
                    zettenHand.add(speelbaar);
                    System.out.println(zettenHand.size());
                    i = normaleI;

                }
            } else {
                jokerGebruikt++;
            }
        }


    }
    public void checkZetten() {
        for (Iterator<List<Steen>> iterator = zettenHand.iterator(); iterator.hasNext(); ) {
            List<Steen> next = iterator.next();
            boolean bestaatAl = false;
            for (Steen steen : next) {
                for (int i = 0; i < zettenHand.size(); i++) {
                    if (zettenHand.get(i) != steen) {
                        for (int j = 0; j < zettenHand.get(i).size(); j++) {
                            if (steen.getKleur() == zettenHand.get(i).get(j).getKleur() &&  steen.getWaarde() == zettenHand.get(i).get(j).getWaarde()){
                                bestaatAl = true;
                            }
                        }
                    }
                }
            }
            if (bestaatAl) {
                iterator.remove();
            }
        }
    }

}
