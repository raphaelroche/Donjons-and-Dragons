package entites.personnages;

import entites.Entite;
import equipements.Equipement;
import equipements.armes.*;
import equipements.armures.CotteDeMailles;
import equipements.armures.Ecailles;
import entites.personnages.classes.*;
import entites.personnages.races.*;
import des.Des;

import java.util.ArrayList;

public class Personnage extends Entite {
    protected String m_nom;
    private Race m_race;
    private Classe m_classe;
    private int m_vitesse;

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

    public int getVitesse() {
        return this.m_vitesse;
    }


    // Setters
    public void setVitesse(int vitesse) {
        this.m_vitesse = vitesse;
    }

    public void ajusterVitesse(int valeur) {
        this.m_vitesse += valeur;
        if (this.m_vitesse < 0) {
            this.m_vitesse = 0;
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
