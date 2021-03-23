package be.kdg.rummikub.model.deelnemer;
/**
 * Deze klasse is een computer en een deelnemer
 * @author Wouter Selis & Arthur Benbassat
 * @version 1.0
 */
public class MakkelijkeComputer  extends Computer{

    @Override
    public void berekenZet() {
        super.berekenNummersHand();

        super.checkZetten();
    }

}
