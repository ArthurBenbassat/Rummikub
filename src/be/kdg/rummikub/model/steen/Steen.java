package be.kdg.rummikub.model.steen;



public class Steen {
    private Kleur kleur;
    private int waarde;
    private String pad;

    public Steen(Kleur kleur, int waarde) {
        this.kleur = kleur;
        this.waarde = waarde;
        this.setPad();
    }

    public Steen(String url) {

        if (url.contains("B")) {
            this.kleur = Kleur.BLAUW;
        } else if (url.contains("R")) {
            this.kleur = Kleur.ROOD;
        } else if (url.contains("G")) {
            this.kleur = Kleur.GEEL;
        } else if ((url.contains("Z"))) {
            this.kleur = Kleur.ZWART;
        } else {
            System.out.println("error1");
        }
        try {
            System.out.println(url.substring(1));
            this.waarde = Integer.getInteger(url.substring(1));

        } catch (RuntimeException e) {
            System.out.println("error");
        }

        this.setPad();
    }

    public void setPad() {
        this.pad = "/fotos/stenen/" + this.kleur.toString().charAt(0) + this.waarde + ".png";
    }

    public String getPad() {
        return pad;
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
