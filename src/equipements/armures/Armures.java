package equipements.armures;

import equipements.Equipement;

public abstract class Armures extends Equipement {
    protected int m_classe;
    public Armures(int classe) {
        this.m_classe = classe;
    }

    public int getClasseArmure() {
        return this.m_classe;
    }
}
