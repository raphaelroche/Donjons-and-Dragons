package placable.obstacle;

import des.Des;
import donjons.Donjon;
import placable.Placable;

public class Obstacle implements Placable {
    private int m_positionX;
    private int m_positionY;
    private String m_nomAffiche;
    private Des des;

    public Obstacle(int x, int y){
        this.m_nomAffiche = "[ ]";
        this.setLocation(x-1, y-1);
    }
    public Obstacle(Donjon d){
        this.des = new Des();
        this.m_nomAffiche = "[ ]";
        this.m_positionX = des.lancerDes(1, d.getLargeur()-1);
        this.m_positionY = des.lancerDes(1, d.getHauteur()-1);
    }

    @Override
    public void setLocation(int x, int y){
        this.m_positionX = x;
        this.m_positionY = y;
    }

    @Override
    public String getNomAffiche(){
        return this.m_nomAffiche;
    }

    @Override
    public int getPositionX(){
        return m_positionX;
    }

    @Override
    public int getPositionY(){
        return m_positionY;
    }
}
