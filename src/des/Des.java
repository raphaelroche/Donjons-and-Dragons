package des;

import java.util.Random;

public class Des {

    public int lancerDes(int nbDes, int nbFaces) {
        int m_somme = 0;
        Random des = new Random();
        if (nbDes > 0 && nbFaces > 0) {
            for (int i = 0; i < nbDes; i++) {
                m_somme += des.nextInt(0,nbFaces+1);
            }
        }
        return m_somme;
    }
}