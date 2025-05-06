package personnages.races;

import personnages.Personnage;

public class Humain extends Race {

    @Override
    public void ajusterStat(Personnage p) {
        p.setPv(p.getPv() + 2);
        p.setForce(p.getForce() + 2);
        p.setDexterite(p.getDexterite() + 2);
        p.setVitesse(p.getVitesse() + 2);
        p.setInitiative(p.getInitiative() + 2);
    }
}
