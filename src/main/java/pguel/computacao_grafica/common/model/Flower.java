package pguel.computacao_grafica.common.model;

public class Flower {
    private Point center;
    private int petals;
    private float petalLength;
    private float petalHalfAngle;
    private float tipOffset;

    public Flower(Point center, int petals, float petalLength, float petalHalfAngle, float tipOffset){
        this.center = center;
        this.petals = petals;
        this.petalLength = petalLength;
        this.petalHalfAngle = petalHalfAngle;
        this.tipOffset = tipOffset;
    }

    public Point getCenter(){
        return center;
    }
    public int getPetals(){
        return petals;
    }
    public float getPetalLength(){
        return petalLength;
    }
    public float getPetalHalfAngle(){
        return petalHalfAngle;
    }
    public float getTipOffset(){
        return tipOffset;
    }

    public void setCenter(Point center){
        this.center = center;
    }
    public void setPetals(int petals){
        this.petals = petals;
    }
    public void setTipOffset(float tipOffset){
        this.tipOffset = tipOffset;
    }
}
