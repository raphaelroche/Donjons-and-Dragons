package sorts;

public class ArmeMagique extends Sort {
    @Override
    public Object lancerSort(ContextSort c){
        c.setArme(true);
        return true;
    }
}
