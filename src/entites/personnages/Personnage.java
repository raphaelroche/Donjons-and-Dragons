package personnages;

import equipements.Equipement;
import equipements.armes.*;
import equipements.armures.CotteDeMailles;
import equipements.armures.Ecailles;
import personnages.classes.*;
import personnages.races.*;
import des.Des;

import java.util.ArrayList;

public class Personnage {
    private String m_nom;

    private Race m_race;
    private Classe m_classe;

    private int m_pv;
    private int m_pvMax;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;

    private ArrayList<Equipement> m_inventaire;

    public Personnage(String nom, int race, int classe) {
        this.m_nom = nom;
        this.m_inventaire = new ArrayList<Equipement>();
        attribuerRaceClasse(race, classe); //utilise un int pour désigner
        Des des = new Des();
        this.m_force = 3 + des.lancerDes(4,4);
        this.m_dexterite = 3 + des.lancerDes(4,4);
        this.m_vitesse = 3 + des.lancerDes(4,4);
        this.m_initiative = 3 + des.lancerDes(4,4);
    }

    public void attribuerRaceClasse(int race, int classe) {
        switch(race) {      //attribue la race
            case 1:
                this.m_race = new Humain(this);
                break;
            case 2:
                this.m_race = new Nain(this);
                break;
            case 3:
                this.m_race = new Elfe(this);
                break;
            case 4:
                this.m_race = new Halfelin(this);
                break;
            default:
                break;
        }
        switch(classe) {      //attribue la classe, et ajoute ses objets dans l'inventaire
            case 1:
                this.m_classe = new Clerc(this);
                this.m_inventaire.add(new Masse());
                this.m_inventaire.add(new Ecailles());
                this.m_inventaire.add(new Arbalete());
                break;
            case 2:
                this.m_classe = new Guerrier(this);
                this.m_inventaire.add(new CotteDeMailles());
                this.m_inventaire.add(new EpeeLongue());
                this.m_inventaire.add(new Arbalete());
                break;
            case 3:
                this.m_classe = new Magicien(this);
                this.m_inventaire.add(new Baton());
                this.m_inventaire.add(new Fronde());
                break;
            case 4:
                this.m_classe = new Roublard(this);
                this.m_inventaire.add(new Rapiere());
                this.m_inventaire.add(new Arc());
                break;
            default:
                break;
        }
    }

    // Getters
    public String getNom() {
        return this.m_nom;
    }

    public Race getRace() {
        return this.m_race;
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

    public int getVitesse() {
        return this.m_vitesse;
    }

    public int getInitiative() {
        return this.m_initiative;
    }

    // Setters

    public void setPv(int pv) {
        this.m_pv = pv; this.m_pvMax = pv;
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

    public void ajusterVitesse(int valeur) {
        this.m_vitesse += valeur;
        if (this.m_vitesse < 0) {
            this.m_vitesse = 0;
        }
    }

    public void ajusterInitiative(int valeur) {
        this.m_initiative += valeur;
        if (this.m_initiative < 0) {
            this.m_initiative = 0;
        }
    }

    public String afficherInventaire() {
        if (this.m_inventaire.isEmpty()) {
            return "La liste est vide.";
        }

        StringBuilder contenu = new StringBuilder();
        for (int i = 0; i < this.m_inventaire.size(); i++) {
            contenu.append((i + 1)).append(" - ").append(this.m_inventaire.get(i).getNomEquipement()).append("\n");
        }
        return contenu.toString();
    }


    @Override
    public String toString() {
        return "Nom : " + this.m_nom +
                ", Race : " + (this.m_race != null ? this.m_race.getClass().getSimpleName() : "Aucune") +
                ", Classe : " + (this.m_classe != null ? this.m_classe.getClass().getSimpleName() : "Aucune") +
                ", PV : " + this.m_pv +
                ", Force : " + this.m_force +
                ", Dextérité : " + this.m_dexterite +
                ", Vitesse : " + this.m_vitesse +
                ", Initiative : " + this.m_initiative;
    }

}
