package placable.equipements.armures;

import des.Des;
import donjons.Donjon;
import placable.entites.personnages.Personnage;

public class Ecailles extends Legeres{
    public Ecailles(Personnage p) {
        super(9);
        this.m_nom = "armure d'écailles";
        p.ajouterEquipementInventaire(this);
    }

    public Ecailles(int x, int y) {
        super(9, x, y);
        this.m_nom = "armure d'écailles";

    }

    public Ecailles(Donjon d, Des des){
        super(9,des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
        this.m_nom = "armure d'écailles";


    }
    public Ecailles(Donjon d){
        this(d, new Des());
    }
}
