import des.Des;
import donjons.*;
import maitredujeu.MaitreDuJeu;
import placable.Placable;
import placable.entites.Entite;
import placable.entites.personnages.Personnage;
import placable.obstacle.Obstacle;
import utils.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    private ArrayList<Personnage> m_joueursEnVie;
    private Utils m_utils;
    private int m_nbJoueurs;
    private Scanner scanner;
    private Donjon m_d1;
    private MaitreDuJeu mdj;
    private Des m_des;
    private String[] alphabet;
    private int m_nbObstacle;

    public Jeu() {
        scanner = new Scanner(System.in);
        mdj = new MaitreDuJeu();
        m_des = new Des();
        alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};
        this.m_joueursEnVie = new ArrayList<>();
    }

    public void demarrerJeu() {
        System.out.println("Bienvenue dans DOOnjon et Dragons");
        m_utils = new Utils();
        m_nbJoueurs = m_utils.demanderChoix(this.scanner, "Indiquez le nombre de joueurs (entre 1 et 5) : ", 1, 5);

        scanner.nextLine();

        int hauteurD = this.m_utils.demanderChoixOuParDefaut("Entrez la hauteur du tableau", 15, 25, 15, scanner);
        int largeurD = this.m_utils.demanderChoixOuParDefaut("Entrez la largeur du tableau", 15, 25, 15, scanner);

        this.m_d1 = new Donjon(hauteurD, largeurD);

        this.m_nbObstacle = this.m_utils.demanderChoixOuParDefaut("Indiquez le nombre d'obstacle",
                0, 10, 5,
                this.scanner);

        System.out.println("Vous allez maintenant placer les obstacles sur la carte");
        int i;
        for (i = 0; i < this.m_nbObstacle; i++) {
            int[] position = this.m_utils.demanderPositionCarte("Choisissez la position de l'obstacle " + String.valueOf(i+1),
                    'A', this.m_d1.getLettreMax(),
                    1, this.m_d1.getHauteur(),
                    scanner);
            mdj.postionnerObstacle(m_d1, new Obstacle(position[0], position[1]));
        }

        for (i = 0; i < this.m_nbJoueurs; i++) {
            this.m_joueursEnVie.add(initJoueur(i + 1, this.m_d1));
            System.out.println("=================================================================================");
        }
        this.m_d1.afficherDonjon();
    }

    public Personnage initJoueur(int nJoueur, Donjon d) {
        String nom = "";
        boolean nomValide = false;
        while (!nomValide) {
            System.out.println("Choisissez un nom pour le joueur " + String.valueOf(nJoueur) + " :");
            nom = scanner.nextLine();

            if (nom.isBlank()) {
                System.out.println("Le nom ne peut pas être vide ou composé uniquement d'espaces.");
                continue;
            }

            boolean nomDejaPris = false;
            for (Personnage p : this.m_joueursEnVie) {
                if (p.getNom().equalsIgnoreCase(nom)) {
                    nomDejaPris = true;
                    break;
                }
            }

            if (nomDejaPris) {
                System.out.println("Ce nom est déjà pris par un joueur. Choisissez-en un autre.");
            } else {
                nomValide = true;
            }
        }

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
            System.out.println(p.getNom() + " a été placé en " + alphabet[p.getPositionY()] + String.valueOf(p.getPositionX() + 1));
            return p;
        } else {
            Personnage p = new Personnage(nom, race, classe, position[0], position[1]);
            peutSePlacer = mdj.positionnerEntite(this.m_d1, p);
            while (!peutSePlacer) {
                position = this.m_utils.demanderPositionCarte("Il y a un élément sur cette case, rechoisissez la position du joueur",
                        'A', d.getLettreMax(),
                        1, d.getHauteur(),
                        scanner);
                p.setLocation(position[0] - 1, position[1] - 1);
                peutSePlacer = mdj.positionnerEntite(this.m_d1, p);
            }
            return p;
        }
    }
}
