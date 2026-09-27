package pguel.computacao_grafica.common.model;

public class WindowSize{
    private float height, width;

    public WindowSize(float width,float height){
        this.height = height;
        this.width = width;
    }

    public float  getHeight(){
        return height;
    }
    public float getWidth() {
        return width;
    }
}
