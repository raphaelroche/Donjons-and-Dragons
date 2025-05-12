package placable.equipements.armes;

public abstract class Distance extends Armes{
    public Distance(int portee) {

        super(portee);
        this.m_changeStat = false;
    }
}
