package placable.equipements.armes;

public abstract class Courantes extends CAC{
    public Courantes() {

        this.m_degats = 0;
        this.m_changeStat = false;
    }

    public Courantes(int x, int y){
        super(x, y);
        this.m_degats = 0;
        this.m_changeStat = false;
    }

    @Override
    public void determinerDegat(){
        this.m_degats = des.lancerDes(1, 6);
        if(this.estEnchanter){
            this.m_degats+=1;
        }
    }
}
