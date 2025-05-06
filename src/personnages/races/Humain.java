package personnages.races;

import personnages.Personnage;

public class Humain extends Race {

    @Override
    public void ajusterStat(Personnage p) {
        p.ajouterPv(2);
        p.ajouterForce(2);
        p.ajouterDexterite(2);
        p.ajouterVitesse(2);
        p.ajouterInitiative(2);
    }
}
