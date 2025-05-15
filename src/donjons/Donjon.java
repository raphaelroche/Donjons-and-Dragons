package donjons;

import placable.CaseVide;
import placable.Placable;
import placable.entites.Entite;
import placable.equipements.Equipement;
import placable.obstacle.Obstacle;

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

        Placable p = l.getFirst();
        l.set(1, p);

        l.set(0, null); // la première case devient vide

    }

    public void decalerAGauche(ArrayList<Placable> l){

        Placable p  = l.get(1);
        l.set(0, p);

        l.set(1, null);
    }
    public boolean positionnerElementCarte(Placable p) {
        int x = p.getPositionX();
        int y = p.getPositionY();

        if(p instanceof Equipement){
            if(this.m_carte[x][y].getFirst() instanceof CaseVide){
                this.m_carte[x][y].set(0,p);
                return true;
            }
            else if(this.m_carte[x][y].getFirst() instanceof Entite){
                this.m_carte[x][y].add(p);
                return true;
            }
            else{
                //si la case sur laquelle on veut placer l'equipement est un obtsacle alors on redemendera de placer
                return false;
            }
        }
        else if(p instanceof Entite){
            if(this.m_carte[x][y].getFirst() instanceof Equipement){
                this.decalerADroite(this.m_carte[x][y]);
                this.m_carte[x][y].set(0,p);
                return true;
            }
            else if(this.m_carte[x][y].getFirst() instanceof CaseVide){
                this.m_carte[x][y].set(0,p);
                return true;
            }
            return false;
        }
        else{
            if(this.m_carte[x][y].getFirst() == null || this.m_carte[x][y].getFirst() instanceof CaseVide){
                this.m_carte[x][y].set(0,p);
                return true;
            }
            return false;

        }
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
