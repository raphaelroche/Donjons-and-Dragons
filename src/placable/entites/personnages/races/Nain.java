package placable.entites.personnages.races;

import placable.entites.personnages.Personnage;

public class Nain extends Race{
    public Nain() {
        this.m_force = 6;
    }
    @Override
    public void initialiser(Personnage p){
        p.ajusterForce(this.m_force);
    }
}
