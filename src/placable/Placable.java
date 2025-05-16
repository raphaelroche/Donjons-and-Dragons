package placable;

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
