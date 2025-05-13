package maitredujeu;

import donjons.Donjon;
import placable.entites.Entite;
import placable.equipements.Equipement;
import placable.obstacle.Obstacle;

public class MaitreDuJeu {

    public MaitreDuJeu(){
        String m_nom = "Maitre Du Jeu";
    }

    public void creerDonjon(Donjon d, int longueur, int largeur){
        d = new Donjon(largeur, longueur);
    }

    public void postionnerObstacle(Donjon d, Obstacle o, int x, int y){
        o.setLocation(x, y);
        d.positionnerElementCarte(o);
    }

    public void positionnerEntite(Donjon d, Entite e, int x, int y){
        e.setLocation(x, y);
        d.positionnerElementCarte(e);
    }

    public void positionnerEquipement(Donjon d, Equipement e, int x, int y){
        e.setLocation(x, y);
        d.positionnerElementCarte(e);
    }

}
