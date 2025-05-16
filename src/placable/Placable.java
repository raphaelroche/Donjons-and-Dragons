package placable;
/**
 * Interface représentant un élément pouvant être placé dans le donjon.
 * Implémentée par toutes les entités visibles sur la carte (monstres, héros, équipements, murs, etc.).
 *
 * Permet d'unifier le traitement des objets sur la carte :
 * - accès à leur position (x, y),
 * - affichage d'un nom court ou d'un symbole,
 * - identification de leur type sans instanceof.
 *
 * Les méthodes par défaut (estObstacle, estEntite, etc.) permettent
 * de savoir à quoi correspond l'objet.
 */
public interface Placable {

    void setLocation(int x, int y);
    int getPositionX();
    int getPositionY();
    String getNomAffiche();


    //evite les instanceof en overridant dans chaque classe 
    default boolean estObstacle(){
        return false;
    }
    default boolean estEntite(){
        return false;
    }
    default boolean estEquipement(){
        return false;
    }
    default boolean estCaseVide(){
        return false;
    }
}
