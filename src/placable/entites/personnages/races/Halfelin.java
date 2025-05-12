package placable.entites.personnages.races;

import placable.entites.personnages.Personnage;

public class Halfelin extends Race{
    public  Halfelin(Personnage p) {
        p.ajusterDexterite(4);
        p.ajusterVitesse(2);
    }
}
