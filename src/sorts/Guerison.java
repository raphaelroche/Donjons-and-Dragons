package sorts;

import des.Des;
import placable.entites.personnages.Personnage;

public class Guerison extends Sort {
    private int m_efficacite;
    private Des des;

    public Guerison(){
        this.des = new Des();
    }

    @Override
    public void lancerSort(ContextSort c){
        this.m_efficacite=des.lancerDes(1,10);

    }

    public int getEfficacite() {
        return this.m_efficacite;
    }
}
