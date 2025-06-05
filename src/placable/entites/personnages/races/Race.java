package placable.entites.personnages.races;

import placable.entites.personnages.Personnage;

public abstract class Race {

    protected int m_dexterite;
    protected int m_vitesse;
    protected int m_force;
    protected int m_initiative;
    protected String m_nomRace;

    public Race(int dexterite, int vitesse, int force, int initiative, String nomRace) {
        m_dexterite = dexterite;
        m_vitesse = vitesse;
        m_force = force;
        m_initiative = initiative;
        m_nomRace = nomRace;
    }
    public int getDexterite() {
        return m_dexterite;
    }
    public int getVitesse() {
        return m_vitesse;
    }
    public int getForce() {
        return m_force;
    }
    public int getInitiative() {
        return m_initiative;
    }

    public String getNom() {
        return m_nomRace;
    }
}