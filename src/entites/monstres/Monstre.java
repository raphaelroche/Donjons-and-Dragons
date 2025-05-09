package entites.monstres;

import entites.Entite;
import entites.personnages.Personnage;

import java.util.ArrayList;

public class Monstre extends Entite {
    private String m_espece;
    private int m_numero;
    private String m_nomAttaque;
    private int m_portee;
    private int m_degats;
    private int m_classeArmure;

    private static ArrayList<Monstre> monstres;

    public int getclasseArmure(){
        return this.m_classeArmure;
    }

    public void perdrePV(int degats){
        this.m_pv -= degats;
    }

    @Override
    public void seDeplacer(){

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

    /*public Monstre() {
        this.m_espece = "dragon";

    }

    public Monstre();
     */
}


