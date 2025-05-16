package placable.entites.personnages.classes;


import placable.entites.personnages.Personnage;

public class Clerc extends Classe{
    public Clerc() {
        this.m_pv = 16;
        this.m_nomClasse = "Clerc";

    }

    @Override
    public void initialiser(Personnage p){
        p.setPv(this.m_pv);
    }
}
