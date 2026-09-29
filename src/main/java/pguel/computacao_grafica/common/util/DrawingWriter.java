package pguel.computacao_grafica.common.util;

import pguel.computacao_grafica.common.model.Drawing;
import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.Point;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;

public class DrawingWriter {

    public static void writeDrawing(Drawing drawing, String filePath) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(drawing.getFigures().size());
            for (Figure figure : drawing.getFigures()) {
                writer.println(figure.getPoints().size());
                for (Point point : figure.getPoints()) {
                    writer.println(String.format(Locale.US, "%.4f %.4f", point.getX(), point.getY()));
                }
            }
        }
    }
}
