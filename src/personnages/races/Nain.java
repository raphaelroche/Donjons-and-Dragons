package personnages.races;

import personnages.Personnage;

public class Nain extends Race{
    @Override
    public void ajusterStat(Personnage p) {
        p.ajouterForce(6);
    }
}
