package be.kdg.rummikub.model.deelnemer;

public class Speler extends Deelnemer{
    private String naam;

    public Speler(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    @Override
    public String toString() {
        return "Speler{" +
                "naam='" + naam + '\'' +
                '}';
    }
}
