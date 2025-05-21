package placable.entites.monstres;

import des.Des;
import donjons.Donjon;
import placable.entites.Entite;
import placable.entites.personnages.Personnage;

import java.util.ArrayList;

public class Monstre extends Entite {
    private String m_espece;
    private int m_numero;
    private String m_nomAttaque;
    private int m_portee;
    private int m_degats;
    private int m_classeArmure;
    private Des des;

    private static ArrayList<Monstre> monstres;

    //par default
    public Monstre(String espece) {
        if(monstres == null) {
            monstres = new ArrayList<>();
        }

        this.m_espece = espece;
        monstres.add(this);
        if(monstres.size() > 1) {
            this.m_numero = monstres.indexOf(this)+1;
            this.m_nomAffiche = "X " + this.m_numero+" ";
        }
        else{
            this.m_nomAffiche = " X ";
        }




    }

    public Monstre(String espece, int portee,int pv, String nomattaque,  int classeArmure, int force, int dexterite, int initiative, int x, int y) {
        des = new Des();

        if(monstres == null) {
            monstres = new ArrayList<>();
        }

        this.m_espece = espece;
        this.m_portee = portee;
        this.m_pv = pv;
        this.m_pvMax = pv;
        this.m_nomAttaque = nomattaque;
        //degat min = 4, degat max = 10
        this.m_degats = 4 + des.lancerDes(1, 6);
        this.m_classeArmure = classeArmure;
        this.m_initiative = initiative;
        monstres.add(this);
        setLocation(x-1, y-1);

        if(monstres.size() > 1) {
            this.m_numero = monstres.indexOf(this)+1;
            this.m_nomAffiche = "X" + this.m_numero+" ";
        }
        else{
            this.m_nomAffiche = " X ";
        }
        if(portee > 1){
            this.m_force = 0;
            this.m_dexterite = dexterite;
        }
        else{
            this.m_force = force;
            this.m_dexterite = 0;
        }


    }
    public int getclasseArmure(){
        return this.m_classeArmure;
    }

    @Override
    public void ajusterPv(int valeur) {
        this.m_pv += valeur;
        if (this.m_pv < 0) {
            this.m_pv = 0;
            monstres.remove(this);
        }
    }
    @Override
    public boolean estMonstre(){
        return true;
    }
    @Override
    public String getIdentificationEntite(){
        return this.m_espece + this.m_numero;
    }
    public boolean attaquer(Personnage cible, String[][] carte){


        int dX = this.m_positionX - cible.getPositionX();
        int dY = this.m_positionY - cible.getPositionY();

        double distanceMonstreCible = Math.sqrt(dX * dX + dY * dY);
        if(this.m_portee >= distanceMonstreCible){
            if(this.m_degats > cible.getClasseArmure()){
                cible.ajusterPv(-(this.m_degats));
                return true;
            }
        }
        return false;
    }

    public String getEspece() {
        return m_espece;
    }
    public int getNumero(){
        return this.m_numero;
    }
}


