package placable.entites.personnages.classes;

import placable.entites.personnages.Personnage;

public abstract class Classe {
    protected String m_nomClasse;
    protected int m_pv;


    public Classe(String nomClasse, int pv) {
        m_nomClasse = nomClasse;
        m_pv = pv;
    }
    public String getNom() {
        return m_nomClasse;
    }

    public boolean estMagicien(){
        return false;
    }
    public boolean estClerc(){
        return false;
    }

    public int getPv() {
        return m_pv;
    }
}
