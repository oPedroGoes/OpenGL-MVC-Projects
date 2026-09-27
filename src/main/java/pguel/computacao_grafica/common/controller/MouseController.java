package pguel.computacao_grafica.common.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;

import com.jogamp.opengl.awt.GLJPanel;
import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.OrthoBounds;
import pguel.computacao_grafica.common.model.Point;
import pguel.computacao_grafica.common.model.WindowSize;
import pguel.computacao_grafica.common.util.CoordUtils;

import javax.swing.*;

public class MouseController extends MouseAdapter {
    JPanel panel;
    Figure figure;
    OrthoBounds ortho;
    WindowSize window;
    BiConsumer<Figure, Point> onAddPoint;
    BiConsumer<Figure, Point> onFindNearest;

    public MouseController(JPanel panel, Figure figure, OrthoBounds ortho, WindowSize window, BiConsumer<Figure, Point> onAddPoint, BiConsumer<Figure, Point> onFindNearest) {
        super();
        this.panel = panel;
        this.figure = figure;
        this.ortho = ortho;
        this.window = window;
        this.onAddPoint = onAddPoint;
        this.onFindNearest = onFindNearest;
    }


    @Override
    public void mouseClicked(MouseEvent event) {
        int button = event.getButton();

        /*Debug*/ System.out.println(button);
        Point click = new Point(event.getX(), event.getY());
        Point point = CoordUtils.mapWindowToOrtho(click, ortho, window);

        switch (button) {
            case MouseEvent.BUTTON1 -> {
                figure.addPoint(point);
                onAddPoint.accept(figure, point);
            }
            case MouseEvent.BUTTON3 -> {
                Point nearest = figure.findNearest(point);
                onFindNearest.accept(figure, nearest);
            }
            default -> {}
        }
        panel.repaint();
    }
}

