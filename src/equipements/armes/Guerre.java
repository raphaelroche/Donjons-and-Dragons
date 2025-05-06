package equipements.armes;

public abstract class Guerre extends CAC{
    public Guerre() {
        this.m_degats = des.lancerDes(1, 6);
    }
}
