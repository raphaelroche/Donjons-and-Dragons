import des.Des;
import donjons.*;
import maitredujeu.MaitreDuJeu;
import placable.entites.monstres.Monstre;
import placable.entites.personnages.*;
import placable.equipements.armes.*;
import placable.equipements.armures.*;
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
    private int m_nbEquipements;

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

        if(this.m_nbObstacle > 0) {
            System.out.println("Vous avez choisi de placer " + this.m_nbObstacle + " obstacles");
        } else {
            System.out.println("Vous n'avez pas choisi d'obstacles");
        }
        int i;
        for (i = 0; i < this.m_nbObstacle; i++) {
            creerObstacleAleatoire(i);
        }

        this.m_nbEquipements = this.m_utils.demanderChoixOuParDefaut("Indiquez le nombre d'équipements",
                0, 10, 5,
                this.scanner);

        for (i = 0; i<this.m_nbEquipements; i++) {
            System.out.println("Choisissez l'équipement " + String.valueOf(i+1));
            int creationEquipement = this.m_utils.demanderChoixOuParDefaut("Voulez-vous créer un équipement manuellement ? 1 - Oui | 2 - Non",
                    1, 2, 2, scanner);


            if (creationEquipement == 1) {
                int type = this.m_utils.demanderChoix(scanner, "Choisissez un type d'équipement :\n1 - Armures\n2 - Armes",
                        1, 2);
                scanner.nextLine();
                int[] position = m_utils.demanderPositionCarte("Choisissez la position de l'équipement " + String.valueOf(i+1), 'A', this.m_d1.getLettreMax(),
                        1, this.m_d1.getHauteur(),
                        scanner);
                creerEquipement(type, i, position[0], position[1]);
            } else if (creationEquipement == 2) {
                System.out.println("Vous avez choisi de créer les équipement aléatoirement.");
                initEquipementAleatoire(i);
            }
        }

        for (i = 0; i < this.m_nbJoueurs; i++) {
            Personnage p = initJoueur(i + 1, this.m_d1);
            this.m_joueursEnVie.add(p);
            System.out.println("=================================================================================");
        }

        mdj.positionnerEntite(this.m_d1, new Monstre("dragonnnnn"));

        afficherEntites();

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
                "Choisissez une race :\n1 - Humain\n2 - Nain\n3 - Elfe\n4 - Halfelin",
                1, 4);
        scanner.nextLine();


        int classe = this.m_utils.demanderChoix(scanner,
                "Choisissez une classe :\n1 - Clerc\n2 - Guerrier\n3 - Magicien\n4 - Roublard",
                1, 4);
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

    public void creerObstacleAleatoire(int i) {
        int[] position = this.m_utils.demanderPositionCarte("Choisissez la position de l'obstacle " + String.valueOf(i+1),
                'A', this.m_d1.getLettreMax(),
                1, this.m_d1.getHauteur(),
                scanner);
        if (position[0] == -1 || position[1] == -1) {
            System.out.println("Obstacle aléatoirement positionné\n");
            mdj.positionnerObstacle(this.m_d1, this.m_utils.creerObstacleAleatoire(this.m_d1));
        }
        else {
            mdj.positionnerObstacle(this.m_d1, new Obstacle(position[0], position[1]));
            System.out.println("Obstacle positionné en " + alphabet[position[1]-1] + String.valueOf(position[0]));
        }
    }

    public void initEquipementAleatoire(int i) {
        System.out.println("Equipement aléatoirement positionné\n");
        mdj.positionnerEquipement(this.m_d1, this.m_utils.creerEquipementAleatoire(this.m_d1));
    }

    public void creerEquipement(int type, int i, int x, int y) {

        if (type == 1) {
            int typeArmure = this.m_utils.demanderChoix(scanner,
                    "Choisissez un type d'armure :\n1 - Cotte de mailles\n2 - Demi-plaque\n3 - Ecailles\n4 - Harnois",
                    1, 4);
            switch (typeArmure) {
                case 1 -> mdj.positionnerEquipement(this.m_d1, new CotteDeMailles(x, y));
                case 2 -> mdj.positionnerEquipement(this.m_d1, new DemiPlatte(x, y));
                case 3 -> mdj.positionnerEquipement(this.m_d1, new Ecailles(x, y));
                case 4 -> mdj.positionnerEquipement(this.m_d1, new Harnois(x, y));
            }
        } else {
            int typeArme = this.m_utils.demanderChoix(scanner,
                    "Choisissez un type d'arme : \n1 - Arbalète\n2 - Arc\n3 - Baton\n4 - Epee longue\n5 - Fronde\n6 - Masse\n7 - Rapière",
                    1, 7);
            switch (typeArme) {
                case 1 -> mdj.positionnerEquipement(this.m_d1, new Arbalete(x, y));
                case 2 -> mdj.positionnerEquipement(this.m_d1, new Arc(x, y));
                case 3 -> mdj.positionnerEquipement(this.m_d1, new Baton(x, y));
                case 4 -> mdj.positionnerEquipement(this.m_d1, new EpeeLongue(x, y));
                case 5 -> mdj.positionnerEquipement(this.m_d1, new Fronde(x, y));
                case 6 -> mdj.positionnerEquipement(this.m_d1, new Masse(x, y));
                case 7 -> mdj.positionnerEquipement(this.m_d1, new Rapiere(x, y));
            }
        }
    }

    public void afficherEntites() {
        for (Personnage p : this.m_joueursEnVie) {
            System.out.println("\t" + p.getNomAffiche() + " " + p.getNom() +
                    " (" + p.getNomRace() + " " + p.getNomClasse() + ", " + p.getPv() + ")");
        }
        if (this.m_d1.getListeMonstre() != null) {
            for (Monstre m : this.m_d1.getListeMonstre()) {
                System.out.println(m.getNomAffiche() + " " + m.getEspece() +
                        " (" + m.getPv() + ")");
            }
        }
        System.out.println();

    }
}
