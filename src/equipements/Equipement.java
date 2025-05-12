package equipements;

import des.Des;
import placable.Placable;

public abstract class Equipement implements Placable {
    protected String m_nom;
    protected int PositionX;
    protected int positionY;
    protected String m_nomAffiche;
    public Equipement() {
        this.m_nomAffiche = "*";
    }

    public String getNomEquipement() {
        return this.m_nom;
    }

    @Override
    public String getNomAffiche(){
        return this.m_nomAffiche;
    }

    @Override
    public int getPositionX(){
        return this.PositionX;
    }
    @Override
    public int getPositionY(){
        return this.positionY;
    }
}
