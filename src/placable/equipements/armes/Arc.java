package placable.equipements.armes;

public class Arc extends Distance{
    public Arc() {
        super(16);
        this.m_degats = 0;
        this.m_nom = "arc court";
    }

    @Override
    public void determinerDegat(){
        this.m_degats = des.lancerDes(1, 6);
    }
}
