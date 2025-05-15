import des.Des;
import donjons.*;
import maitredujeu.MaitreDuJeu;
import placable.entites.personnages.Personnage;
import placable.obstacle.Obstacle;
import utils.*;

import java.util.Scanner;

public class Jeu {
    private Personnage[] m_enVie;
    private Utils m_utils;
    private int m_nbJoueurs;
    private Scanner scanner;
    private Donjon m_d1;
    private MaitreDuJeu mdj;
    private Des m_des;
    private String[] alphabet;

    public Jeu() {
        scanner = new Scanner(System.in);
        mdj = new MaitreDuJeu();
        m_des = new Des();
        alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};
    }

    public void demarrerJeu() {
        System.out.println("Bienvenue dans DOOnjon et Dragons");
        m_utils = new Utils();
        m_nbJoueurs = m_utils.demanderChoix(this.scanner, "Indiquez le nombre de joueurs (entre 1 et 5) : ", 1, 5);

        scanner.nextLine();

        int hauteurD = this.m_utils.demanderChoixOuParDefaut("Entrez la hauteur du tableau", 15, 25, 15, scanner);
        int largeurD = this.m_utils.demanderChoixOuParDefaut("Entrez la largeur du tableau", 15, 25, 15, scanner);

        m_d1 = new Donjon(hauteurD, largeurD);
        mdj.postionnerObstacle(this.m_d1, new Obstacle(1, 1));

        for (int i = 0; i < this.m_nbJoueurs; i++) {
            initJoueur(i+1, this.m_d1);
            System.out.println("=================================================================================");
        }



        m_d1.afficherDonjon();
    }

    public void initJoueur(int nJoueur, Donjon d) {
        System.out.println("Choisissez un nom pour le joueur " + String.valueOf(nJoueur) + " :");
        String nom = scanner.nextLine();

        int race = this.m_utils.demanderChoix(scanner,
                "Choisissez une race :\n1 - Humain\n2 - Nain\n3 - Elfe\n4 - Halfelin", 1, 4);

        scanner.nextLine();

        int classe = this.m_utils.demanderChoix(scanner,
                "Choisissez une classe :\n1 - Clerc\n2 - Guerrier\n3 - Magicien\n4 - Roublard", 1, 4);

        scanner.nextLine();


        int x = 0, y = 0;

        int[] position = this.m_utils.demanderPositionCarte("Choisissez la position du joueur",
                'A', d.getLettreMax(),
                1, d.getHauteur(),
                scanner);

        boolean peutSePlacer = false;
        if (position[0] == -1 || position[1] == -1) {
            Personnage p = new Personnage(nom, race, classe, m_d1);
            while (!peutSePlacer) {
                p.setLocation(m_des.lancerDes(1, this.m_d1.getHauteur() - 1),
                        (m_des.lancerDes(1, this.m_d1.getLargeur() - 1)));
                peutSePlacer = mdj.positionnerEntite(this.m_d1, p);
            }
            System.out.println(p.getNom() + " a été placé en " + alphabet[p.getPositionY()] + String.valueOf(p.getPositionX()+1));
        }
        else {
            Personnage p = new Personnage(nom, race, classe, position[0], position[1]);
            peutSePlacer = mdj.positionnerEntite(this.m_d1,p);
            while(!peutSePlacer) {
                position = this.m_utils.demanderPositionCarte("Il y a un élément sur cette case, rechoisissez la position du joueur",
                        'A', d.getLettreMax(),
                        1, d.getHauteur(),
                        scanner);
                p.setLocation(position[0]-1, position[1]-1);
                peutSePlacer = mdj.positionnerEntite(this.m_d1,p);
            }
        }
    }
}
