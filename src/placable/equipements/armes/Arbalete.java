package placable.equipements.armes;

import donjons.Donjon;

//dans l'inventaire
public class Arbalete extends Distance{
    public Arbalete() {
        super(16);
        this.m_degats = 0;
        this.m_nom = "arbalète";
    }

    //sur la carte
    public Arbalete(int x, int y){
        super(16, x, y);
        this.m_degats = 0;
        this.m_nom = "arbalète";
    }

    //sur la carte par defaut
    public Arbalete(Donjon d){
        super(16);
        this.setLocation(des.lancerDes(1,d.getLargeur()-1), des.lancerDes(1,d.getHauteur()-1));
        this.m_degats = 0;
        this.m_nom = "arbalète";
    }

    @Override
    public void determinerDegat(){
        this.m_degats = des.lancerDes(1, 8);
    }
}
