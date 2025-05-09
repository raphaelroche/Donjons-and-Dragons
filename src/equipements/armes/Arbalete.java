package equipements.armes;

public class Arbalete extends Distance{
    public Arbalete() {
        super(16);
        this.m_degats = 0;
        this.m_nom = "arbalète";
    }

    @Override
    public void determinerDegat(){
        this.m_degats = des.lancerDes(1, 8);
    }
}
