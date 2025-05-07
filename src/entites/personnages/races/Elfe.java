package entites.personnages.races;

import entites.personnages.Personnage;

public class Elfe extends Race{
    public Elfe(Personnage p) {
        p.ajusterDexterite(6);
    }
}
