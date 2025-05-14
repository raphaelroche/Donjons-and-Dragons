package donjons;

import placable.CaseVide;
import placable.Placable;

import java.util.ArrayList;

public class Donjon {
    private char[] m_alphabet;
    private final ArrayList<Placable>[][] m_carte;
    private int m_hauteur;
    private int m_largeur;
    private CaseVide m_casevide;


    public Donjon(){
        this(15, 15);
    }

    public Donjon(int hauteur, int largeur){
        this.m_hauteur = hauteur;
        this.m_largeur = largeur;
        this.m_carte = new ArrayList[m_hauteur][m_largeur];
        this.m_alphabet = new char[] {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        // Initialiser la carte avec des "."
        for (int x = 0; x < m_hauteur; x++) {
            for (int y = 0; y < m_largeur; y++) {
                m_carte[x][y] = new ArrayList<>();
                positionnerEmplacementVide(x,y);
            }
        }
    }

    public void afficherDonjon(){
        //affichage des lettres
        System.out.print("\t");
        for (int i = 0; i < this.m_largeur; i++) {
            System.out.print("  ");
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
                System.out.print(" " + this.m_carte[i][j].getFirst().getNomAffiche());
            }
            System.out.print("  |");
            System.out.println();
        }
        //ligne de séparation
        this.separer();

        //légende de la carte
        System.out.println("\t* Equipement\t|\t[ ] Obstacle\t|\tM Monstre");
    }

    public void positionnerEmplacementVide(int x, int y){
        if (this.m_carte[x][y].isEmpty()) {
            this.m_carte[x][y].add(new CaseVide(x, y));
        } else {
            this.m_carte[x][y].set(0, new CaseVide(x, y));
        }
    }

    public void decalerADroite(ArrayList<Placable> l){
        for (int i = l.size() - 2; i >= 0; i--) { // on part de la fin !
            Placable p = l.get(i);
            l.set(i + 1, p);
        }
        l.set(0, null); // la première case devient vide

    }

    public void decalerAGauche(ArrayList<Placable> l){
        for(int i = 1; i<l.size(); i++){
            Placable p  = l.get(i);
            l.set(i-1, p);
        }
        l.set(l.size() - 1, null);
    }
    public void positionnerElementCarte(Placable p) {
        int x = p.getPositionX();
        int y = p.getPositionY();

        this.m_carte[x][y].set(0,p);
    }

    public ArrayList<Placable>[][] getCarte(){
        return this.m_carte;
    }

    public int getHauteur() {
        return this.m_hauteur;
    }

    public int getLargeur() {
        return this.m_largeur;
    }

    public char getLettreMax() { return this.m_alphabet[m_largeur]; }

    public void separer() {
        System.out.println("\t*" + "-".repeat(this.getLargeur()*4) + "*");
    }
}
