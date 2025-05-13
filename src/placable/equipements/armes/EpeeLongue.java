package placable.equipements.armes;

import donjons.Donjon;

public class EpeeLongue extends Guerre{
    public EpeeLongue() {
        this.m_nom = "épée longue";
    }

    public EpeeLongue(int x, int y) {
        super(x, y);
        this.m_nom = "épée longue";

    }

    public EpeeLongue(Donjon d) {
        this.m_nom = "épée longue";
        this.setLocation(des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
    }
}
