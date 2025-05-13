package placable.equipements.armes;

import des.Des;
import donjons.Donjon;

public class Fronde extends Distance{
    public Fronde() {
        super(6);
        this.m_degats = 0;
        this.m_nom = "fronde";
    }

    public Fronde(int x, int y) {
        super(6, x, y);
        this.m_degats = 0;
        this.m_nom = "fronde";
    }

    public Fronde(Donjon d, Des des) {
        super(6,des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
        this.m_degats = 0;
        this.m_nom = "fronde";

    }

    public Fronde(Donjon d){
        this(d, new Des());
    }

    @Override
    public void determinerDegat(){
        this.m_degats = des.lancerDes(1, 4);
    }
}
