package placable.equipements.armes;

import donjons.Donjon;

public class Baton extends Courantes{
    public Baton() {
        this.m_nom = "bâton";
    }

    public Baton(int x, int y) {
        super(x, y);
        this.m_nom = "bâton";

    }

    public Baton(Donjon d) {
        this.m_nom = "bâton";
        this.setLocation(des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
    }


}
