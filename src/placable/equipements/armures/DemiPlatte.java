package placable.equipements.armures;

import donjons.Donjon;

public class DemiPlatte extends Legeres{
    public DemiPlatte() {
        super(10);
        this.m_nom = "demi-platte";
    }

    public DemiPlatte(int x, int y){
        super(10,x, y);
        this.m_nom = "demi-platte";
    }

    public DemiPlatte(Donjon d){
        super(10);
        this.setLocation(des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
        this.m_nom = "demi-platte";
    }
}
