package pguel.computacao_grafica.common.builder;

import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.Point;
import pguel.computacao_grafica.common.model.Teardrop;

public class TeardropBuilder {

    private static final int ARC_SEGMENTS = 100;

    public static Figure build(Teardrop teardrop){
        Point tip = teardrop.getTip();
        float axis = teardrop.getAxisAngle();       // direção do eixo
        float length = teardrop.getTangentLength();
        float half = teardrop.getHalfAngle();       // alfa

        // raio do bulbo: cateto oposto a alga no triângulo ponta-centro-tangência
        float radius = (float) (length * Math.tan(half));

        // distância ponta-centro
        float distance = (float) (length / Math.cos(half));

        // centro do bulbo
        float centerX = (float) (tip.getX() + distance * Math.cos(axis));
        float centerY = (float) (tip.getY() + distance * Math.sin(axis));

        // ângulo (visto do centro) do 1º ponto de tangência
        float start = (float) (axis - (Math.PI / 2 + half));

        // quanto o arco percorre até o outro ponto de tangência (arco maior)
        float sweep = (float) (Math.PI + 2 * half);

        Figure figure = new Figure();
        figure.addPoint(new Point(tip.getX(), tip.getY()));
        for (int i = 0; i <= ARC_SEGMENTS; i++){
            float angle = start + sweep * i / ARC_SEGMENTS;
            figure.addPoint(new Point(
                    (float) (centerX + radius * Math.cos(angle)),
                    (float) (centerY + radius * Math.sin(angle))
            ));
        }
        figure.addPoint(new Point(tip.getX(), tip.getY()));

        return figure;
    }
}
