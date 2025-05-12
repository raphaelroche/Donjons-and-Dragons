package placable.entites.personnages.races;

import placable.entites.personnages.Personnage;

public class Nain extends Race{
    public Nain(Personnage p) {
        p.ajusterForce(6);
    }

}
