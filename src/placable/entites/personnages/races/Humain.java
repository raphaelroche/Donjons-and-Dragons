package placable.entites.personnages.races;

import placable.entites.personnages.Personnage;

public class Humain extends Race {
    public Humain(Personnage p) {
        
        p.ajusterForce(2);
        p.ajusterDexterite(2);
        p.ajusterVitesse(2);
        p.ajusterInitiative(2);
    }
}
