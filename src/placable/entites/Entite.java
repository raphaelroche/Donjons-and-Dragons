package placable.entites;

import des.Des;
import donjons.Donjon;
import exception.CaseTtropLointaineException;
import maitredujeu.MaitreDuJeu;
import placable.Placable;

import java.util.ArrayList;


public abstract class Entite implements Placable {
    protected int m_pv;
    protected int m_pvMax;
    protected int m_force;
    protected int m_dexterite;
    protected int m_initiative;
    protected int m_positionX;
    protected int m_positionY;
    protected int m_vitesse;
    protected String m_nomAffiche;

    protected int scoreInitiative;


    @Override
    public void setLocation(int x, int y){
        this.m_positionX = x;
        this.m_positionY = y;
    }

    @Override
    public int getPositionX(){
        return m_positionX;
    }

    @Override
    public int getPositionY(){
        return m_positionY;
    }

    public boolean seDeplacer(int x, int y, Donjon d){
        int distanceMax = this.m_vitesse / 3;
        boolean etrePlace;
        //mettre un point sur la case ou se trouvai le perso

        ArrayList<Placable>[][] carte = d.getCarte();


        //si la case contient un equipement et que le joueur ne la pas recuperé alors on met l'equipement a l'index 0 pour l'afficher
        if(contientEquipement(carte[this.m_positionX][this.m_positionY])){
            d.decalerAGauche(carte[this.m_positionX][this.m_positionY]);
        }
        //sinon on affiche une case vide a l'endroit ou etait l'entite
        else{
            d.positionnerEmplacementVide(this.m_positionX, this.m_positionY);
        }

        int dX = Math.abs(this.m_positionX - x);
        int dY = Math.abs(this.m_positionY - y);
        int distanceDeplacement = Math.max(dX, dY);

       if(distanceDeplacement <= distanceMax){
           this.setLocation(x, y);
           //mettre a jour la carte du donjon
           etrePlace = d.positionnerElementCarte(this);

       }
       else{
           throw new CaseTtropLointaineException();
       }

        return etrePlace;

    }
    public void tuerCible(Donjon d, Entite e){
        MaitreDuJeu.tuerCible(d, e);
    }
    public void scorePourCommencer(){
        Des des = new Des();
        scoreInitiative = des.lancerDes(1,20) + this.m_initiative;
    }

    public int getScoreInitiative(){
        return this.scoreInitiative;
    }
    public abstract int getDegats();
    public abstract boolean attaquer(int x, int y, Donjon d);


    public boolean estMonstre(){
        return false;
    }
    public boolean estPerso(){
        return false;
    }

    public abstract String getIdentificationEntite();

    @Override
    public String getNomAffiche(){
        return this.m_nomAffiche;
    }


    public int getPv() {
        return this.m_pv;
    }

    public int getVitesse(){
        return this.m_vitesse;
    }

    public void setPv(int pv) {
        this.m_pv = pv; this.m_pvMax = pv;
    }


    //fonctions pour ajuster stat : à chaque ajustement, vérifie si positif, sinon met à 0

    public void ajusterPv(int valeur) {
        this.m_pv += valeur;
        if (this.m_pv < 0) {
            this.m_pv = 0;
        }
        if(this.m_pv>this.m_pvMax){
            this.m_pv = this.m_pvMax;
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

    public static boolean contientEquipement(ArrayList<Placable> liste) {
        for (Placable p : liste) {
            if (p != null && p.estEquipement()) {
                return true;
            }
        }
        return false;
    }

    public int getPvMax() {
        return this.m_pvMax;
    }

    @Override
    public boolean estEntite(){
        return true;
    }
}
