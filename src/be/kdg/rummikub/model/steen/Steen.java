package be.kdg.rummikub.model.steen;

public class Steen {
    private Kleur kleur;
    private int waarde;

    public Steen(Kleur kleur, int waarde) {
        this.kleur = kleur;
        this.waarde = waarde;
    }

    public Kleur getKleur() {
        return kleur;
    }

    public int getWaarde() {
        return waarde;
    }

    @Override
    public String toString() {
        return "Steen{" +
                "kleur=" + kleur +
                ", waarde=" + waarde +
                '}';
    }
}
