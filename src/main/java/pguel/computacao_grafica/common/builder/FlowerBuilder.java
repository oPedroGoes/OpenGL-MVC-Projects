package pguel.computacao_grafica.common.builder;

import java.util.ArrayList;
import java.util.List;

import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.Flower;
import pguel.computacao_grafica.common.model.Point;
import pguel.computacao_grafica.common.model.Teardrop;
import pguel.computacao_grafica.common.util.Transform2D;

public class FlowerBuilder {

    public static List<Figure> build(Flower flower){
        Teardrop petal = new Teardrop(new Point(0, 0), 0, flower.getPetalLength(), flower.getPetalHalfAngle());
        Figure base = Transform2D.translate(TeardropBuilder.build(petal), 0, flower.getTipOffset());

        Point center = flower.getCenter();
        float step = (float) (2 * Math.PI / flower.getPetals());

        List<Figure> petals = new ArrayList<>();
        for (int i = 0; i < flower.getPetals(); i++){
            Figure rotated = Transform2D.rotate(base, i * step);
            petals.add(Transform2D.translate(rotated, center.getX(), center.getY()));
        }

        return petals;
    }
}
