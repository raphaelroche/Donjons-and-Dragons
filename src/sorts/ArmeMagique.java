package sorts;

public class ArmeMagique extends Sort {
    @Override
    public boolean lancerSort(ContextSort c){
        c.setArme(true);
        return true;
    }
}
