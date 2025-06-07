package maitredujeu;

import donjons.Donjon;
import placable.Placable;
import placable.entites.Entite;
import placable.equipements.Equipement;
import placable.obstacle.Obstacle;

import java.util.ArrayList;

public class MaitreDuJeu {

    private final String m_nom;
    public MaitreDuJeu(){
        m_nom = "Maitre Du Jeu";
    }

    public boolean positionnerObstacle(Donjon d, Obstacle o){return d.positionnerElementCarte(o);}

    public boolean positionnerEntite(Donjon d, Entite e){return d.positionnerElementCarte(e);}

    public boolean positionnerEquipement(Donjon d, Equipement e){
        return d.positionnerElementCarte(e);
    }

    public void commenter(String message){
        System.out.println(this.m_nom + " - "+message);
    }

    public static void tuerCible(Donjon d, Entite e){
        int x = e.getPositionX();
        int y = e.getPositionY();
        ArrayList<Placable> caseCible = d.getCarte()[x][y];
        if(caseCible.get(1) == null){

            d.positionnerEmplacementVide(x, y);
        }
        else{
            d.decalerAGauche(caseCible);
        }

    }

}
