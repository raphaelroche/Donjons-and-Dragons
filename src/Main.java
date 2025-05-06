import des.Des;
import personnages.Personnage;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        System.out.println("Bienvenue dans DOOnjon et Dragons");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez votre nom : ");
        String nom = scanner.nextLine();

        System.out.print("Choisissez votre race : ");
        int race = Integer.parseInt(scanner.nextLine());

        System.out.print("Choisissez votre classe : ");
        int classe = Integer.parseInt(scanner.nextLine());

        Personnage p1 = new Personnage(nom, race, classe);

        System.out.print(p1.toString());
    }
}