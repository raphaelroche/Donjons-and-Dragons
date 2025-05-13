package placable.equipements.armes;

import des.Des;
import donjons.Donjon;
import placable.entites.personnages.Personnage;

public class EpeeLongue extends Guerre{
    public EpeeLongue(Personnage p) {
        this.m_nom = "épée longue";
        p.ajouterEquipementInventaire(this);
    }

    public EpeeLongue(int x, int y) {
        super(x, y);
        this.m_nom = "épée longue";

    }

    public EpeeLongue(Donjon d, Des des) {
        super(des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
        this.m_nom = "épée longue";

    }

    public EpeeLongue(Donjon d){
        this(d, new Des());
    }
}
