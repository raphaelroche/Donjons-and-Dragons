package placable.entites.personnages.classes;


import placable.entites.personnages.Personnage;

public class Clerc extends Classe{
    public Clerc() {
         super("Clerc",16);
    }



    @Override
    public boolean estClerc(){
        return true;
    }
}
