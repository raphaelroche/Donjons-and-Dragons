package personnages.races;

import personnages.Personnage;

public class Nain extends Race{
    public Nain(Personnage p) {
        p.ajouterForce(6);
    }

}
