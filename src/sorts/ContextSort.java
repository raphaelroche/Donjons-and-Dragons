package sorts;

import placable.entites.Entite;
import placable.entites.personnages.Personnage;
import placable.equipements.armes.Armes;

public class ContextSort {
    private Entite lanceur;
    private Entite cible;
    private Armes armes;


    public ContextSort(Entite lanceur, Entite cible, Armes armes) {
        this.lanceur = lanceur;
        this.cible = cible;
        this.armes = armes;
    }

    public ContextSort(Entite cible) {
        this.cible = cible;

    }

    public ContextSort(Armes armes) {
      this.armes = armes;

    }
}
