package be.kdg.rummikub.model.deelnemer;
/**
 * Deze klasse is een deelnemer
 * @author Wouter Selis & Arthur Benbassat
 * @version 1.0
 */
public class Speler extends Deelnemer{
    private String naam;

    public Speler(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
            this.naam = naam;
    }

    @Override
    public String toString() {
        return "Speler{" +
                "naam='" + naam + '\'' +
                '}';
    }
}
