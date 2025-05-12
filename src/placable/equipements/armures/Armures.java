package placable.equipements.armures;

import placable.equipements.Equipement;

public abstract class Armures extends Equipement {
    protected int m_classe;
    protected boolean m_changeStat;
    public Armures(int classe) {
        this.m_classe = classe;
    }

    public int getClasseArmure() {
        return this.m_classe;
    }
    public boolean getChangeStat() {return this.m_changeStat;}
}
