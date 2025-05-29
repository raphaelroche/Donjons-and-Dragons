package placable.entites.monstres;

import des.Des;
import donjons.Donjon;
import placable.Placable;
import placable.entites.Entite;
import placable.entites.personnages.Personnage;

import java.util.ArrayList;

public class Monstre extends Entite {
    private final String m_espece;
    private int m_numero;
    private final String m_nomAttaque;
    private final int m_portee;
    private final int m_degats;
    private final int m_classeArmure;
    private final Des des;

    private static ArrayList<Monstre> monstres;

    //par default
    public Monstre(String espece) {
        this.des = new Des();
        if(monstres == null) {
            monstres = new ArrayList<>();
        }

        this.m_espece = espece;
        monstres.add(this);
        if(monstres.size() > 1) {
            this.m_numero = monstres.indexOf(this)+1;
            this.m_nomAffiche = "X" + this.m_numero+" ";
        }
        else{
            this.m_nomAffiche = " X ";
        }

        this.m_portee = des.lancerDes(3, 5);
        if(m_portee > 1){
            this.m_force = 0;
            this.m_dexterite = des.lancerDes(3, 6);
            this.m_nomAttaque = "attaque de feu";
        }
        else{
            this.m_force =  this.des.lancerDes(4, 6);
            this.m_dexterite = 0;
            this.m_nomAttaque = "croc du dragon";
        }
        this.m_classeArmure = des.lancerDes(3, 6);
        this.m_degats = 3 + des.lancerDes(1, 4);
        this.m_pvMax = des.lancerDes(4, 7);
        this.m_pv = m_pvMax;
        this.m_initiative = des.lancerDes(3, 6);
        this.m_vitesse = des.lancerDes(3, 6);
    }

    public Monstre(String espece, int portee,int pv,int vitesse, String nomattaque,  int classeArmure, int force, int dexterite, int initiative,  int x, int y) {
        des = new Des();

        if(monstres == null) {
            monstres = new ArrayList<>();
        }
        this.m_vitesse = vitesse;
        this.m_espece = espece;
        this.m_portee = portee;
        this.m_pvMax = pv;
        this.m_pv = m_pvMax;
        this.m_nomAttaque = nomattaque;
        //degat min = 4, degat max = 10
        this.m_degats = 3 + des.lancerDes(1, 4);
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

    @Override
    public boolean attaquer(int x, int y,Donjon d) {
        Placable p = d.getCarte()[x-1][y-1].getFirst();
        if(p.estEntite()){
            if(((Entite)p).estPerso()){
                Personnage cible = (Personnage)p;
                int dX = Math.abs(this.m_positionX - (x-1));
                int dY = Math.abs(this.m_positionY - (y-1));
                int distanceMonstreCible = Math.max(dX, dY);
                if(this.m_portee >= distanceMonstreCible){
                    if(this.m_degats > cible.getClasseArmure()){
                        cible.ajusterPv(-(this.m_degats));
                        if(cible.getPv()<=0){
                            tuerCible(d, x-1, y-1);
                        }
                        return true;
                    }
                }
            }
        }

        return false;
    }
    @Override
    public int getDegats(){
        return this.m_degats;
    }


    public String getNomAttaque(){
        return this.m_nomAttaque;
    }
    public String getEspece() {
        return m_espece;
    }
}


