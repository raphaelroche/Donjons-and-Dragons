package placable.equipements.armures;

public abstract class Legeres extends Armures{
    public Legeres(int classe) {

        super(classe);
        m_changeStat = false;
    }

    public Legeres(int classe, int x, int y){
        super(classe, x, y);
        m_changeStat = false;
    }
}
