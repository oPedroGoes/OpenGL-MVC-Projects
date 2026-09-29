package pguel.computacao_grafica.common.util;

import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.Point;

public class Transform2D {

    public static Point translate(Point point, float dx, float dy){
        return new Point(point.getX() + dx, point.getY() + dy);
    }

    public static Point rotate(Point point, float angle){
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        return new Point(
                point.getX() * cos - point.getY() * sin,
                point.getX() * sin + point.getY() * cos
        );
    }

    public static Figure translate(Figure figure, float dx, float dy){
        Figure result = new Figure();
        for (Point point : figure.getPoints()){
            result.addPoint(translate(point, dx, dy));
        }
        return result;
    }

    public static Figure rotate(Figure figure, float angle){
        Figure result = new Figure();
        for (Point point : figure.getPoints()){
            result.addPoint(rotate(point, angle));
        }
        return result;
    }
}
