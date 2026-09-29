package pguel.computacao_grafica.common.builder;

import pguel.computacao_grafica.common.model.Matrix3;
import pguel.computacao_grafica.common.model.Point;

public class TransformBuilder {

    public static Matrix3 translation(float dx, float dy){
        return new Matrix3(new float[][]{
                {1, 0, dx},
                {0, 1, dy},
                {0, 0, 1}
        });
    }

    public static Matrix3 scale(float sx, float sy){
        return new Matrix3(new float[][]{
                {sx, 0, 0},
                {0, sy, 0},
                {0, 0, 1}
        });
    }

    public static Matrix3 rotation(float angle){
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        return new Matrix3(new float[][]{
                {cos, -sin, 0},
                {sin, cos, 0},
                {0, 0, 1}
        });
    }

    public static Matrix3 around(Matrix3 transform, Point pivot){
        Matrix3 toOrigin = translation(-pivot.getX(), -pivot.getY());
        Matrix3 back = translation(pivot.getX(), pivot.getY());
        return back.multiply(transform).multiply(toOrigin);
    }
}
