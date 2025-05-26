package utils;

import des.Des;
import donjons.Donjon;
import placable.entites.monstres.Monstre;
import placable.entites.personnages.Personnage;
import placable.equipements.Equipement;
import placable.equipements.armes.*;
import placable.equipements.armures.CotteDeMailles;
import placable.equipements.armures.DemiPlatte;
import placable.equipements.armures.Ecailles;
import placable.equipements.armures.Harnois;
import placable.obstacle.Obstacle;

import java.util.Scanner;

public class Utils {        //vérifie qu'on donne un entier compris entre min et max
                            // sert beaucoup lors des choix


    public int demanderChoix(Scanner scanner, String message, int min, int max) {
        int choix = -1;
        while (choix < min || choix > max) {
            System.out.println(message);
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (choix < min || choix > max) {
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre " + min + " et " + max + ".");
                }
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.next(); // consomme l'entrée invalide
            }
        }
        return choix;
    }

    // demander valeur ou laisser vide par défaut
    public int demanderChoixOuParDefaut(String message, int min, int max, int valeurParDefaut, Scanner scanner) {
        int valeur = valeurParDefaut;

        while (true) {
            System.out.println(message + " (entre " + min + " et " + max + ", " + valeurParDefaut + " par défaut (laisser vide)) : ");
            String input = scanner.nextLine().trim();


            if (input.isEmpty()) {
                // laissé vide → on garde la valeur par défaut
                break;
            }

            try {
                int parsedValue = Integer.parseInt(input);
                if (parsedValue >= min && parsedValue <= max) {
                    valeur = parsedValue;
                    break;
                } else {
                    System.out.println("Veuillez entrer un nombre entre " + min + " et " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre valide ou laisser vide.");
            }
        }

        return valeur;
    }

    public int[] demanderPositionCarte(String message, char minLettre, char maxLettre, int minNumero, int maxNumero, Scanner scanner) {
        int[] position = new int[2]; // [indexLettre, numero]
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        while (true) {
            System.out.print(message + " (format: LettreNuméro, par exemple A1, B20... entre "
                    + minLettre + minNumero + " et " + maxLettre + maxNumero + ", laisser vide pour tirage au hasard): \n");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.isEmpty()) {
                position[0] = -1;
                position[1] = -1;
                break;
            }

            if (position(minLettre, maxLettre, minNumero, maxNumero, position, alphabet, input)) break;
        }
        return position;
    }

    public int[] demanderPositionCarteObligatoire(String message, char minLettre, char maxLettre, int minNumero, int maxNumero, Scanner scanner) {
        int[] position = new int[2]; // [indexLettre, numero]
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        while (true) {
            System.out.print(message + " (format: LettreNuméro, par exemple A1, B20... entre "
                    + minLettre + minNumero + " et " + maxLettre + (maxNumero-1) + " : \n");
            String input = scanner.nextLine().trim().toUpperCase();


            if (position(minLettre, maxLettre, minNumero, maxNumero, position, alphabet, input)) break;
        }
        return position;
    }

    private boolean position(char minLettre, char maxLettre, int minNumero, int maxNumero, int[] position, String alphabet, String input) {
        if (input.matches("[A-Z]+[0-9]+")) {
            char lettre = input.charAt(0);
            int numero;

            try {
                numero = Integer.parseInt(input.substring(1));
                int indexLettre = alphabet.indexOf(lettre)+1;

                if (indexLettre >= (minLettre - 'A') && indexLettre <= (maxLettre - 'A') && numero >= minNumero && numero <= maxNumero) {
                    position[1] = numero;
                    position[0] = indexLettre;
                    return true;
                } else {
                    System.out.println("Position hors plage. Veuillez entrer une position valide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Numéro invalide. Veuillez entrer une position valide.");
            }
        } else {
            System.out.println("Format invalide. Veuillez entrer une position valide (ex. A1, B20).");
        }
        return false;
    }

    public Monstre creerMonstreAleatoire(Donjon d) {
        Des des = new Des();
        int x = des.lancerDes(1, d.getLargeur()-1);
        int y = des.lancerDes(1, d.getHauteur()-1);
        Monstre m = new Monstre("dragon");
        m.setLocation(x-1, y-1);
        return m;
    }

    public Equipement creerEquipementAleatoire(Donjon d) {
        Des des = new Des();
        int x = des.lancerDes(1, d.getLargeur() - 1);
        int y = des.lancerDes(1, d.getHauteur() - 1);

        // Choisir un type aléatoire
        int choix = des.lancerDes(1, 12);

        Equipement e = null;
         switch (choix) {
            case 1 -> e = new Arbalete(x, y);
            case 2 -> e = new Arc(x, y);
            case 3 -> e = new Baton(x, y);
            case 4 -> e = new EpeeLongue(x, y);
            case 5 -> e = new Fronde(x, y);
            case 6 -> e = new Masse(x, y);
            case 7 -> e = new Rapiere(x, y);
            case 8 -> e = new CotteDeMailles(x, y);
            case 9 -> e = new DemiPlatte(x, y);
            case 10 -> e = new Ecailles(x, y);
            case 11 -> e = new Harnois(x, y);
            case 12 -> e = new EpeeDeuxMain(x, y);

        };
         return e;
    }
    public Obstacle creerObstacleAleatoire(Donjon d){
        Des des = new Des();
        int x = des.lancerDes(1, d.getLargeur() - 1);
        int y = des.lancerDes(1, d.getHauteur() - 1);
        return new Obstacle(x, y);
    }
    public Personnage creerPersonnageAleatoire(String nom, int race, int classe, Donjon d){
        Des des = new Des();
        int x = des.lancerDes(1, d.getLargeur() - 1);
        int y = des.lancerDes(1, d.getHauteur() - 1);
        return new Personnage(nom, race, classe, x, y);
    }

}
