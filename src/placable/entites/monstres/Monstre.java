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
    public Monstre(Donjon d) {
        monstres = new ArrayList<>();
        this.m_espece = "dragon";
        this.des = new Des();
        monstres.add(this);
        this.m_numero = monstres.indexOf(this)+1;
        this.m_nomAffiche = "D" + this.m_numero;
        setLocation(des.lancerDes(1, d.getLargeur()), des.lancerDes(1, d.getHauteur()));

    }

    public Monstre(String espece, int portee,int pv, String nomattaque,  int classeArmure, int force, int dexterite, int initiative, int x, int y) {
        des = new Des();
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
        setLocation(x, y);

        this.m_nomAffiche = "X";
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

    public void perdrePV(int degats){
        this.m_pv -= degats;
    }

    public int getX(){
        return this.m_positionX;
    }

    public  int getY(){
        return this.m_positionY;
    }




    public boolean attaquer(Personnage cible, String[][] carte){
        int distanceMonstreCible = 0;

        //determiner la distance monstre-cible avec la carte

        if(m_portee <= distanceMonstreCible){
            if(this.m_degats > cible.getClasseArmure()){
                cible.perdrePV(this.m_degats);
                return true;
            }
        }
        return false;
    }





}


