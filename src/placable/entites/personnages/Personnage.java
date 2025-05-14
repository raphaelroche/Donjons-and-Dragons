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
import placable.obstacle.Obstacle;

import java.util.ArrayList;

public class Personnage extends Entite {
    protected String m_nom;
    private Race m_race;
    private Classe m_classe;
    private Des des;

    private ArrayList<Equipement> m_inventaire;
    private Armes[] m_armeEquipee;
    private Armures[] m_armureEquipee;

    public Personnage(String nom, int race, int classe, int x, int y) {
        this.m_nom = nom;
        setLocation(x-1, y-1);
        this.m_inventaire = new ArrayList<Equipement>();
        this.m_armeEquipee = new Armes[1];
        this.m_armureEquipee = new Armures[1];
        this.m_nomAffiche = this.m_nom.substring(0,3);
        this.attribuerRaceClasse(race, classe); //utilise un int pour désigner
        des = new Des();
        this.m_force = 3 + des.lancerDes(4,4);
        this.m_dexterite = 3 + des.lancerDes(4,4);
        this.m_vitesse = 3 + des.lancerDes(4,4);
        this.m_initiative = 3 + des.lancerDes(4,4);
    }
    public Personnage(String nom, int race, int classe, Donjon d) {
        this.m_nom = nom;
        des = new Des();
        setLocation(des.lancerDes(1, d.getHauteur()-1), des.lancerDes(1, d.getLargeur()-1));
        this.m_inventaire = new ArrayList<Equipement>();
        this.m_armeEquipee = new Armes[1];
        this.m_armureEquipee = new Armures[1];
        this.m_nomAffiche = this.m_nom.substring(0,3);
        this.attribuerRaceClasse(race, classe); //utilise un int pour désigner
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
                new Masse(this);
                new Ecailles(this);
                new Arbalete(this);
                break;
            case 2:
                this.m_classe = new Guerrier(this);
                new CotteDeMailles(this);
                new EpeeLongue(this);
                new Arbalete(this);
                break;
            case 3:
                this.m_classe = new Magicien(this);
                new Baton(this);
                new Fronde(this);
                break;
            case 4:
                this.m_classe = new Roublard(this);
                new Rapiere(this);
                new Arc(this);
                break;
            default:
                break;
        }
    }


    public Armes getArmeEquipee(){
        return m_armeEquipee[0];
    }



    public int getClasseArmure(){
        return this.m_armureEquipee[0].getClasseArmure();
    }

    public boolean attaquer(Monstre cible){



        int dX = this.m_positionX - cible.getPositionX();
        int dY = this.m_positionY - cible.getPositionY();

        double distanceJoueurCible = Math.sqrt(dX * dX + dY * dY);


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
        if(this.m_armureEquipee[0] != null){
            this.m_inventaire.add(this.m_armureEquipee[0]);
            this.m_armureEquipee[0] = null;
        }
        this.m_armureEquipee[0] = a;
        this.m_inventaire.remove(this.m_armureEquipee[0]);

        if(m_armureEquipee[0].getChangeStat()){
            this.m_vitesse -= 4;
        }
    }
    public void sEquiperArme(Armes a){

        if(this.m_armeEquipee[0] != null){
            this.m_inventaire.add(this.m_armeEquipee[0]);
            this.m_armeEquipee[0] = null;
        }
        this.m_armeEquipee[0] = a;
        this.m_inventaire.remove(this.m_armeEquipee[0]);

        if(m_armeEquipee[0].getChangeStat()){
            this.m_vitesse -= 2;
            this.m_force += 4;
        }

    }

    public boolean ramasserEquipement(Equipement e, ArrayList<Placable>[][] carte, Donjon d){


        if(contientEquipement(carte[this.m_positionX][this.m_positionY])){

            carte[this.m_positionX][this.m_positionY].clear();
            carte[this.m_positionX][this.m_positionY].add(this);
            this.m_inventaire.add(e);
            return true;

        }
        return false;
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

    public void ajouterEquipementInventaire(Equipement e){
        this.m_inventaire.add(e);
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

    public static boolean contientEquipement(ArrayList<Placable> liste) {
        for (Placable p : liste) {
            if (p instanceof Equipement) {
                return true;
            }
        }
        return false;
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
