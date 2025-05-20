package sorts;

import placable.entites.Entite;
import placable.equipements.armes.Armes;

public class ContextSort {
    private Entite m_cible1;
    private Entite m_cible2;
    private Armes m_arme;


    public ContextSort(Entite cible1, Entite cible2) {
        this.m_cible1 = cible1;
        this.m_cible2 = cible2;
    }

    public ContextSort(Entite cible) {
        this.m_cible1 = cible;

    }

    public ContextSort(Armes armes) {
      this.m_arme = armes;

    }

    //guerison
    public void setPV(int pv){
        this.m_cible1.ajusterPv(pv);
    }


    //boogie woogie
    public int getXcible1(){
        return this.m_cible1.getPositionX();
    }
    public int getYcible1(){
        return this.m_cible1.getPositionY();
    }

    public int getXcible2(){
        return this.m_cible2.getPositionX();
    }

    public int getYcible2(){
        return this.m_cible2.getPositionY();
    }
    public void setCible1(int x, int y){
        this.m_cible1.setLocation(x,y);
    }
    public void setCible2(int x, int y){
        this.m_cible2.setLocation(x,y);
    }

    //arme magique
    public void setArme(boolean b){
        this.m_arme.setEnchantement(b);
    }
}
