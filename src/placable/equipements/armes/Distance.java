package placable.equipements.armes;

public abstract class Distance extends Armes{
    public Distance(int portee) {

        super(portee);
        this.m_changeStat = false;
    }

    public Distance(int portee, int x, int y){
        super(portee, x, y);
        this.m_changeStat = false;
    }
}
