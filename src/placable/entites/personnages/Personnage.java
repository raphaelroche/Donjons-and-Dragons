package placable.entites.personnages;

import donjons.Donjon;
import placable.Placable;
import placable.entites.Entite;
import placable.entites.monstres.Monstre;
import placable.equipements.Equipement;
import placable.equipements.armes.*;
import placable.equipements.armures.Armures;
import placable.equipements.armures.CotteDeMailles;
import placable.equipements.armures.Ecailles;
import placable.entites.personnages.classes.*;
import placable.entites.personnages.races.*;
import des.Des;
import sorts.*;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class Personnage extends Entite {
    protected String m_nom;
    private Race m_race;
    private Classe m_classe;
    private Des des;

    private ArrayList<Equipement> m_inventaire;
    private ArrayList<Sort> m_sorts;
    private Armes m_armeEquipee;
    private Armures m_armureEquipee;

    public Personnage(String nom, int race, int classe, int x, int y) {
        this.m_nom = nom;
        setLocation(x-1, y-1);
        this.m_inventaire = new ArrayList<>();
        this.m_sorts = new ArrayList<>();
        this.m_armeEquipee =null;
        this.m_armureEquipee =null;
        if(this.m_nom.length()<3){
            this.m_nomAffiche = (this.m_nom + "   ").substring(0,3);
        }
        else{
            this.m_nomAffiche = this.m_nom.substring(0,3);
        }

        this.attribuerRaceClasse(race, classe); //utilise un int pour désigner
        des = new Des();
        this.m_force = 3 + des.lancerDes(4,4);
        this.m_dexterite = 3 + des.lancerDes(4,4);
        this.m_vitesse = 3 + des.lancerDes(4,4);
        this.m_initiative = 3 + des.lancerDes(4,4);
    }
   

    public void attribuerRaceClasse(int race, int classe) {
        switch(race) {      //attribue la race
            case 1:
                this.m_race = new Humain();
                this.m_race.initialiser(this);
                break;
            case 2:
                this.m_race = new Nain();
                this.m_race.initialiser(this);
                break;
            case 3:
                this.m_race = new Elfe();
                this.m_race.initialiser(this);
                break;
            case 4:
                this.m_race = new Halfelin();
                this.m_race.initialiser(this);
                break;
            default:
                break;
        }
        switch(classe) {      //attribue la classe, et ajoute ses objets dans l'inventaire
            case 1:
                this.m_classe = new Clerc();
                this.m_classe.initialiser(this);
                this.ajouterEquipementInventaire(new Masse());
                this.ajouterEquipementInventaire(new Ecailles());
                this.ajouterEquipementInventaire(new Arbalete());
                this.m_sorts.add(new Guerison());
                break;
            case 2:
                this.m_classe = new Guerrier();
                this.m_classe.initialiser(this);
                this.ajouterEquipementInventaire(new CotteDeMailles());
                this.ajouterEquipementInventaire(new EpeeLongue());
                this.ajouterEquipementInventaire(new Arbalete());
                break;
            case 3:
                this.m_classe = new Magicien();
                this.m_classe.initialiser(this);
                this.ajouterEquipementInventaire( new Baton());
                this.ajouterEquipementInventaire( new Fronde());
                this.m_sorts.add(new Guerison());
                this.m_sorts.add(new BoogieWoogie());
                this.m_sorts.add(new ArmeMagique());
                break;
            case 4:
                this.m_classe = new Roublard();
                this.m_classe.initialiser(this);
                this.ajouterEquipementInventaire(new Rapiere());
                this.ajouterEquipementInventaire(new Arc());
                break;
            default:
                break;
        }
    }

    public boolean Guerir(Personnage p){
        boolean heal = false;

        if(this.m_classe.estClerc() || this.m_classe.estMagicien()){
            ContextSort cible = new ContextSort(p);
             heal = (boolean) this.m_sorts.getFirst().lancerSort(cible);

        }
        return heal;
    }

    public boolean echangerPosition(Entite e1, Entite e2){
        boolean echange = false;
        if(this.m_classe.estMagicien()){
            ContextSort cible = new ContextSort(e1,e2);
                echange = (boolean) this.m_sorts.get(1).lancerSort(cible);
        }
        return echange;
    }

    public boolean enchanterArme(Armes arme){
        boolean enchanter = false;
        if(this.m_classe.estMagicien()){
            ContextSort armeAEnchanter = new ContextSort(arme);
            enchanter = (boolean) this.m_sorts.get(2).lancerSort(armeAEnchanter);
        }
        return enchanter;

    }


    public boolean attaquer(Monstre cible){



        int dX = this.m_positionX - cible.getPositionX();
        int dY = this.m_positionY - cible.getPositionY();

        double distanceJoueurCible = Math.sqrt(dX * dX + dY * dY);


        if(this.m_armeEquipee.getPortee() >= distanceJoueurCible){
            int degat = des.lancerDes(1, 20);
            if(this.m_armeEquipee.getPortee() == 1){
                degat += this.m_force;
            }
            else{
                degat += this.m_dexterite;
            }
            if(degat > cible.getclasseArmure()){
                this.m_armeEquipee.determinerDegat();
                cible.ajusterPv(-(this.m_armeEquipee.getDegats()));
                return true;
            }
        }
        return false;

    }

    public void sEquiperArmure(Armures a){
        if(this.m_armureEquipee != null){
            this.m_inventaire.add(this.m_armureEquipee);
            this.m_armureEquipee = null;
        }
        this.m_armureEquipee= a;
        this.m_inventaire.remove(this.m_armureEquipee);

        if(m_armureEquipee.getChangeStat()){
            this.m_vitesse -= 4;
        }
    }
    public void sEquiperArme(Armes a){

        if(this.m_armeEquipee != null){
            this.m_inventaire.add(this.m_armeEquipee);
        }
        this.m_armeEquipee= a;
        this.m_inventaire.remove(this.m_armeEquipee);

        if(m_armeEquipee.getChangeStat()){
            this.m_vitesse -= 2;
            this.m_force += 4;
        }

    }

    public boolean ramasserEquipement(Equipement e, ArrayList<Placable>[][] carte){


        if(contientEquipement(carte[this.m_positionX][this.m_positionY])){

            carte[this.m_positionX][this.m_positionY].clear();
            carte[this.m_positionX][this.m_positionY].add(this);
            this.m_inventaire.add(e);
            return true;

        }
        return false;
    }


    @Override
    public boolean estPerso(){
        return true;
    }
    @Override
    public String getIdentificationEntite(){
        return this.m_nom;
    }

    // Getters
    public String getNom() {
        return this.m_nom;
    }

    public Armes getArmeEquipee(){
        return m_armeEquipee;
    }

    public int getClasseArmure(){
        return this.m_armureEquipee.getClasseArmure();
    }

    public Race getRace() {
        return this.m_race;
    }

    public String getNomRace() {
        return m_race.getNom();
    }

    public Classe getClasse() {
        return this.m_classe;
    }

    public String getNomClasse() {
        return m_classe.getNom();
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

    public void ajouterEquipementInventaire(Equipement e){
        this.m_inventaire.add(e);
    }

    public String afficherInventaire() {
        if (this.m_inventaire.isEmpty()) {
            return "La liste est vide.";
        }

        StringBuilder contenu = new StringBuilder();
        for (int i = 0; i < this.m_inventaire.size(); i++) {
            contenu.append("  [").append(i + 1).append("] ").append(this.m_inventaire.get(i).getNomEquipement());
        }
        return contenu.toString();
    }



    @Override
    public String toString() {
        return "Nom : " + this.m_nom +
                ", \n\tRace : " + (this.m_race).getNom() +
                ", \n\tClasse : " + (this.m_classe).getNom() +
                ", \n\tVie : " + this.m_pv + "/" + this.m_pvMax +
                ", \n\tInventaire :" + this.afficherInventaire() +
                ", \n\tForce : " + this.m_force +
                ", \n\tDextérité : " + this.m_dexterite +
                ", \n\tVitesse : " + this.m_vitesse +
                ", \n\tInitiative : " + this.m_initiative +
                "\n";
    }

}
