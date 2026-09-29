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
    Consumer<Point> onPress;
    Consumer<Point> onDrag;

    public MouseController(JPanel panel, OrthoBounds ortho, WindowSize window, Consumer<Point> onLeftClick, Consumer<Point> onRightClick){
        this(panel, ortho, window, onLeftClick, onRightClick, point -> {}, point -> {});
    }

    public MouseController(JPanel panel, OrthoBounds ortho, WindowSize window, Consumer<Point> onLeftClick, Consumer<Point> onRightClick, Consumer<Point> onPress, Consumer<Point> onDrag){
        super();
        this.panel = panel;
        this.ortho = ortho;
        this.window = window;
        this.onLeftClick = onLeftClick;
        this.onRightClick = onRightClick;
        this.onPress = onPress;
        this.onDrag = onDrag;
    }

    @Override
    public void mouseClicked(MouseEvent event){
        Point point = toOrtho(event);

        switch (event.getButton()) {
            case MouseEvent.BUTTON1 -> onLeftClick.accept(point);
            case MouseEvent.BUTTON3 -> onRightClick.accept(point);
            default -> {}
        }

        panel.requestFocusInWindow();
        panel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent event){
        if (SwingUtilities.isLeftMouseButton(event)){
            onPress.accept(toOrtho(event));
            panel.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent event){
        if (SwingUtilities.isLeftMouseButton(event)){
            onDrag.accept(toOrtho(event));
            panel.repaint();
        }
    }

    private Point toOrtho(MouseEvent event){
        return CoordUtils.mapWindowToOrtho(new Point(event.getX(), event.getY()), ortho, window);
    }
}
