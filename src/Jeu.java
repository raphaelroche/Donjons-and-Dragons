import des.Des;
import donjons.*;
import maitredujeu.MaitreDuJeu;
import placable.entites.Entite;
import placable.entites.monstres.Monstre;
import placable.entites.personnages.*;
import placable.equipements.Equipement;
import placable.equipements.armes.*;
import placable.equipements.armures.*;
import placable.obstacle.Obstacle;
import utils.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    private ArrayList<Personnage> m_joueursEnVie;
    private ArrayList<Monstre> m_monstresEnVie;
    private ArrayList<Entite> m_entitesEnVie;
    private Utils m_utils;
    private int m_nbJoueurs;
    private Scanner scanner;
    private MaitreDuJeu mdj;
    private Des m_des;
    private String[] alphabet;
    private int m_nbObstacle;
    private int m_nbEquipements;
    private int m_nb_monstres;

    public Jeu() {
        scanner = new Scanner(System.in);
        mdj = new MaitreDuJeu();
        m_des = new Des();
        alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};
        this.m_joueursEnVie = new ArrayList<>();
        this.m_monstresEnVie = new ArrayList<>();
        this.m_entitesEnVie = new ArrayList<>();
    }

    public void demarrerJeu() {
        System.out.println("Bienvenue dans DOOnjon et Dragons");
        m_utils = new Utils();
        m_nbJoueurs = m_utils.demanderChoix(this.scanner, "Indiquez le nombre de joueurs (entre 1 et 5) : ", 1, 5);

        scanner.nextLine();

        int i;

        for (i = 0; i < 3; i ++) {
            System.out.println("================================== DONJON " + (i+1) + " ===================================");


            Donjon d = initDonjon();

            for (int j = 0; j < this.m_nbJoueurs; j++) {
                Personnage p = initJoueur(j + 1, d);
                this.m_joueursEnVie.add(p);
                m_entitesEnVie.add(p);
                System.out.println("================================================================================");
            }



            afficherEntites(d);
            d.afficherDonjon();
            deroulePartie(d);
            System.out.println("============================== FIN DU DONJON " + (i+1) + " =================================\n\n");
            m_monstresEnVie.clear();

        }
    }

    public Donjon initDonjon() {
        int hauteurD = this.m_utils.demanderChoixOuParDefaut("Entrez la hauteur du tableau", 15, 25, 15, scanner);
        int largeurD = this.m_utils.demanderChoixOuParDefaut("Entrez la largeur du tableau", 15, 25, 15, scanner);

        Donjon d = new Donjon(hauteurD, largeurD);

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
            creerObstacleAleatoire(i, d);
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

                creerEquipement(type, i, d);
            } else if (creationEquipement == 2) {
                System.out.println("Vous avez choisi de créer les équipement aléatoirement.");
                initEquipementAleatoire(i, d);
            }
        }
        this.m_nb_monstres = this.m_utils.demanderChoixOuParDefaut("Indiquez le nombre de monstres",
                1, 5, 2,
                this.scanner);

        System.out.println("Vous avez choisi de placer " + this.m_nb_monstres + " monstres");


        for (i = 0; i < this.m_nb_monstres; i++) {
            creerMonstres(i, d);
        }

        return d;
    }

    public void deroulePartie(Donjon d) {
        for (Personnage p : this.m_joueursEnVie) {
            System.out.println(p);
            //demande d'equipé une arme et une armure

        }
        while(this.m_joueursEnVie.size() == this.m_nbJoueurs && !this.m_monstresEnVie.isEmpty()) {
            for(Entite e : this.m_monstresEnVie) {
                System.out.println("Au tour de "+e.getIdentificationEntite());
                for(int i = 0; i<3;i++){
                    demanderAction(e, d);
                }
            }
        }
    }

    public void demanderAction(Entite e, Donjon d) {
        int choixAction;
        StringBuilder message = new StringBuilder("choississez votre action : \n0 - passer votre tour \n1 - se deplacer\n2 - attaquer");
        if(e.estMonstre()){

            choixAction = m_utils.demanderChoix(scanner,
                    message.toString(),
                    0, 2);

        }
        else{

            if(((Personnage) e).estClerc() || ((Personnage) e).estMagicien()){
                message.append("\n3 - jeter un sort");
            }
            if(d.getCarte()[e.getPositionX()][e.getPositionY()].get(1).estEquipement()) {
                //arranger ca dans donjon
                choixAction = m_utils.demanderChoix(scanner,
                        message.toString(),
                        0, 4);
            }
            else{
                choixAction = m_utils.demanderChoix(scanner,
                        message.toString(),
                        0, 3);
            }
        }
        faireActionEntite(choixAction);
    }

    public void faireActionEntite(int choix){

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
        Personnage p;
        if (position[0] == -1 || position[1] == -1) {
            p = m_utils.creerPersonnageAleatoire(nom, race, classe, d);
            peutSePlacer = mdj.positionnerEntite(d, p);
            while (!peutSePlacer) {
                p.setLocation(m_des.lancerDes(1, d.getHauteur() - 1),
                        (m_des.lancerDes(1, d.getLargeur() - 1)));
                peutSePlacer = mdj.positionnerEntite(d, p);
            }
            System.out.println(p.getNom() + " a été placé en " + alphabet[p.getPositionY()] + String.valueOf(p.getPositionX() + 1));
        } else {
            p = new Personnage(nom, race, classe, position[0], position[1]);
            peutSePlacer = mdj.positionnerEntite(d, p);
            while (!peutSePlacer) {
                position = this.m_utils.demanderPositionCarte("Il y a un élément sur cette case, rechoisissez la position du joueur",
                        'A', d.getLettreMax(),
                        1, d.getHauteur(),
                        scanner);
                p.setLocation(position[0] - 1, position[1] - 1);
                peutSePlacer = mdj.positionnerEntite(d, p);
            }
        }
        return p;
    }

    public void creerObstacleAleatoire(int i, Donjon d) {
        int[] position = this.m_utils.demanderPositionCarte("Choisissez la position de l'obstacle " + String.valueOf(i+1),
                'A', d.getLettreMax(),
                1, d.getHauteur(),
                scanner);
        if (position[0] == -1 || position[1] == -1) {
            Obstacle o = this.m_utils.creerObstacleAleatoire(d);
            boolean peutSePlacer = mdj.positionnerObstacle(d, o);
            while(!peutSePlacer) {
                o.setLocation(m_des.lancerDes(1, d.getHauteur() - 1),
                        (m_des.lancerDes(1, d.getLargeur() - 1)));

                peutSePlacer = mdj.positionnerObstacle(d, o);
            }
            System.out.println("Obstacle aléatoirement positionné\n");
        }
        else {
            Obstacle o = new Obstacle(position[0], position[1]);
            boolean peutSePlacer = mdj.positionnerObstacle(d, o);
            while(!peutSePlacer) {
                position = this.m_utils.demanderPositionCarte("Il y a un élément sur cette case, rechoisissez la position de l'obstacle",
                        'A', d.getLettreMax(),
                        1, d.getHauteur(),
                        scanner);
                o.setLocation(position[0]-1,position[1]-1);
                peutSePlacer = mdj.positionnerObstacle(d, o);
            }
                System.out.println("Obstacle positionné en " + alphabet[position[1]-1] + String.valueOf(position[0]));


        }
    }

    public void initEquipementAleatoire(int i, Donjon d) {

        Equipement e = this.m_utils.creerEquipementAleatoire(d);
        boolean peutSePlacer = mdj.positionnerEquipement(d, e);
        while(!peutSePlacer){
            e.setLocation(m_des.lancerDes(1, d.getHauteur() - 1),
                    (m_des.lancerDes(1, d.getLargeur() - 1)));
           peutSePlacer =  mdj.positionnerEquipement(d, e);
        }
        System.out.println("Equipement aléatoirement positionné\n");

    }
    public void creerMonstres(int i, Donjon d){
        Monstre m = null;
        int choix = 0;
        while(choix != 1 && choix != 2){
            System.out.println(" 1 - creer le monstre automatiquement\n 2 - creer le monstre manuellement");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine(); // consomme le retour à la ligne
                if (choix != 1 && choix != 2) {
                    System.out.println("Veuillez entrer 1 ou 2 uniquement.");
                }
            } else {
                System.out.println("Entrée invalide, un entier (1 ou 2) est requis.");
                scanner.nextLine(); // consomme l'entrée invalide
            }
        }

        switch (choix) {

            case 1: {
                m = this.m_utils.creerMonstreAleatoire(d);
                boolean peutSePlacer = mdj.positionnerEntite(d, m);
                while (!peutSePlacer) {
                    m.setLocation(m_des.lancerDes(1, d.getHauteur() - 1),
                            (m_des.lancerDes(1, d.getLargeur() - 1)));

                    peutSePlacer = mdj.positionnerEntite(d, m);
                }
                System.out.println("Monstre aléatoirement positionné\n");

                break;
            }
            case 2: {
                String espece = "";
                while (espece.isBlank() || !espece.matches("[a-zA-Z]+")) {
                    System.out.println("Donnez l'espece du monstre : ");
                    espece = scanner.nextLine();
                }
                int portee = 0;
                while (portee <= 0) {
                    System.out.println("Donnez sa portée : ");
                    if (scanner.hasNextInt()) {
                        portee = scanner.nextInt();
                        scanner.nextLine(); // consomme le \n
                        if (portee <= 0) {
                            System.out.println("La portée doit être un entier strictement positif !");
                        }
                    } else {
                        System.out.println("Entrée invalide, entier requis !");
                        scanner.nextLine(); // consomme l'entrée invalide
                    }
                }


                int pv = 0;
                while (pv <= 0) {
                    System.out.println("Donnez son nombre de pv max : ");
                    if (scanner.hasNextInt()) {
                        pv = scanner.nextInt();
                        scanner.nextLine(); // consomme le \n
                        if (pv <= 0) {
                            System.out.println("Le nombre de PV doit être > 0");
                        }
                    } else {
                        System.out.println("Entrée invalide, entier requis !");
                        scanner.nextLine(); // consomme l'entrée invalide
                    }
                }
                String attaque = "";
                while (attaque.isBlank() || !attaque.matches("[a-zA-Z]+")) {
                    System.out.println("Donnez le nom de l'attaque du monstre : ");
                    attaque = scanner.nextLine();

                }
                int armure = 0;
                while (armure <= 0) {
                    System.out.println("Donnez sa classe d'armure : ");
                    if (scanner.hasNextInt()) {
                        armure = scanner.nextInt();
                        scanner.nextLine(); // consomme le \n
                        if (armure <= 0) {
                            System.out.println("L'armure doit être un entier strictement positif.");
                        }
                    } else {
                        System.out.println("Entrée invalide, entier requis !");
                        scanner.nextLine(); // consomme l'entrée invalide
                    }
                }

                int force = 0;
                while (force <= 0) {
                    System.out.println("Donnez sa force : ");
                    if (scanner.hasNextInt()) {
                        force = scanner.nextInt();
                        scanner.nextLine(); // consomme le \n
                        if (force <= 0) {
                            System.out.println("La force doit être un entier strictement positif !");
                        }
                    } else {
                        System.out.println("Entrée invalide, entier requis !");
                        scanner.nextLine(); // consomme l'entrée invalide
                    }
                }

                int dexterite = 0;
                while (dexterite <= 0) {
                    System.out.println("Donnez sa dexterite : ");
                    if (scanner.hasNextInt()) {
                        dexterite = scanner.nextInt();
                        scanner.nextLine();
                        if (dexterite <= 0) {
                            System.out.println("La dextérité doit être un entier strictement positif !");
                        }
                    } else {
                        System.out.println("Entrée invalide, entier requis !");
                        scanner.nextLine();
                    }
                }

                int initiative = 0;
                while (initiative <= 0) {
                    System.out.println("Donnez son initiative : ");
                    if (scanner.hasNextInt()) {
                        initiative = scanner.nextInt();
                        scanner.nextLine();
                        if (initiative <= 0) {
                            System.out.println("L'initiative doit être un entier strictement positif !");
                        }
                    } else {
                        System.out.println("Entrée invalide, entier requis !");
                        scanner.nextLine();
                    }
                }
                int[] position = this.m_utils.demanderPositionCarte("Choisissez la position du Monstre " + String.valueOf(i + 1),
                        'A', d.getLettreMax(),
                        1, d.getHauteur(),
                        scanner);
                m = new Monstre(espece, portee, pv, attaque, armure, force, dexterite, initiative, position[0], position[1]);
                boolean peutSePlacer = mdj.positionnerEntite(d, m);
                while (!peutSePlacer) {
                    position = this.m_utils.demanderPositionCarte("Il y a un élément sur cette case, rechoisissez la position du Monstre",
                            'A', d.getLettreMax(),
                            1, d.getHauteur(),
                            scanner);
                    m.setLocation(position[0] - 1, position[1] - 1);
                    peutSePlacer = mdj.positionnerEntite(d, m);
                }
                System.out.println("Monstre positionné en " + alphabet[position[1] - 1] + String.valueOf(position[0]));

                break;
            }
        }
        m_monstresEnVie.add(m);
        m_entitesEnVie.add(m);
    }

    public void creerEquipement(int type, int i, Donjon d) {
        Equipement e = null;
        if (type == 1) {

            int typeArmure = this.m_utils.demanderChoix(scanner,
                    "Choisissez un type d'armure :\n1 - Cotte de mailles\n2 - Demi-plaque\n3 - Ecailles\n4 - Harnois",
                    1, 4);
            scanner.nextLine();
            switch (typeArmure) {
                case 1 -> e = new CotteDeMailles();
                case 2 -> e =  new DemiPlatte();
                case 3 -> e=  new Ecailles();
                case 4 -> e =  new Harnois();
            }
        } else {
            int typeArme = this.m_utils.demanderChoix(scanner,
                    "Choisissez un type d'arme : \n1 - Arbalète\n2 - Arc\n3 - Baton\n4 - Epee longue\n5 - Fronde\n6 - Masse\n7 - Rapière\n8 - Epée 2 mains",
                    1, 7);
            scanner.nextLine();
            switch (typeArme) {
                case 1 -> e = new Arbalete();
                case 2 -> e = new Arc();
                case 3 -> e =  new Baton();
                case 4 -> e = new EpeeLongue();
                case 5 -> e = new Fronde();
                case 6 -> e = new Masse();
                case 7 -> e = new Rapiere();
                case 8 -> e = new EpeeDeuxMain();
            }
        }
        int[] position = this.m_utils.demanderPositionCarte("Choisissez la position de l'équipement " + String.valueOf(i+1), 'A', d.getLettreMax(),
                1, d.getHauteur(),
                scanner);
        boolean peutSePlacer = false;
        while(!peutSePlacer){


            assert e != null;
            e.setLocation(position[0]-1, position[1]-1);
            peutSePlacer = mdj.positionnerEquipement(d, e);
            if(!peutSePlacer){
                position = this.m_utils.demanderPositionCarte("Il y a un élément sur cette case, rechoisissez la position de l'equipement",
                        'A', d.getLettreMax(),
                        1, d.getHauteur(),
                        scanner);
            }

        }
    }

    public void afficherEntites(Donjon d) {
        for (Personnage p : this.m_joueursEnVie) {
            System.out.println("\t" + p.getNomAffiche() + " " + p.getNom() +
                    " (" + p.getNomRace() + " " + p.getNomClasse() + ", " + p.getPv() + "/" + p.getPvMax() + ")");
        }
        if (d.getListeMonstre() != null) {
            for (Monstre m : d.getListeMonstre()) {
                System.out.println(m.getNomAffiche() + " " + m.getEspece() +
                        " (" + m.getPv() + "/" + m.getPvMax() + ")");
            }
        }
        System.out.println();

    }


}
