package placable.entites.personnages.classes;

import placable.entites.personnages.Personnage;

public class Roublard extends Classe{
    public Roublard() {
       this.m_pv = 16;
       this.m_nomClasse = "Roublard";
    }
    @Override
    public void initialiser(Personnage p){
        p.setPv(this.m_pv);
    }
}
