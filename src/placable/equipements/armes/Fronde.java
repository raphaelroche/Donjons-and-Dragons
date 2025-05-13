package placable.equipements.armes;

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

    public Fronde(Donjon d) {
        super(6);
        this.m_degats = 0;
        this.m_nom = "fronde";
        this.setLocation(des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
    }

    @Override
    public void determinerDegat(){
        this.m_degats = des.lancerDes(1, 4);
    }
}
