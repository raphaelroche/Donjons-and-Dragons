package affichage;

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

public class Creation {
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
