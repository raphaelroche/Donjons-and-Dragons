package utils;

import des.Des;

import java.util.Scanner;

public class Utils {        //vérifie qu'on donne un entier compris entre min et max
                            // sert beaucoup lors des choix
    private Des des;

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
        char[] alphabet = new char[] {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        while (true) {
            System.out.print(message + " (format: LettreNuméro, par exemple A1, B20... entre "
                    + minLettre + minNumero + " et " + maxLettre + maxNumero + ", laisser vide pour tirage au hasard): \n");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.isEmpty()) {
                position[0] = -1;
                position[1] = -1;
                break;
            }

            if (input.matches("[A-Z]+[0-9]+")) {
                char lettre = input.charAt(0);
                int numero;

                try {
                    numero = Integer.parseInt(input.substring(1));
                    int indexLettre = lettre - 'A';

                    if (indexLettre >= (minLettre - 'A') && indexLettre <= (maxLettre - 'A') && numero >= minNumero && numero <= maxNumero) {
                        position[0] = indexLettre;
                        position[1] = numero;
                        break;
                    } else {
                        System.out.println("Position hors plage. Veuillez entrer une position valide.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Numéro invalide. Veuillez entrer une position valide.");
                }
            } else {
                System.out.println("Format invalide. Veuillez entrer une position valide (ex. A1, B20).");
            }
        }
        return position;
    }

}
