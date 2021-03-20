package be.kdg.rummikub.model.deelnemer;

public class MakkelijkeComputer  extends Computer{

    @Override
    public void berekenZet() {
        super.berekenNummersHand();

        super.checkZetten();
    }

}
