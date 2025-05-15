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

    public boolean postionnerObstacle(Donjon d, Obstacle o){return d.positionnerElementCarte(o);}

    public boolean positionnerEntite(Donjon d, Entite e){return d.positionnerElementCarte(e);}

    public boolean positionnerEquipement(Donjon d, Equipement e){
        return d.positionnerElementCarte(e);
    }



}
