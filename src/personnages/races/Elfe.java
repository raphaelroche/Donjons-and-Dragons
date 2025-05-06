package personnages.races;

import personnages.Personnage;

public class Elfe extends Race{
    @Override
    public void ajusterStat(Personnage p) {
        p.ajouterDexterite(6);
    }
}
