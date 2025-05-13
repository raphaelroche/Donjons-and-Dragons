package placable.equipements.armes;

import donjons.Donjon;

public class Masse extends Courantes{
    public Masse() {
        this.m_nom = "masse d'armes";
    }

    public Masse(int x, int y) {
        super(x,y);
        this.m_nom = "masse d'armes";

    }

    public Masse(Donjon d){
        this.m_nom = "masse d'armes";
        this.setLocation(des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
    }
}
