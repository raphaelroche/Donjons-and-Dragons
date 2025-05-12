package equipements;

import des.Des;
import placable.Placable;

public abstract class Equipement implements Placable {
    protected String m_nom;
    protected int PositionX;
    protected int positionY;
    public Equipement() {
    }

    public String getNomEquipement() {
        return this.m_nom;
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
