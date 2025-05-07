package entites.personnages.races;

import entites.personnages.Personnage;

public class Nain extends Race{
    public Nain(Personnage p) {
        p.ajusterForce(6);
    }

}
