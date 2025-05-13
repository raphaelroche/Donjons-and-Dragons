package placable.equipements;

import des.Des;
import donjons.Donjon;
import placable.Placable;

public abstract class Equipement implements Placable {
    protected String m_nom;
    protected int m_positionX;
    protected int m_positionY;
    protected String m_nomAffiche;

    public Equipement(){
        this.m_nomAffiche = " * ";
    }

    @Override
    public void setLocation(int x, int y){
        this.m_positionX = x;
        this.m_positionY = y;
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
        return this.m_positionX;
    }
    @Override
    public int getPositionY(){
        return this.m_positionY;
    }
}
