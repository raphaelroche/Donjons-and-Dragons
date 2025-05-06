import des.Des;
import personnages.Personnage;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        System.out.println("Bienvenue dans DOOnjon et Dragons");
        Des des1 = new Des();
        System.out.println(des1.lancerDes(4,4));

        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez votre nom : ");
        String nom = scanner.nextLine();

        System.out.print("Choisissez votre classe : ");
        int race = Integer.parseInt(scanner.nextLine());

        Personnage p1 = new Personnage(nom, race);

        System.out.println("Nom : " + p1.getNom());
        System.out.println("Race : " + p1.getRace().getClass().getSimpleName());
        System.out.println("PV : " + p1.getPv());
        System.out.println("Force : " + p1.getForce());
        System.out.println("Dextérité : " + p1.getDexterite());
        System.out.println("Vitesse : " + p1.getVitesse());
        System.out.println("Initiative : " + p1.getInitiative());
    }
}