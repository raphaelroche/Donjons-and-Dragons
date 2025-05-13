package placable.equipements.armes;

import des.Des;
import donjons.Donjon;

public class Arc extends Distance{
    public Arc() {
        super(16);
        this.m_degats = 0;
        this.m_nom = "arc court";
    }

    public Arc(int x, int y) {
        super(16, x, y);
        this.m_degats = 0;
        this.m_nom = "arc court";
    }

    public Arc(Donjon d, Des des) {
        super(16, des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
        this.m_degats = 0;
        this.m_nom = "arc court";
    }
    public Arc(Donjon d){
        this(d, new Des());
    }

    @Override
    public void determinerDegat(){
        this.m_degats = des.lancerDes(1, 6);
    }
}
