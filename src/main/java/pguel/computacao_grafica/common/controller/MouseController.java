package pguel.computacao_grafica.common.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

import javax.swing.*;

import pguel.computacao_grafica.common.model.OrthoBounds;
import pguel.computacao_grafica.common.model.Point;
import pguel.computacao_grafica.common.model.WindowSize;
import pguel.computacao_grafica.common.util.CoordUtils;

public class MouseController extends MouseAdapter {
    JPanel panel;
    OrthoBounds ortho;
    WindowSize window;
    Consumer<Point> onLeftClick;
    Consumer<Point> onRightClick;

    public MouseController(JPanel panel, OrthoBounds ortho, WindowSize window, Consumer<Point> onLeftClick, Consumer<Point> onRightClick){
        super();
        this.panel = panel;
        this.ortho = ortho;
        this.window = window;
        this.onLeftClick = onLeftClick;
        this.onRightClick = onRightClick;
    }

    @Override
    public void mouseClicked(MouseEvent event){
        Point click = new Point(event.getX(), event.getY());
        Point point = CoordUtils.mapWindowToOrtho(click, ortho, window);

        switch (event.getButton()) {
            case MouseEvent.BUTTON1 -> onLeftClick.accept(point);
            case MouseEvent.BUTTON3 -> onRightClick.accept(point);
            default -> {}
        }

        panel.requestFocusInWindow();
        panel.repaint();
    }
}
