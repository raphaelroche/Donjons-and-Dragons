package placable.entites.personnages.races;

import placable.entites.personnages.Personnage;

public abstract class Race {

    protected int m_dexterite;
    protected int m_vitesse;
    protected int m_force;
    protected int m_initiative;

    public abstract void initialiser(Personnage p);
}