package entites.personnages;

import donjons.Donjon;
import entites.Entite;
import entites.monstres.Monstre;
import equipements.Equipement;
import equipements.armes.*;
import equipements.armures.Armures;
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
    private Des des;

    private ArrayList<Equipement> m_inventaire;
    private Armes[] m_armeEquipee;
    private Armures[] m_armureEquipee;

    public Personnage(String nom, int race, int classe) {
        this.m_nom = nom;
        this.m_inventaire = new ArrayList<Equipement>();
        this.m_armeEquipee = new Armes[1];
        this.m_armureEquipee = new Armures[1];
        attribuerRaceClasse(race, classe); //utilise un int pour désigner
        des = new Des();
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

    public void sEquiperArme(Armes a){

        this.m_armeEquipee[0] = a;

    }
    public Armes getArmeEquipee(){
        return m_armeEquipee[0];
    }

    @Override
    public void seDeplacer(){

    }

    public int getClasseArmure(){
        return this.m_armureEquipee[0].getClasseArmure();
    }

    public boolean attaquer(Monstre cible, String[][] carte){
        int distanceJoueurCible = 0;


        //verifier la distance entre le joueur le monstre grace a la carte


        if(this.m_armeEquipee[0].getPortee() <= distanceJoueurCible){
            int degat = des.lancerDes(1, 20);
            if(this.m_armeEquipee[0].getPortee() == 1){
                degat += this.m_force;
            }
            else{
                degat += this.m_dexterite;
            }
            if(degat > cible.getclasseArmure()){
                this.m_armeEquipee[0].determinerDegat();
                cible.perdrePV(this.m_armeEquipee[0].getDegats());
                return true;
            }
        }
        return false;

    }

    public void sEquiperArmure(Armures a){
        this.m_armureEquipee[0] = a;
    }

    public void ramasserEquipement(Equipement e, Donjon d){

        this.m_inventaire.add(e);
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

    public void perdrePV(int degats){
        this.m_pv -= degats;
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
