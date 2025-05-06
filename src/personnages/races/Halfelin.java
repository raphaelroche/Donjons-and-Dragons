package personnages.races;

import personnages.Personnage;

public class Halfelin extends Race{
    public  Halfelin(Personnage p) {
        p.ajouterDexterite(4);
        p.ajouterVitesse(2);
    }
}
