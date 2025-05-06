package equipements.armes;

public class Arc extends Distance{
    public Arc() {
        super(16);
        this.m_degats = des.lancerDes(1, 6);
        this.m_nom = "arc court";
    }
}
