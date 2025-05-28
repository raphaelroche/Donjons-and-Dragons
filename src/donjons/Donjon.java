package donjons;

import placable.CaseVide;
import placable.Placable;
import placable.entites.Entite;
import placable.entites.monstres.Monstre;
import placable.equipements.Equipement;
import placable.obstacle.Obstacle;

import java.util.ArrayList;

public class Donjon {
    private char[] m_alphabet;
    private final ArrayList<Placable>[][] m_carte;
    private int m_hauteur;
    private int m_largeur;


    public Donjon(){
        this(15, 15);
    }


    public Donjon(int largeur,int hauteur){
        this.m_hauteur = hauteur;
        this.m_largeur = largeur;
        this.m_carte = new ArrayList[m_largeur][m_hauteur];
        this.m_alphabet = new char[] {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        // Initialiser la carte avec des "." initaliser la 2 eme case a null
        for (int x = 0; x < m_largeur; x++) {
            for (int y = 0; y < m_hauteur; y++) {
                m_carte[x][y] = new ArrayList<>();
                positionnerEmplacementVide(x,y);
                m_carte[x][y].add(null);
            }
        }
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
        l.set(1,p);

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

        if(p.estEquipement()){
            if(this.m_carte[x][y].getFirst().estCaseVide()){
                this.m_carte[x][y].set(0,p);
                return true;
            }
            else if(this.m_carte[x][y].getFirst().estEntite() && ((this.m_carte[x][y].get(1) == null))){
                this.m_carte[x][y].set(1,p);
                return true;
            }
            else{
                //si la case sur laquelle on veut placer l'equipement est un obtsacle alors on redemendera de placer
                return false;
            }
        }
        else if(p.estEntite()){
            if(this.m_carte[x][y].getFirst().estEquipement()){
                this.decalerADroite(this.m_carte[x][y]);
                this.m_carte[x][y].set(0,p);
                return true;
            }
            else if(this.m_carte[x][y].getFirst() == null || this.m_carte[x][y].getFirst().estCaseVide()){
                this.m_carte[x][y].set(0,p);
                return true;
            }
            return false;
        }
        else if(p.estObstacle()){
            if(this.m_carte[x][y].getFirst() == null || this.m_carte[x][y].getFirst().estCaseVide()){
                this.m_carte[x][y].set(0,p);
                return true;
            }
            return false;

        }
        else{
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

    public char getLettreMax() { return this.m_alphabet[m_largeur-1]; }
}
