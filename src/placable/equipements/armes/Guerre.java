package placable.equipements.armes;

public abstract class Guerre extends CAC{
    public Guerre() {

        this.m_degats = 0;
        this.m_changeStat = true;
    }

    public Guerre(int x, int y) {
        super(x, y);
        this.m_degats = 0;
        this.m_changeStat = true;
    }

    @Override
    public void determinerDegat(){
        this.m_degats = des.lancerDes(1, 8);
        if(this.estEnchanter){
            this.m_degats+=1;
        }
    }
}
