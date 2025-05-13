package placable.equipements.armes;

import des.Des;
import donjons.Donjon;
import placable.entites.personnages.Personnage;

public class Masse extends Courantes{
    public Masse(Personnage p) {
        this.m_nom = "masse d'armes";
        p.ajouterEquipementInventaire(this);
    }

    public Masse(int x, int y) {
        super(x,y);
        this.m_nom = "masse d'armes";

    }

    public Masse(Donjon d, Des des){
        super(des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
        this.m_nom = "masse d'armes";

    }

    public Masse(Donjon d){
        this(d, new Des());
    }
}
