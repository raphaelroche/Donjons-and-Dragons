package personnages.races;

import personnages.Personnage;

public class Halfelin extends Race{
    @Override
    public void ajusterStat(Personnage p) {
        p.setDexterite(p.getDexterite() + 4);
        p.setVitesse(p.getVitesse() + 2);
    }
}
