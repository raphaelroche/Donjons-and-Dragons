package equipements;

import des.Des;

public abstract class Equipement {
    protected String m_nom;
    public Equipement() {
    }

    public String getNomEquipement() {
        return this.m_nom;
    }
}
