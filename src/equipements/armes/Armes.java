package equipements.armes;

import des.Des;
import equipements.Equipement;

public abstract class Armes extends Equipement {
    protected int m_degats;
    protected int m_portee;
    protected Des des;

    public Armes(int portee) {
        this.m_portee = portee;
        this.des = new Des();
    }
}
