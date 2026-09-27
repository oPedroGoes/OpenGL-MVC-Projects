package pguel.computacao_grafica.common.model;

public class Point {
    private float x = 0, y = 0;

    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX(){ return x; }
    public float getY(){ return y; }

    public void setX(float x){ this.x = x; }
    public void setY(float y){ this.y = y; }
}
