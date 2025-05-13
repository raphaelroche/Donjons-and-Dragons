package placable.entites;

import donjons.Donjon;
import placable.Placable;
import placable.obstacle.Obstacle;


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
        d.positionnerEmplacementVide(this.m_positionX, this.m_positionY);
        Placable[][] carte = d.getCarte();

        //label pour quitter directement le for et le switch d'un coup
        quitterBoucle:
        for(int i = 0; i < distance; i++){
            switch(direction){
                case 1:
                    //en haut
                    if(this.m_positionY - 1 < 0){
                        setLocation(this.m_positionX, 0);
                    } else if (carte[this.m_positionX] [this.m_positionY - 1].equals(new Obstacle(d))) {
                       break quitterBoucle;
                    } else{
                        setLocation(this.m_positionX, this.m_positionY - 1);
                    }

                    break;
                case 2:
                    //en bas
                    if(this.m_positionY + 1 >= d.getHauteur()){
                        setLocation(this.m_positionX, d.getHauteur()-1);
                    } else if (carte[this.m_positionX] [this.m_positionY + 1].equals(new Obstacle(d))) {
                        break quitterBoucle;
                    } else{
                        setLocation(this.m_positionX, this.m_positionY + 1);
                    }

                    break;
                case 3:
                    //gauche
                    if(this.m_positionX - 1 < 0){
                        setLocation(0, this.m_positionY);
                    } else if (carte[this.m_positionX - 1] [this.m_positionY].equals(new Obstacle(d))) {
                        break quitterBoucle;
                    } else{
                        setLocation(this.m_positionX - 1, this.m_positionY);
                    }

                    break;
                case 4:
                    //droite
                    if(this.m_positionX + 1 >= d.getLargeur()){
                        setLocation(d.getLargeur()-1, this.m_positionY);
                    } else if (carte[this.m_positionX + 1] [this.m_positionY].equals(new Obstacle(d))) {
                        break quitterBoucle;
                    } else{
                        setLocation(this.m_positionX + 1, this.m_positionY);
                    }

                    break;
            }

        }

        //mettre a jour la carte du donjon
        d.positionnerElementCarte(this);

    }
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
}
