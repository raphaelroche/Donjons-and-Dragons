package placable.equipements.armures;

import des.Des;
import donjons.Donjon;
import placable.entites.personnages.Personnage;

public class CotteDeMailles extends Lourdes{
    public CotteDeMailles(Personnage p) {
        super(11);
        this.m_nom = "cotte de mailles";
        p.ajouterEquipementInventaire(this);
    }

    public CotteDeMailles(int x, int y){
        super(11, x, y);
        this.m_nom = "cotte de mailles";
    }

    public CotteDeMailles(Donjon d, Des des){
        super(11,des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
        this.m_nom = "cotte de mailles";
    }

    public CotteDeMailles(Donjon d){
        this(d, new Des());
    }

}
