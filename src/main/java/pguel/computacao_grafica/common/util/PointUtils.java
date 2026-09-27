package pguel.computacao_grafica.common.util;

import pguel.computacao_grafica.common.model.Point;


public class PointUtils {
    public static float distance(Point a, Point b){
        return (float) Math.sqrt( Math.pow(b.getX()-a.getX(), 2) + Math.pow(b.getY()-a.getY(), 2));
    }
}
