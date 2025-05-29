package sorts;

import des.Des;

public class Guerison extends Sort {
    private int m_efficacite;
    private final Des des;

    public Guerison(){
        this.des = new Des();
    }

    @Override
    public boolean lancerSort(ContextSort c){
        this.m_efficacite=des.lancerDes(1,10);
        c.setPV(this.m_efficacite);
        return true;

    }

    public int getEfficacite() {
        return this.m_efficacite;
    }
}
