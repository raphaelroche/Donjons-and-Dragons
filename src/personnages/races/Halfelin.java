package personnages.races;

import personnages.Personnage;

public class Halfelin extends Race{
    @Override
    public void ajusterStat(Personnage p) {
        p.ajouterDexterite(4);
        p.ajouterVitesse(2);
    }
}
