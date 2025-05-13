package placable.equipements.armures;

import des.Des;
import placable.equipements.Equipement;

public abstract class Armures extends Equipement {
    protected int m_classe;
    protected boolean m_changeStat;
    protected Des des;
    public Armures(int classe) {
        this.m_classe = classe;
        this.des = new Des();
    }

    public Armures(int classe, int x, int y){
        this.m_classe = classe;
        this.setLocation(x, y);
        this.des = new Des();
    }

    public int getClasseArmure() {
        return this.m_classe;
    }
    public boolean getChangeStat() {return this.m_changeStat;}
}
