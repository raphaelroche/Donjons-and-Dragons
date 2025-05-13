package placable.equipements.armures;

public abstract class Lourdes extends Armures{
    public Lourdes(int classe) {

        super(classe);
        m_changeStat = true;
    }
    public Lourdes(int classe, int x, int y){
        super(classe, x, y);
        m_changeStat = true;
    }
}
