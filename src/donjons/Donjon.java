package donjons;

public class Donjon {
    private String[] m_alphabet;
    private final String[][] m_carte;
    private int m_hauteur;
    private int m_largeur;

    public Donjon(){
        this(15, 15);
    }

    public Donjon(int hauteur, int largeur){
        this.m_hauteur = hauteur;
        this.m_largeur = largeur;
        this.m_carte = new String[m_largeur][m_hauteur];
        this.m_alphabet = new String[] {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };
    }

    public void afficherDonjon(){
        System.out.print("\t");
        for (int i = 0; i < this.m_largeur; i++) {
            System.out.print("\t");
            System.out.print(this.m_alphabet[i] + " ");
        }
    }

    public void positionnerElementCarte(){

    }

    public String[][] getCarte(){
        return this.m_carte;
    }
}
