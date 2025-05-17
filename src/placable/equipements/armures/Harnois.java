package placable.equipements.armures;

import des.Des;
import donjons.Donjon;
import placable.entites.personnages.Personnage;

public class Harnois extends Lourdes{
    public Harnois() {
        super(12);
        this.m_nom = "harnois";

    }

    public Harnois(int x, int y){
        super(12, x, y);
        this.m_nom = "harnois";
    }

}
