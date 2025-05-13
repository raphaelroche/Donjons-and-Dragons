import donjons.Donjon;
import maitredujeu.MaitreDuJeu;
import placable.entites.monstres.Monstre;
import placable.equipements.armes.*;
import placable.obstacle.Obstacle;

public class Main {
    public static void main(String args[]){
     /*   System.out.println("Bienvenue dans DOOnjon et Dragons");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez un nom :");
        String nom = scanner.nextLine();

        int race = utils.Utils.demanderChoix(scanner,
                "Choisissez une race :\n1 - Humain\n2 - Nain\n3 - Elfe\n4 - Halfelin", 1, 4);

        int classe = utils.Utils.demanderChoix(scanner,
                "Choisissez une classe :\n1 - Clerc\n2 - Guerrier\n3 - Magicien\n4 - Roublard", 1, 4);


       Personnage p1 = new Personnage(nom, race, classe, 5, 5);
      System.out.println(p1.toString());
      System.out.println(p1.afficherInventaire());
      */

        MaitreDuJeu mj = new MaitreDuJeu();

        Donjon d = new Donjon();
        d.positionnerElementCarte(new Monstre(d));
        Fronde fronde = new Fronde();
        mj.positionnerEquipement(d, fronde, 1, 2);
        Obstacle o = new Obstacle(d);
        mj.postionnerObstacle(d, o, o.getPositionX(), o.getPositionY());
        d.afficherDonjon();
    }
}