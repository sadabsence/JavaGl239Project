package problem;

import java.util.Random;

// класс точки
public class PointClass {
    // номер множества
    private int setVal;
    // положение точки
    private Vector2 pos;
    // константы множества
    public static final int SET_1=0;
    public static final int SET_2=1;
    // пересекается ли точка с точкой из другого множества
    private boolean isCrossed = false;

    // получаем множество
    public int getSetVal() {
        return setVal;
    }
    public boolean isCrossed() {
        return isCrossed;
    }
    void setCrossed(boolean crossed) {
        isCrossed = crossed;
    }
    public Vector2 getPos() {
        return pos;
    }

    public PointClass(int setVal, Vector2 pos) {
        this.setVal = setVal;
        this.pos = pos;
    }

    public PointClass() {
        setVal = SET_1;
        pos = new Vector2(0,0);
    }

    PointClass(double x, double y, int setVal){
        pos = new Vector2(x,y);
        this.setVal = setVal;
    }
    // получаем рандомную точку
    static PointClass getRandomPoint(){
        Random r = new Random();
        double nx = (double)r.nextInt(50)/25-1;
        double ny = (double)r.nextInt(50)/25-1;
        int nSetVal = r.nextInt(2);
        return new PointClass(nx,ny,nSetVal);
    }

    @Override
    public String toString() {
        return  "Точка с координатами: "+pos+" из множества: "+setVal;
    }
}
