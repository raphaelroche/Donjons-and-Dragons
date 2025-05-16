package placable.equipements.armes;

import des.Des;
import donjons.Donjon;
import placable.entites.personnages.Personnage;

public class Rapiere extends Guerre{
    public Rapiere() {
        this.m_nom = "rapière";

    }

    public Rapiere(int x, int y) {
        super(x, y);
        this.m_nom = "rapière";

    }

    public Rapiere(Donjon d, Des des) {
        super(des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
        this.m_nom = "rapière";

    }

    public Rapiere(Donjon d){
        this(d, new Des());
    }
}
