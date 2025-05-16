package placable.entites.personnages.classes;

import placable.entites.personnages.Personnage;

public class Guerrier extends Classe {
    public Guerrier() {
       this.m_pv = 20;
    }

    @Override
    public void initialiser(Personnage p){
        p.setPv(this.m_pv);
    }
}
