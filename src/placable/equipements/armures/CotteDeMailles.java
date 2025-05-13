package placable.equipements.armures;

import des.Des;
import donjons.Donjon;

public class CotteDeMailles extends Lourdes{
    public CotteDeMailles() {
        super(11);
        this.m_nom = "cotte de mailles";
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
