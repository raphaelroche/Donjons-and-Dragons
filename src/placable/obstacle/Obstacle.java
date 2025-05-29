package placable.obstacle;

import des.Des;
import placable.Placable;

public class Obstacle implements Placable {
    private int m_positionX;
    private int m_positionY;
    private final String m_nomAffiche;

    public Obstacle(int x, int y){
        this.m_nomAffiche = "[ ]";
        this.setLocation(x-1, y-1);
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

    @Override
    public boolean estObstacle(){
        return true;
    }
}
