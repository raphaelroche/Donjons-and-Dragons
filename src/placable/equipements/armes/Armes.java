package placable.equipements.armes;

import des.Des;
import placable.equipements.Equipement;

public abstract class Armes extends Equipement {
    protected int m_degats;
    protected int m_portee;
    protected Des des;
    protected boolean m_changeStat;
    protected boolean estEnchanter;

    public Armes(int portee) {
        this.m_portee = portee;
        this.des = new Des();
        this.estEnchanter = false;
    }

    public Armes(int portee, int x, int y){
        this.m_portee = portee;
        this.des = new Des();
        this.setLocation(x-1,y-1);
        this.estEnchanter = false;
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

    public void setEnchantement(boolean b) {
        this.estEnchanter = b;
    }
}
