package placable.entites.personnages;

import donjons.Donjon;
import exception.ArmureException;
import exception.PorteeException;
import placable.CaseVide;
import placable.Placable;
import placable.entites.Entite;
import placable.entites.monstres.Monstre;
import placable.entites.personnages.enums.TypeClasse;
import placable.entites.personnages.enums.TypeRace;
import placable.equipements.Equipement;
import placable.equipements.armes.*;
import placable.equipements.armures.Armures;
import placable.equipements.armures.CotteDeMailles;
import placable.equipements.armures.Ecailles;
import placable.entites.personnages.classes.*;
import placable.entites.personnages.races.*;
import des.Des;
import sorts.*;


import java.lang.reflect.Type;
import java.util.ArrayList;


public class Personnage extends Entite {
    protected String m_nom;
    private Race m_race;
    private Classe m_classe;
    private final Des des;

    private final ArrayList<Equipement> m_inventaire;
    private final ArrayList<Sort> m_sorts;
    private Armes m_armeEquipee;
    private Armures m_armureEquipee;

    public Personnage(String nom, TypeRace race, TypeClasse classe, int x, int y) {
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


    private void attribuerRaceClasse(TypeRace race, TypeClasse classe) {
        switch(race) {      //attribue la race
            case HUMAIN:
                this.m_race = new Humain();
                this.m_race.initialiser(this);
                break;
            case NAIN:
                this.m_race = new Nain();
                this.m_race.initialiser(this);
                break;
            case ELFE:
                this.m_race = new Elfe();
                this.m_race.initialiser(this);
                break;
            case HALFELIN:
                this.m_race = new Halfelin();
                this.m_race.initialiser(this);
                break;
            default:
                break;
        }
        switch(classe) {      //attribue la classe, et ajoute ses objets dans l'inventaire
            case CLERC:
                this.m_classe = new Clerc();
                this.m_classe.initialiser(this);
                this.ajouterEquipementInventaire(new Masse());
                this.ajouterEquipementInventaire(new Ecailles());
                this.ajouterEquipementInventaire(new Arbalete());
                this.m_sorts.add(new Guerison());
                break;
            case GUERRIER:
                this.m_classe = new Guerrier();
                this.m_classe.initialiser(this);
                this.ajouterEquipementInventaire(new CotteDeMailles());
                this.ajouterEquipementInventaire(new EpeeLongue());
                this.ajouterEquipementInventaire(new Arbalete());
                break;
            case MAGICIEN:
                this.m_classe = new Magicien();
                this.m_classe.initialiser(this);
                this.ajouterEquipementInventaire( new Baton());
                this.ajouterEquipementInventaire( new Fronde());
                this.m_sorts.add(new Guerison());
                this.m_sorts.add(new BoogieWoogie());
                this.m_sorts.add(new ArmeMagique());
                break;
            case ROUBLARD:
                this.m_classe = new Roublard();
                this.m_classe.initialiser(this);
                this.ajouterEquipementInventaire(new Rapiere());
                this.ajouterEquipementInventaire(new Arc());
                break;
            default:
                break;
        }
    }

    @Override
    public int getDegats(){
        return this.m_armeEquipee.getDegats();
    }
    public boolean estClerc(){
        return this.m_classe.estClerc();
    }
    public boolean estMagicien(){
        return this.m_classe.estMagicien();
    }

    public boolean Guerir(int x, int y, Donjon d){
        boolean heal = false;
        Placable caseTableau = d.getCarte()[x-1][y-1].getFirst();
        if(caseTableau.estEntite()){
            if(((Entite)caseTableau).estPerso()){
                Personnage p = (Personnage)caseTableau;
                if(this.m_classe.estClerc() || this.m_classe.estMagicien()){
                    ContextSort cible = new ContextSort(p);
                    heal =  this.m_sorts.getFirst().lancerSort(cible);
                }
            }
        }

        return heal;
    }

    public boolean echangerPosition(int x1, int y1, int x2, int y2, Donjon d){
        boolean echange = false;
        Placable caseTableau1 = d.getCarte()[x1-1][y1-1].getFirst();
        Placable caseTableau2 = d.getCarte()[x2-1][y2-1].getFirst();
        if(caseTableau1.estEntite() && caseTableau2.estEntite()){
            Entite e1 = (Entite)caseTableau1;
            Entite e2 = (Entite)caseTableau2;
            if(this.m_classe.estMagicien()){
                ContextSort cible = new ContextSort(e1,e2);
                echange = this.m_sorts.get(1).lancerSort(cible);
                d.positionnerEmplacementVide(x1-1, y1-1);
                d.positionnerEmplacementVide(x2-1, y2-1);
                d.positionnerElementCarte(e1);
                d.positionnerElementCarte(e2);
            }
        }

        return echange;
    }

    public boolean enchanterArme(Armes a){
        boolean enchanter = false;
        if(this.m_classe.estMagicien() && !a.estEnchanter()){
            ContextSort armeAEnchanter = new ContextSort(a);
            enchanter = this.m_sorts.get(2).lancerSort(armeAEnchanter);
        }
        return enchanter;
    }

    @Override
    public boolean attaquer(int x, int y, Donjon d)throws ArmureException, PorteeException {
        Placable p = d.getCarte()[x-1][y-1].getFirst();
        if(p.estEntite()){
            if(((Entite) p).estMonstre()){
                Monstre cible = (Monstre)p;
                int dX = Math.abs(this.m_positionX - (x-1));
                int dY = Math.abs(this.m_positionY - (y-1));
                int distanceJoueurCible = Math.max(dX, dY);//on utilise math.max pour avoir les diagonal = 1

                if(this.m_armeEquipee != null){
                    if((this.m_armeEquipee.getPortee()) >=distanceJoueurCible){
                        int degat = des.lancerDes(1, 20);
                        if(this.m_armeEquipee.getPortee() == 1){
                            degat += this.m_force;
                        }
                        else{
                            degat += this.m_dexterite;
                        }
                        if(this.m_armeEquipee.estEnchanter()){
                            degat +=1;
                        }
                        if(degat >  cible.getclasseArmure()){
                            this.m_armeEquipee.determinerDegat();
                            cible.ajusterPv(-(this.m_armeEquipee.getDegats()));
                            if(cible.getPv()<=0){
                                tuerCible(d, x-1, y-1);
                            }
                            return true;
                        }
                        else{
                            throw new ArmureException();
                        }
                    }
                    else{
                        throw new PorteeException();
                    }
                }

            }
        }

        return false;

    }

    public void sEquiper(Equipement e){
        if(e.estArme()){
            if(this.m_armeEquipee != null){
                this.m_inventaire.add(this.m_armeEquipee);
            }
            this.m_armeEquipee= (Armes)e;
            this.m_inventaire.remove(this.m_armeEquipee);

            if(m_armeEquipee.getChangeStat()){
                this.m_vitesse -= 2;
                this.m_force += 4;
            }
        }
        else{
            if(this.m_armureEquipee != null){
                this.m_inventaire.add(this.m_armureEquipee);

            }
            this.m_armureEquipee= (Armures)e;
            this.m_inventaire.remove(this.m_armureEquipee);

            if(m_armureEquipee.getChangeStat()){
                this.m_vitesse -= 4;
            }
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
        if(this.m_armureEquipee!=null){
            return this.m_armureEquipee.getClasseArmure();
        }
       return 0;
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

    public int getEfficaciteGuerison(){
        return ((Guerison)this.m_sorts.getFirst()).getEfficacite();
    }

    // Setters

    public void ajusterVitesse(int valeur) {
        this.m_vitesse += valeur;
        if (this.m_vitesse < 0) {
            this.m_vitesse = 0;
        }
    }

    public ArrayList<Equipement> getInventaire() {
        return this.m_inventaire;
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
            contenu.append("\n");
            contenu.append("  [").append(i + 1).append("] ").append(this.m_inventaire.get(i));
        }
        contenu.append("\n");
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
