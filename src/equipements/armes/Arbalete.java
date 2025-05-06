package equipements.armes;

public class Arbalete extends Distance{
    public Arbalete() {
        super(16);
        this.m_degats = des.lancerDes(1, 8);
        this.m_nom = "arbalète";
    }
}
