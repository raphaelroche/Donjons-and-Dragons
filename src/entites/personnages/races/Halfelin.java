package entites.personnages.races;

import entites.personnages.Personnage;

public class Halfelin extends Race{
    public  Halfelin(Personnage p) {
        p.ajusterDexterite(4);
        p.ajusterVitesse(2);
    }
}
