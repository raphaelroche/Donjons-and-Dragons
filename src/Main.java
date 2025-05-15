import donjons.Donjon;
import maitredujeu.MaitreDuJeu;
import placable.entites.monstres.Monstre;
import placable.entites.personnages.Personnage;
import placable.equipements.armes.*;
import placable.obstacle.Obstacle;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Jeu jeu = new Jeu();
        jeu.demarrerJeu();
    }
}