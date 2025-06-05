package placable.entites.personnages.classes;

import placable.entites.personnages.Personnage;

public class Magicien extends Classe {
    public Magicien() {
        super("Magicien",12);
    }

    @Override
    public boolean estMagicien(){
        return true;
    }
}
