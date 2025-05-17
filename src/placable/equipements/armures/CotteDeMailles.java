package placable.equipements.armures;

import des.Des;
import donjons.Donjon;
import placable.entites.personnages.Personnage;

public class CotteDeMailles extends Lourdes{
    public CotteDeMailles() {
        super(11);
        this.m_nom = "cotte de mailles";

    }

    public CotteDeMailles(int x, int y){
        super(11, x, y);
        this.m_nom = "cotte de mailles";
    }


}
