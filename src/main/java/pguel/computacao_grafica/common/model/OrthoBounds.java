package pguel.computacao_grafica.common.model;

public class OrthoBounds{
    private float left, right, bottom, top;

    public OrthoBounds(float left, float  right, float bottom, float top){
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
    }

    public float getLeft(){
        return left;
    }
    public float getRight(){
        return right;
    }
    public float getBottom(){
        return bottom;
    }
    public float getTop(){
        return top;
    }

}
