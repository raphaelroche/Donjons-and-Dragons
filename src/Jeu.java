import des.Des;
import donjons.*;
import exception.ArmureException;
import exception.PorteeException;
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
    private ArrayList<Entite> m_entites;
    private ArrayList<Personnage> m_joueur;
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
        this.m_entites = new ArrayList<>();
        this.m_joueur = new ArrayList<>();
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
                if(i == 0){
                    Personnage p = initJoueur(j + 1, d);
                    this.m_joueur.add(p);
                    System.out.println("================================================================================");

                }
                else{
                    for(Personnage p : this.m_joueur){
                        placerPersonnage(p, d);
                        this.demanderEquipement(p);
                    }
                }

            }
            this.m_joueursEnVie.addAll(this.m_joueur);
            this.m_entites.addAll(this.m_joueur);
            this.m_entites.addAll(this.m_monstresEnVie);



            afficherEntites();
            d.afficherDonjon();
            System.out.println();
            deroulePartie(d);
            System.out.println("============================== FIN DU DONJON " + (i+1) + " =================================\n\n");
            this.m_monstresEnVie.clear();
            this.m_entites.clear();
            this.m_joueursEnVie.clear();

        }
    }

    public Donjon initDonjon() {

        int largeurD = this.m_utils.demanderChoixOuParDefaut("Entrez la largeur du tableau", 15, 25, 15, scanner);
        int hauteurD = this.m_utils.demanderChoixOuParDefaut("Entrez la hauteur du tableau", 15, 25, 15, scanner);

        Donjon d = new Donjon(largeurD,hauteurD);

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
                initEquipementAleatoire(d);
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
        }
        for(Entite e : this.m_entites) {
            e.scorePourCommencer();
            this.mdj.commenter("le score d'initiative ajouté a 1d20 de "+e.getIdentificationEntite()+" est de : "+e.getScoreInitiative());
        }

        this.m_entites.sort((e1, e2) ->
                Integer.compare(e2.getScoreInitiative(), e1.getScoreInitiative()));
        quitterwhile:
        while(this.m_joueursEnVie.size() == this.m_nbJoueurs && !this.m_monstresEnVie.isEmpty()) {
            for(Entite e : this.m_entites) {
                if((e.estMonstre() && this.m_monstresEnVie.contains((Monstre)e)) || (e.estPerso() && this.m_joueursEnVie.contains((Personnage)e))) {
                    System.out.println("Au tour de "+e.getIdentificationEntite());
                    for(int i = 0; i<3;i++){
                        if(this.m_joueursEnVie.size() != this.m_nbJoueurs || this.m_monstresEnVie.isEmpty()){
                            break quitterwhile;
                        }
                        int choix = demanderAction(e, d);
                        if(choix == 0) {
                            break;
                        }
                    }
                }
            }
        }
    }

    public int demanderAction(Entite e, Donjon d) {
        int choixAction;
        int nbchoix = 3;
        StringBuilder message = new StringBuilder("choississez votre action : \n0 - passer votre tour \n1 - se deplacer\n2 - attaquer");
        if(e.estMonstre()){

            choixAction = m_utils.demanderChoix(scanner,
                    message.toString(),
                    0, 2);
            scanner.nextLine();

        }
        else{
            message.append("\n3 - s'equiper \n4 - ramasser equipement (fonctionne uniquement si vous vous trouver sur une case d'equiupement)");
            nbchoix+=2;

            if(((Personnage) e).estClerc() || ((Personnage) e).estMagicien()){
                message.append("\n5 - jeter un sort");
                nbchoix++;
            }
            choixAction = m_utils.demanderChoix(scanner,
                    message.toString(),
                    0, nbchoix);
            scanner.nextLine();
        }
        faireActionEntite(e, choixAction, d, message.toString(),nbchoix);
        return choixAction;
    }

    public void faireActionEntite(Entite e, int choix, Donjon d ,String message, int nbchoix){
        boolean redemander = false;
        switch (choix) {
            case 0:
                this.mdj.commenter("Vous avez passer votre tour !");

                break;
            case 1:
               int direction = m_utils.demanderChoix(scanner,
                        "dans quelle direction voulez vous vous deplacer : \n1 - en haut \n2 - en bas \n3 - gauche \n4 - droite",
                        1, 4);
                scanner.nextLine();
                e.seDeplacer(direction, d);
                break;
            case 2:

                String nom;
                Entite cible;
                    int[] position = this.m_utils.demanderPositionCarteObligatoire("Quelle case voulez vous attaquer ?",
                            'A', d.getLettreMax(),
                            1, d.getHauteur(),
                            scanner);
                    if(d.getCarte()[position[0]-1][position[1]-1].getFirst().estEntite()){
                        nom = ((Entite)d.getCarte()[position[0]-1][position[1]-1].getFirst()).getIdentificationEntite();
                        cible = (Entite)d.getCarte()[position[0]-1][position[1]-1].getFirst();
                        int pv = 0;
                        try{
                            boolean attaque = e.attaquer(position[0],position[1], d);
                            if(!attaque){
                                this.mdj.commenter("Impossible d'attaquer, vous vous tromper de cible ou n'avez pas d'arme equipée !");
                                redemander = true;
                            }
                            else{

                                int degat = e.getDegats();
                                if(e.estMonstre()){
                                    this.mdj.commenter("Vous lancer "+((Monstre)e).getNomAttaque()+" et infligé "+degat+" dégat à "+nom);
                                }
                                else {
                                    this.mdj.commenter("Attaque reussi, vous avez infligé "+degat+" dégat à "+nom);
                                }

                                if(d.getCarte()[position[0]-1][position[1]-1].getFirst().estCaseVide()){
                                    this.mdj.commenter("Vous avez tuer "+nom);
                                    if(cible.estPerso()){
                                        this.m_joueursEnVie.remove((Personnage) cible);
                                    }
                                    else{
                                        this.m_monstresEnVie.remove((Monstre) cible);
                                    }
                                }
                            }

                        }
                        catch(ArmureException | PorteeException ex){
                            mdj.commenter("Impossible d'attaquer : "+ex.getMessage());
                        }


                    }



                break;
            case 3:
                StringBuilder arme = new StringBuilder("Quelle Equipement voulez vous equiper ?");
                for(int i = 0; i<((Personnage)e).getInventaire().size(); i++){
                    arme.append("\n").append(i+1).append(" - ").append(((Personnage) e).getInventaire().get(i).getNomEquipement());

                }



                int equipementChoisi = m_utils.demanderChoix(scanner,
                        arme.toString(),
                        1, ((Personnage)e).getInventaire().size());
                this.mdj.commenter("Vous venez d'equiper : "+((Personnage) e).getInventaire().get(equipementChoisi-1).getNomEquipement());

                ((Personnage)e).sEquiper(((Personnage) e).getInventaire().get(equipementChoisi-1));
                break;


            case 4:
                boolean ramasser = ((Personnage)e).ramasserEquipement((Equipement)d.getCarte()[e.getPositionX()][e.getPositionY()].get(1), d.getCarte());
                if(ramasser){
                    this.mdj.commenter("vous avez ramasser "+((Personnage)e).getInventaire().getLast().getNomEquipement());
                }
                else{
                    this.mdj.commenter("vous ne vous trouver pas sur une case d'équipement !");
                    redemander = true;
                }
                break;
            case 5:
                int nb = 1;
                StringBuilder sort = new StringBuilder("quelle sort voulez vous jeter \n1 - Guérison");
                if(((Personnage) e).estMagicien()){
                    sort.append("\n2 - Boogie Woogie \n3 - Arme Magique");
                    nb = 3;
                }
                int choixdusort = m_utils.demanderChoix(scanner,
                        sort.toString(),
                        1, nb);
                scanner.nextLine();
                choixSort(choixdusort, d,(Personnage) e);
                break;
        }
        if(redemander){
            int choixAction = m_utils.demanderChoix(scanner,
                    message,
                    0, nbchoix);
            scanner.nextLine();
            faireActionEntite(e, choixAction, d, message, nbchoix);
        }
        d.afficherDonjon();
    }

    public void choixSort(int choix, Donjon d, Personnage p){
        boolean sortLancer = false;
        switch(choix){
            case 1:
                int[] position = this.m_utils.demanderPositionCarteObligatoire("Choisissez la position du joueur a guerir",
                        'A', d.getLettreMax(),
                        1, d.getHauteur(),
                        scanner);
                while (!sortLancer){
                    sortLancer = p.Guerir(position[0], position[1], d);
                    if(!sortLancer){
                        position = this.m_utils.demanderPositionCarteObligatoire("Position invalide, rechoisissez !",
                                'A', d.getLettreMax(),
                                1, d.getHauteur(),
                                scanner);
                    }
                }
                this.mdj.commenter("Vous avez redonner "+p.getEfficaciteGuerison()+" à "+((Entite)d.getCarte()[position[0]-1][position[1]-1].getFirst()).getIdentificationEntite());
                break;
            case 2:
                int[] position1 = this.m_utils.demanderPositionCarteObligatoire("Entrez la position du 1er joueur ou monstre : ",
                        'A', d.getLettreMax(),
                        1, d.getHauteur(),
                        scanner);
                int[] position2 = this.m_utils.demanderPositionCarteObligatoire("Entrez la position du 2eme joueur ou monstre : ",
                        'A', d.getLettreMax(),
                        1, d.getHauteur(),
                        scanner);
                while (!sortLancer){
                    sortLancer = p.echangerPosition(position1[0], position1[1], position2[0], position2[1], d );
                    if(!sortLancer){
                        position1 = this.m_utils.demanderPositionCarteObligatoire("Entrez la position du 1er joueur ou monstre : ",
                                'A', d.getLettreMax(),
                                1, d.getHauteur(),
                                scanner);
                        position2 = this.m_utils.demanderPositionCarteObligatoire("Entrez la position du 2eme joueur ou monstre : ",
                                'A', d.getLettreMax(),
                                1, d.getHauteur(),
                                scanner);
                    }
                }
                this.mdj.commenter("Position echangée avec succès ! ");
                break;
            case 3:
                int location = m_utils.demanderChoix(scanner,
                        "Ou voulez vous enchanter votre arme ? : \n1 - sur la map \n2 - dans l'inventaire d'un joueur",
                        1, 2);
                scanner.nextLine();
                if(location == 1){
                    int[] positionArme = this.m_utils.demanderPositionCarteObligatoire("Entrez la position de l'arme a enchanter : ",
                            'A', d.getLettreMax(),
                            1, d.getHauteur(),
                            scanner);

                        sortLancer = p.enchanterArme(positionArme[0], positionArme[1], d);
                        if(!sortLancer){
                           choixSort(3, d, p);
                        }

                    this.mdj.commenter(((Equipement)d.getCarte()[positionArme[0]-1][positionArme[1]-1].getFirst()).getNomEquipement()+" enchantée ! ");
                }
                else{
                    int[] postionJoueur = this.m_utils.demanderPositionCarteObligatoire("Entrez la position du joueur : ",
                            'A', d.getLettreMax(),
                            1, d.getHauteur(),
                            scanner);
                    int nbarme = 1;
                    StringBuilder message = new StringBuilder("Quelle arme voulez vous enchanter ? : ");
                    for(Equipement e : ((Personnage)d.getCarte()[postionJoueur[0]-1][postionJoueur[1]-1].getFirst()).getInventaire()){
                        if(e.estArme()){
                            message.append("\n").append(nbarme).append(" - ").append(e.getNomEquipement());
                            nbarme++;
                        }
                    }
                    int arme = m_utils.demanderChoix(scanner,
                            message.toString(),
                            1, nbarme);
                    scanner.nextLine();

                    p.enchanterArmeInventaire(((Personnage)d.getCarte()[postionJoueur[0]-1][postionJoueur[1]-1].getFirst()),arme-1);

                    this.mdj.commenter("Arme enchantée !");
                }
               break;

        }
    }


    public void placerPersonnage(Personnage p, Donjon d) {
        int[] position = this.m_utils.demanderPositionCarte("Choisissez la position du joueur",
                'A', d.getLettreMax(),
                1, d.getHauteur(),
                scanner);


        boolean peutSePlacer = false;
        if (position[0] == -1 || position[1] == -1) {
            verificationPlacementjoueur(p, d, peutSePlacer);
        } else {
            while (!peutSePlacer) {
                p.setLocation(position[0] - 1, position[1] - 1);
                peutSePlacer = mdj.positionnerEntite(d, p);
                if(!peutSePlacer){
                    position = this.m_utils.demanderPositionCarte("Il y a un élément sur cette case, rechoisissez la position du joueur",
                            'A', d.getLettreMax(),
                            1, d.getHauteur(),
                            scanner);

                }

            }
        }
    }

    private void verificationPlacementjoueur(Personnage p, Donjon d, boolean peutSePlacer) {
        while (!peutSePlacer) {
            p.setLocation(m_des.lancerDes(1, d.getHauteur() - 1),
                    (m_des.lancerDes(1, d.getLargeur() - 1)));
            peutSePlacer = mdj.positionnerEntite(d, p);
        }
        System.out.println(p.getNom() + " a été placé en " + alphabet[p.getPositionX()] + String.valueOf(p.getPositionY()+1));
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


        int[] position = this.m_utils.demanderPositionCarte("Choisissez la position du joueur",
                'A', d.getLettreMax(),
                1, d.getHauteur(),
                scanner);

        boolean peutSePlacer = false;
        Personnage p;
        if (position[0] == -1 || position[1] == -1) {
            p = m_utils.creerPersonnageAleatoire(nom, race, classe, d);
            peutSePlacer = mdj.positionnerEntite(d, p);
            verificationPlacementjoueur(p, d, peutSePlacer);
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
        this.demanderEquipement(p);

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
                o.setLocation(m_des.lancerDes(1, d.getLargeur() - 1),
                        (m_des.lancerDes(1, d.getHauteur() - 1)));

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
                System.out.println("Obstacle positionné en " + alphabet[position[0]-1] + String.valueOf(position[1]));


        }
    }

    public void initEquipementAleatoire(Donjon d) {

        Equipement e = this.m_utils.creerEquipementAleatoire(d);
        boolean peutSePlacer = mdj.positionnerEquipement(d, e);
        while(!peutSePlacer){
            e.setLocation(m_des.lancerDes(1, d.getLargeur() - 1),
                    (m_des.lancerDes(1, d.getHauteur() - 1)));
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

                int portee = this.m_utils.demanderChoix(scanner,"Donnez sa portée : ", 1, 10);
                scanner.nextLine(); // consomme le \n

                int pv = this.m_utils.demanderChoix(scanner,"Donnez son nombre de pv max : ", 10, 30);
                scanner.nextLine(); // consomme le \n

                int vitesse = this.m_utils.demanderChoix(scanner,"Donnez sa vitesse : ", 8, 20);
                scanner.nextLine(); // consomme le \n

                String attaque = "";
                while (attaque.isBlank() || !attaque.matches("[a-zA-Z]+")) {
                    System.out.println("Donnez le nom de l'attaque du monstre : ");
                    attaque = scanner.nextLine();

                }

                int armure = this.m_utils.demanderChoix(scanner,"Donnez sa classe d'armure : ", 6, 14);
                scanner.nextLine(); // consomme le \n

                int force = this.m_utils.demanderChoix(scanner,"Donnez sa force : ", 5, 15);
                scanner.nextLine(); // consomme le \n

                int dexterite = this.m_utils.demanderChoix(scanner,"Donnez sa dexterite : ", 5, 20);
                scanner.nextLine();

                int initiative = this.m_utils.demanderChoix(scanner,"Donnez son initiative : ", 5, 15);
                scanner.nextLine();

                int[] position = this.m_utils.demanderPositionCarte("Choisissez la position du Monstre " + String.valueOf(i + 1),
                        'A', d.getLettreMax(),
                        1, d.getHauteur(),
                        scanner);

                int posX = 0;
                int posY = 0;
                m = new Monstre(espece, portee, pv,vitesse, attaque, armure, force, dexterite, initiative, posX, posY);
                boolean peutSePlacer = false;
                if(position[0] == -1 || position[1] == -1){
                    while(!peutSePlacer){
                        posX = m_des.lancerDes(1, d.getLargeur());
                        posY = m_des.lancerDes(1, d.getHauteur());
                        m.setLocation(posX-1, posY-1);
                        peutSePlacer = mdj.positionnerEntite(d, m);
                        System.out.println("Monstre positionné en " + alphabet[posX-1] + String.valueOf(posY));
                    }
                }
                else{
                    posX = position[0];
                    posY = position[1];
                }

                peutSePlacer = mdj.positionnerEntite(d, m);
                while (!peutSePlacer) {
                    position = this.m_utils.demanderPositionCarte("Il y a un élément sur cette case, rechoisissez la position du Monstre",
                            'A', d.getLettreMax(),
                            1, d.getHauteur(),
                            scanner);
                    posX = position[0];
                    posY = position[1];
                    m.setLocation(posX - 1, posY - 1);
                    peutSePlacer = mdj.positionnerEntite(d, m);
                }
                System.out.println("Monstre positionné en " + alphabet[posX-1] + String.valueOf(posY));

                break;
            }
        }
        m_monstresEnVie.add(m);
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
                    1, 8);
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
        if(position[0] == -1 || position[1] == -1){
            while(!peutSePlacer){
                position[0] = m_des.lancerDes(1, d.getLargeur());
                position[1] = m_des.lancerDes(1, d.getHauteur());
                assert e != null;
                e.setLocation(position[0]-1, position[1]-1);
                peutSePlacer = mdj.positionnerEquipement(d, e);
            }

        }

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
        System.out.println("Equipement positionné en " + alphabet[position[0]-1] + String.valueOf(position[1]));
    }

    public void afficherEntites() {
        for (Personnage p : this.m_joueursEnVie) {
            System.out.println("\t" + p.getNomAffiche() + " " + p.getNom() +
                    " (" + p.getNomRace() + " " + p.getNomClasse() + ", " + p.getPv() + "/" + p.getPvMax() + ")");
        }
        if (this.m_monstresEnVie != null) {
            for (Monstre m : this.m_monstresEnVie) {
                System.out.println(m.getNomAffiche() + " " + m.getEspece() +
                        " (" + m.getPv() + "/" + m.getPvMax() + ")");
            }
        }
        System.out.println();

    }

    public void demanderEquipement(Personnage p) {
        if (!p.getInventaire().isEmpty()) {
            int choixEquipement = -1;
            while (choixEquipement != 0) {
                choixEquipement = this.m_utils.demanderChoixOuParDefaut(p.getNom() + " peut choisir un équipement à équiper : " + p.afficherInventaire(),
                        0, p.getInventaire().size(), 0, scanner);
                if (choixEquipement != 0) {
                    System.out.println(p.getNom() + "s'est équipé de " + p.getInventaire().get(choixEquipement - 1).getNomEquipement() + ".");
                    p.sEquiper(p.getInventaire().get(choixEquipement - 1));
                }
            }
        }
    }


}
