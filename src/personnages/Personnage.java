package personnages;

import personnages.classes.*;
import personnages.races.*;
import des.Des;

public class Personnage {
    private String m_nom;

    private Race m_race;
    private Classe m_classe;

    private int m_pv;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;

    public Personnage(String nom, int race, int classe) {
        m_nom = nom;
        switch(race) {
            case 1:
                m_race = new Humain(this);
                break;
            case 2:
                m_race = new Nain(this);
                break;
            case 3:
                m_race = new Elfe(this);
                break;
            case 4:
                m_race = new Halfelin(this);
                break;
            //default:
        }
        switch(classe){
            case 1:
                m_classe = new Clerc(this);
                break;
            case 2:
                m_classe = new Guerrier(this);
                break;
            case 3:
                m_classe = new Magicien(this);
                break;
            case 4:
                m_classe = new Roublard(this);
                break;
            default:
                break;
        }

        Des des = new Des();
        m_force = 3 + des.lancerDes(4,4);
        m_dexterite = 3 + des.lancerDes(4,4);
        m_vitesse = 3 + des.lancerDes(4,4);
        m_initiative = 3 + des.lancerDes(4,4);


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

    public void ajouterPv(int valeur) {
        this.m_pv += valeur;
    }

    public void ajouterForce(int valeur) {
        this.m_force += valeur;
    }

    public void ajouterDexterite(int valeur) {
        this.m_dexterite += valeur;
    }

    public void ajouterVitesse(int valeur) {
        this.m_vitesse += valeur;
    }

    public void ajouterInitiative(int valeur) {
        this.m_initiative += valeur;
    }

    @Override
    public String toString() {
        return "Nom : " + m_nom +
                ", Race : " + (m_race != null ? m_race.getClass().getSimpleName() : "Aucune") +
                ", Classe : " + (m_classe != null ? m_classe.getClass().getSimpleName() : "Aucune") +
                ", PV : " + m_pv +
                ", Force : " + m_force +
                ", Dextérité : " + m_dexterite +
                ", Vitesse : " + m_vitesse +
                ", Initiative : " + m_initiative;
    }

}
