package be.kdg.rummikub.model.deelnemer;

public class Computer extends Deelnemer {
    private int niveau;

    public Computer() {
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }

    public int bedenkZet() {
        return 0;
    }
}
