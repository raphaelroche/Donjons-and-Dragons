package sorts;

public class BoogieWoogie extends Sort {




    @Override
    public Object lancerSort(ContextSort c){
        int x1 = c.getXcible1();
        int y1 = c.getYcible1();
        int x2 = c.getXcible2();
        int y2 = c.getYcible2();

        c.setCible1(x2,y2);
        c.setCible2(x1,y1);

        return true;
    }
}
