package placable.entites.personnages.races;

import placable.entites.personnages.Personnage;

public class Halfelin extends Race{
    public  Halfelin() {
        this.m_dexterite = 4;
        this.m_vitesse = 2;
    }
    @Override
    public void initialiser(Personnage p){
        p.ajusterDexterite(this.m_dexterite);
        p.ajusterVitesse(this.m_vitesse);
    }
}
