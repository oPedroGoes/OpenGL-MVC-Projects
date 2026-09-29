package pguel.computacao_grafica.common.util;

import pguel.computacao_grafica.common.model.Drawing;
import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class DrawingReader {

    public static Drawing readDrawing(String filePath) throws FileNotFoundException {
        System.out.println("Lendo drawing...");
        Scanner sc = new Scanner(new File(filePath));
        sc.useLocale(Locale.US);
        Drawing drawing = new Drawing();

        int polygonCount = sc.nextInt();
        for (int i = 0; i < polygonCount; i++) {
            int vertexCount = sc.nextInt();
            Figure figure = new Figure();
            for (int j = 0; j < vertexCount; j++) {
                float x = sc.nextFloat();
                float y = sc.nextFloat();
                figure.addPoint(new Point(x, y));
            }
            drawing.addFigure(figure);
        }

        sc.close();
        return drawing;
    }
}