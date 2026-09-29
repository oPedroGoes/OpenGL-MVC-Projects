package pguel.computacao_grafica.common.util;

import java.util.List;

import pguel.computacao_grafica.common.model.Drawing;
import pguel.computacao_grafica.common.model.Figure;

public class DrawingUtils {

    public static void addAll(Drawing drawing, List<Figure> figures){
        for (Figure figure : figures){
            drawing.addFigure(figure);
        }
    }

    public static void clear(Drawing drawing){
        while (!drawing.getFigures().isEmpty()){
            drawing.popFigure(0);
        }
    }
}
