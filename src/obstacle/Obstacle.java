package obstacle;

import des.Des;
import donjons.Donjon;
import placable.Placable;

public class Obstacle implements Placable {
    private int m_positionX;
    private int m_positionY;
    private String m_look;
    private Des des;

    public Obstacle(int x, int y){
        this.m_look = "[ ]";
        this.m_positionX = x;
        this.m_positionY = y;
    }

    public Obstacle(Donjon d){
        this.des = new Des();
        this.m_look = "[ ]";
        this.m_positionX = des.lancerDes(1, );


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
