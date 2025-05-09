package equipements.armes;

public class Fronde extends Distance{
    public Fronde() {
        super(6);
        this.m_degats = 0;
        this.m_nom = "fronde";
    }

    @Override
    public void determinerdegat(){
        this.m_degats = des.lancerDes(1, 4);
    }
}
