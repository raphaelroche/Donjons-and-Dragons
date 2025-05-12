package donjons;

public class Donjon {
    private final String[][] m_carte;
    private int m_hauteur;
    private int m_largeur;

    public Donjon(){
        this(15, 15);
    }

    public Donjon(int m_hauteur, int m_largeur){
        this.m_carte = new String[m_hauteur][m_largeur];
    }

    public void afficherDonjon(){

    }

    public void positionnerElementCarte(){

    }

    public String[][] getCarte(){
        return this.m_carte;
    }
}
