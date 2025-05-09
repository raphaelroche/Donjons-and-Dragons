package entites;

import donjons.Donjon;
import entites.personnages.Personnage;
import maitredujeu.*;

public abstract class Entite {
    protected int m_pv;
    protected int m_pvMax;
    protected int m_force;
    protected int m_dexterite;
    protected int m_initiative;

    public String[][] seDeplacer(Donjon d, String direction){
        String[][] carte = d.getCarte();






        return carte;

    }

    public void attaquer(Donjon d, Entite cible){
        String[][] carte = d.getCarte();

       



    }


    public int getPv() {
        return this.m_pv;
    }

    public int getForce() {
        return this.m_force;
    }

    public int getDexterite() {
        return this.m_dexterite;
    }

    public int getInitiative() {
        return this.m_initiative;
    }

    public void setPv(int pv) {
        this.m_pv = pv; this.m_pvMax = pv;
    }

    public void setForce(int force) {
        this.m_force = force;
    }

    public void setDexterite(int dexterite) {
        this.m_dexterite = dexterite;
    }

    public void setInitiative(int initiative) {
        this.m_initiative = initiative;
    }

    //fonctions pour ajuster stat : à chaque ajustement, vérifie si positif, sinon met à 0

    public void ajusterPv(int valeur) {
        this.m_pv += valeur;
        if (this.m_pv < 0) {
            this.m_pv = 0;
        }
    }


    public void ajusterForce(int valeur) {
        this.m_force += valeur;
        if (this.m_force < 0) {
            this.m_force = 0;
        }
    }

    public void ajusterDexterite(int valeur) {
        this.m_dexterite += valeur;
        if (this.m_dexterite < 0) {
            this.m_dexterite = 0;
        }
    }

    public void ajusterInitiative(int valeur) {
        this.m_initiative += valeur;
        if (this.m_initiative < 0) {
            this.m_initiative = 0;
        }
    }
}
