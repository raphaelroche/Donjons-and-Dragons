package maitredujeu;

public class MaitreDuJeu {
    public MaitreDuJeu(){

    }

    public void creerDonjon(int longueur, int largeur){
        String[][] carte = creerCarte(longueur, largeur);
        positionnerObstacle();
        creerMonstres();
        positionnerJoueurs();
        positionnerEquipements();
        presenterContexte();
    }

    public String[][] creerCarte(int longueur, int largeur) {
        String[][] carte = new String[longueur][largeur];
        for (int i = 0; i < longueur; i++) {
            for(int j = 0; j<largeur; j++){
                carte[i][j] = ".";
            }
        }
        return carte;
    }

    public void positionnerObstacle(){

    }

    public void creerMonstres() {


    }

    public void positionnerJoueurs(){

    }

    public void positionnerEquipements(){

    }

    public void presenterContexte(){

    }


}
