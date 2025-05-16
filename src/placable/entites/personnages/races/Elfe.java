package placable.entites.personnages.races;

import placable.entites.personnages.Personnage;

public class Elfe extends Race{
    public Elfe() {
        this.m_dexterite = 6;
        this.m_nomRace = "Elfe";
    }
    @Override
    public void initialiser(Personnage p){
        p.ajusterDexterite(this.m_dexterite);
    }
}


