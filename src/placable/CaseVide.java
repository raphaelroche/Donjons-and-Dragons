package placable;

import des.Des;

public class CaseVide implements Placable{
    private int m_positionX;
    private int m_positionY;
    private final String m_nomAffiche;
    private Des des;


    public CaseVide(int x, int y){
        this.m_nomAffiche = ".";
        this.m_positionX = x;
        this.m_positionY = y;
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
