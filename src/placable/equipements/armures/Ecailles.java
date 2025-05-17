package placable.equipements.armures;

import des.Des;
import donjons.Donjon;
import placable.entites.personnages.Personnage;

public class Ecailles extends Legeres{
    public Ecailles() {
        super(9);
        this.m_nom = "armure d'écailles";

    }

    public Ecailles(int x, int y) {
        super(9, x, y);
        this.m_nom = "armure d'écailles";

    }

}
