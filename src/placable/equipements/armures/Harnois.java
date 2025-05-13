package placable.equipements.armures;

import des.Des;
import donjons.Donjon;

public class Harnois extends Lourdes{
    public Harnois() {
        super(12);
        this.m_nom = "harnois";
    }

    public Harnois(int x, int y){
        super(12, x, y);
        this.m_nom = "harnois";
    }

    public Harnois(Donjon d, Des des){
        super(12,des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
        this.m_nom = "harnois";
    }
    public Harnois(Donjon d){
        this(d, new Des());
    }
}
