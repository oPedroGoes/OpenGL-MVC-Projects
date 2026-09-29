package pguel.computacao_grafica.common.builder;

import java.util.List;

import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.Point;

public class BSplineBuilder {
    private static final int SEGMENT_STEPS = 100;

    public static Figure build(Figure controls){
        List<Point> points = controls.getPoints();
        Figure curve = new Figure();

        for (int i = 0; i + 2 < points.size(); i++){
            Point p0 = points.get(i);
            Point p1 = points.get(i + 1);
            Point p2 = points.get(i + 2);

            for (int j = 0; j <= SEGMENT_STEPS; j++){
                float t = (float) j / SEGMENT_STEPS;
                float n0_2 = (t * t - 2 * t + 1) / 2;
                float n1_2 = (-2 * t * t + 2 * t + 1) / 2;
                float n2_2 = (t * t) / 2;

                curve.addPoint(new Point(
                        n0_2 * p0.getX() + n1_2 * p1.getX() + n2_2 * p2.getX(),
                        n0_2 * p0.getY() + n1_2 * p1.getY() + n2_2 * p2.getY()
                ));
            }
        }

        return curve;
    }
}
