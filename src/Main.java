import des.Des;
import equipements.armures.*;
import personnages.Personnage;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        System.out.println("Bienvenue dans DOOnjon et Dragons");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez un nom :");
        String nom = scanner.nextLine();

        int race = Utils.demanderChoix(scanner,
                "Choisissez une race :\n1 - Humain\n2 - Nain\n3 - Elfe\n4 - Halfelin", 1, 4);

        int classe = Utils.demanderChoix(scanner,
                "Choisissez une classe :\n1 - Clerc\n2 - Guerrier\n3 - Magicien\n4 - Roublard", 1, 4);


        Personnage p1 = new Personnage(nom, race, classe);
        System.out.println(p1.toString());
        System.out.println(p1.afficherInventaire());
    }
}