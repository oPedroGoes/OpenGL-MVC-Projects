package pguel.computacao_grafica.common.model;

public class Teardrop {
    private Point tip;
    private float axisAngle;
    private float tangentLength;
    private float halfAngle;

    public Teardrop(Point tip, float axisAngle, float tangentLength, float halfAngle){
        this.tip = tip;
        this.axisAngle = axisAngle;
        this.tangentLength = tangentLength;
        this.halfAngle = halfAngle;
    }

    public Point getTip(){
        return tip;
    }
    public float getAxisAngle(){
        return axisAngle;
    }
    public float getTangentLength(){
        return tangentLength;
    }
    public float getHalfAngle(){
        return halfAngle;
    }

    public void setTip(Point tip){
        this.tip = tip;
    }
    public void setAxisAngle(float axisAngle){
        this.axisAngle = axisAngle;
    }
}
