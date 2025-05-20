package placable.equipements.armes;

import des.Des;
import donjons.Donjon;
import placable.entites.personnages.Personnage;

public class Fronde extends Distance{
    public Fronde() {
        super(6);
        this.m_degats = 0;
        this.m_nom = "fronde";

    }

    public Fronde(int x, int y) {
        super(6, x, y);
        this.m_degats = 0;
        this.m_nom = "fronde";
    }

    @Override
    public void determinerDegat(){
        this.m_degats = des.lancerDes(1, 4);
        if(this.estEnchanter){
            this.m_degats+=1;
        }
    }
}
