package placable.equipements.armures;

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

    public CotteDeMailles(Donjon d){
        super(11);
        this.m_nom = "cotte de mailles";
        this.setLocation(des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
    }


}
