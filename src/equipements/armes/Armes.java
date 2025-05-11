package equipements.armes;

import des.Des;
import equipements.Equipement;

public abstract class Armes extends Equipement {
    protected int m_degats;
    protected int m_portee;
    protected Des des;
    protected boolean m_changeStat;

    public Armes(int portee) {
        this.m_portee = portee;
        this.des = new Des();
    }

    public int getPortee() {
        return this.m_portee;
    }

    public abstract void determinerDegat();

    public int getDegats() {
        return this.m_degats;
    }
    public boolean getChangeStat() {
        return this.m_changeStat;
    }
}
