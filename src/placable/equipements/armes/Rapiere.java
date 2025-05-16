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
}
