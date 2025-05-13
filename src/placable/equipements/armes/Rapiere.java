package placable.equipements.armes;

import donjons.Donjon;

public class Rapiere extends Guerre{
    public Rapiere() {
        this.m_nom = "rapière";
    }

    public Rapiere(int x, int y) {
        super(x, y);
        this.m_nom = "rapière";

    }

    public Rapiere(Donjon d) {
        this.m_nom = "rapière";
        this.setLocation(des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
    }
}
