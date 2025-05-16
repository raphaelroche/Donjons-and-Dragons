package placable.entites.personnages.races;

import placable.entites.personnages.Personnage;

public class Humain extends Race {
    public Humain() {
        
        this.m_force = 2;
        this.m_dexterite = 2;
        this.m_vitesse = 2;
        this.m_initiative = 2;
        this.m_nomRace = "Humain";
    }
    @Override
    public void initialiser(Personnage p){
        p.ajusterDexterite(this.m_dexterite);
        p.ajusterVitesse(this.m_vitesse);
        p.ajusterInitiative(this.m_initiative);
        p.ajusterForce(this.m_force);
    }
}
