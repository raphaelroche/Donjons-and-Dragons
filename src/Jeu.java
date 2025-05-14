import donjons.*;
import maitredujeu.MaitreDuJeu;
import placable.entites.personnages.Personnage;
import utils.*;

import java.util.Scanner;

public class Jeu {
    private Personnage[] m_enVie;
    private Utils m_utils;
    private int m_nbJoueurs;
    private Scanner scanner;
    private Donjon m_d1;
    private MaitreDuJeu mdj;

    public Jeu() {
        scanner = new Scanner(System.in);
        mdj = new MaitreDuJeu();
    }

    public void demarrerJeu() {
        System.out.println("Bienvenue dans DOOnjon et Dragons");
        m_utils = new Utils();
        m_nbJoueurs = m_utils.demanderChoix(this.scanner, "Indiquez le nombre de joueurs (entre 1 et 5) : ", 1, 5);

        scanner.nextLine();

        int hauteurD = this.m_utils.demanderChoixOuParDefaut("Entrez la hauteur du tableau", 15, 25, 15, scanner);
        int largeurD = this.m_utils.demanderChoixOuParDefaut("Entrez la largeur du tableau", 15, 25, 15, scanner);

        m_d1 = new Donjon(hauteurD, largeurD);

        for (int i = 0; i < this.m_nbJoueurs; i++) {
            initJoueur(i+1, this.m_d1);
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

        if (position[0] == -1 || position[1] == -1) {
            Personnage p = new Personnage(nom, race, classe, m_d1);
            mdj.positionnerEntite(this.m_d1,p);

        }
        else {
            Personnage p = new Personnage(nom, race, classe, position[0], position[1]);
            mdj.positionnerEntite(this.m_d1,p);

        }
    }
}
