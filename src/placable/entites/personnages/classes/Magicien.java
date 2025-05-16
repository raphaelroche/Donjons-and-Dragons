package placable.entites.personnages.classes;

import placable.entites.personnages.Personnage;

public class Magicien extends Classe {
    public Magicien() {
        this.m_pv = 12;
    }

    @Override
    public void initialiser(Personnage p){
        p.setPv(this.m_pv);
    }
}
