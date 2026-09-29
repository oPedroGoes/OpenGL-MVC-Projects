package pguel.computacao_grafica.common.util;

import java.util.List;

import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.Point;

public class FigureUtils {

    public static Point centroid(Figure figure){
        List<Point> points = figure.getPoints();
        if (points.isEmpty()){
            throw new IllegalStateException("A figura deve possuir pontos para que se encontre o centro.");
        }

        float sumX = 0;
        float sumY = 0;
        for (Point point : points){
            sumX += point.getX();
            sumY += point.getY();
        }
        return new Point(sumX / points.size(), sumY / points.size());
    }

    public static void replacePoints(Figure target, Figure source){
        while (!target.getPoints().isEmpty()){
            target.popPoint(0);
        }
        for (Point point : source.getPoints()){
            target.addPoint(point);
        }
        target.setCurrentPoint(null);
    }
}
