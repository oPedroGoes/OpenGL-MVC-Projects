package pguel.computacao_grafica.common.model;

public class Matrix3 {
    private final float[][] values;

    public Matrix3(float[][] values){
        this.values = values;
    }

    public static Matrix3 identity(){
        return new Matrix3(new float[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        });
    }

    public float get(int row, int column){
        return values[row][column];
    }

    public Matrix3 multiply(Matrix3 other){
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                for (int k = 0; k < 3; k++){
                    result[i][j] += values[i][k] * other.values[k][j];
                }
            }
        }
        return new Matrix3(result);
    }

    public Point apply(Point point){
        return new Point(
                values[0][0] * point.getX() + values[0][1] * point.getY() + values[0][2],
                values[1][0] * point.getX() + values[1][1] * point.getY() + values[1][2]
        );
    }
}
