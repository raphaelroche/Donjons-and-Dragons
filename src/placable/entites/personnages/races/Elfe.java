package placable.entites.personnages.races;

import placable.entites.personnages.Personnage;

public class Elfe extends Race{
    public Elfe(Personnage p) {
        p.ajusterDexterite(6);
    }
}
