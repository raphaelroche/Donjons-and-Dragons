package personnages.races;

import personnages.Personnage;

public class Humain extends Race {
    public Humain(Personnage p) {
        p.ajusterPv(2);
        p.ajusterForce(2);
        p.ajusterDexterite(2);
        p.ajusterVitesse(2);
        p.ajusterInitiative(2);
    }
}
