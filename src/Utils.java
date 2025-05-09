import java.util.Scanner;

public class Utils {        //vérifie qu'on donne un entier compris entre min et max
                            // sert beaucoup lors des choix
    public static int demanderChoix(Scanner scanner, String message, int min, int max) {
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
}
