package placable.equipements.armes;

public class EpeeDeuxMain extends Guerre{
    public EpeeDeuxMain() {
        this.m_nom = "épee à 2 mains";

    }

    public EpeeDeuxMain(int x, int y) {
        super(x, y);
        this.m_nom = "épee à 2 mains";

    }
    @Override
    public void determinerDegat(){
        this.m_degats = des.lancerDes(2, 6);
    }

}
