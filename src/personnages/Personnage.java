package personnages;

import personnages.races.*;
import des.Des;

public abstract class Personnage {
    private String m_nom;

    private Race m_race;

    private int m_pv;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;

    public Personnage(String nom, int race) {
        m_nom = nom;
        switch(race) {
            case 1:
                m_race = new Humain();
                break;
            case 2:
                m_race = new Nain();
                break;
            case 3:
                m_race = new Elfe();
                break;
            case 4:
                m_race = new Halfelin();
                break;
            //default:
        }

        Des des = new Des();
        m_pv = 3 + des.lancerDes(4,4);
        m_force = 3 + des.lancerDes(4,4);
        m_dexterite = 3 + des.lancerDes(4,4);
        m_vitesse = 3 + des.lancerDes(4,4);
        m_initiative = 3 + des.lancerDes(4,4);

        m_race.ajusterStat(this);
    }

    // Getters
    public String getNom() {
        return m_nom;
    }

    public Race getRace() {
        return m_race;
    }

    public int getPv() {
        return m_pv;
    }

    public int getForce() {
        return m_force;
    }

    public int getDexterite() {
        return m_dexterite;
    }

    public int getVitesse() {
        return m_vitesse;
    }

    public int getInitiative() {
        return m_initiative;
    }

    // Setters
    public void setNom(String nom) {
        this.m_nom = nom;
    }

    public void setRace(Race race) {
        this.m_race = race;
    }

    public void setPv(int pv) {
        this.m_pv = pv;
    }

    public void setForce(int force) {
        this.m_force = force;
    }

    public void setDexterite(int dexterite) {
        this.m_dexterite = dexterite;
    }

    public void setVitesse(int vitesse) {
        this.m_vitesse = vitesse;
    }

    public void setInitiative(int initiative) {
        this.m_initiative = initiative;
    }
}
