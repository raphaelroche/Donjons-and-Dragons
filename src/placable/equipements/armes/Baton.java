package placable.equipements.armes;

import des.Des;
import donjons.Donjon;

public class Baton extends Courantes{
    public Baton() {
        this.m_nom = "bâton";
    }

    public Baton(int x, int y) {
        super(x, y);
        this.m_nom = "bâton";

    }

    public Baton(Donjon d, Des des) {
        super(des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
        this.m_nom = "bâton";

    }
    public Baton(Donjon d){
        this(d, new Des());
    }


}
