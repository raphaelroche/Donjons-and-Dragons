package placable.entites;

import des.Des;
import donjons.Donjon;
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

    public void seDeplacer(int direction, Donjon d){
        int distance = this.m_vitesse / 3;

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
        int newX = this.m_positionX;
        int newY = this.m_positionY;

        //label pour quitter directement le for et le switch d'un coup
        quitterBoucle:
        for(int i = 0; i < distance; i++){
            switch(direction){
                case 1: // Haut
                    if (newY - 1 < 0) break quitterBoucle;
                    if (contientObstacle(carte[newX][newY - 1])) break quitterBoucle;
                    if(contientEntite(carte[newX][newY - 1])) break quitterBoucle;
                    newY--;
                    break;


                case 2: // Bas
                    if (newY + 1 >= d.getHauteur()-1) break quitterBoucle;
                    if (contientObstacle(carte[newX][newY + 1])) break quitterBoucle;
                    if(contientEntite(carte[newX][newY+1])) break quitterBoucle;
                    newY++;
                    break;


                case 3: // Gauche
                    if (newX - 1 < 0) break quitterBoucle;
                    if (contientObstacle(carte[newX - 1][newY])) break quitterBoucle;
                    if(contientEntite(carte[newX - 1][newY])) break quitterBoucle;
                    newX--;
                    break;

                case 4: // Droite
                    if (newX + 1 >= d.getLargeur()-1) break quitterBoucle;
                    if (contientObstacle(carte[newX + 1][newY])) break quitterBoucle;
                    if(contientEntite(carte[newX + 1][newY])) break quitterBoucle;
                    newX++;
                    break;

                default:
                    // Direction invalide, on ne fait rien
                    return;
            }
        }
        this.setLocation(newX, newY);
        //mettre a jour la carte du donjon
        d.positionnerElementCarte(this);

    }
    public void tuerCible(Donjon d, int x, int y){
        ArrayList<Placable> caseCible = d.getCarte()[x][y];
        if(caseCible.get(1) == null){

            d.positionnerEmplacementVide(x, y);
        }
        else{
            d.decalerAGauche(caseCible);
        }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return this.getClass() == obj.getClass();
        //verifie si this est de la meme classe que obj
    }

    public static boolean contientObstacle(ArrayList<Placable> liste) {
        for (Placable p : liste) {
            if (p != null && p.estObstacle()) {
                return true;
            }
        }
        return false;
    }
    public static boolean contientEquipement(ArrayList<Placable> liste) {
        for (Placable p : liste) {
            if (p != null && p.estEquipement()) {
                return true;
            }
        }
        return false;
    }

    public static boolean contientEntite(ArrayList<Placable> liste) {
        for (Placable p : liste) {
            if (p != null && p.estEntite()) {
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
