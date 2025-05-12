package donjons;

import placable.Placable;

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
        // Initialiser la carte avec des "."
        for (int x = 0; x < m_largeur; x++) {
            for (int y = 0; y < m_hauteur; y++) {
                positionnerEmplacementVide(x,y);
            }
        }
    }

    public void afficherDonjon(){
        //affichage des lettres
        System.out.print("\t");
        for (int i = 0; i < this.m_largeur; i++) {
            System.out.print("\t");
            System.out.print(this.m_alphabet[i] + " ");
        }
        System.out.println();

        //ligne de séparation
        this.separer();

        //affichage des lignes
        for (int i = 0; i < this.m_hauteur; i++) {
            if (i+1 < 10) {
                System.out.print(i+1 + "  |");
            }
            else {
                System.out.print(i+1 + " |");
            }
            for (int j = 0; j < this.m_largeur; j++) {
                System.out.print("\t" + this.m_carte[i][j]);
            }
            System.out.print("\t|");
            System.out.println();
        }
        //ligne de séparation
        this.separer();

        //légende de la carte
        System.out.println("\t* Equipement\t|\t[ ] Obstacle");
    }

    public void positionnerEmplacementVide(int x, int y){
        this.m_carte[x][y] = ".";
    }

    public void positionnerElementCarte(Placable p){
        this.m_carte[p.getPositionX()][p.getPositionY()] = p.getNomAffiche();
    }

    public String[][] getCarte(){
        return this.m_carte;
    }

    public int getHauteur() {
        return this.m_hauteur;
    }

    public int getLargeur() {
        return this.m_largeur;
    }

    public void separer() {
        System.out.println("   *----------------------------------------------------------------*");
    }
}
