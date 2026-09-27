package pguel.computacao_grafica.aula02;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import pguel.computacao_grafica.common.controller.KeyboardController;
import pguel.computacao_grafica.common.controller.MouseController;
import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.OrthoBounds;
import pguel.computacao_grafica.common.model.Point;
import pguel.computacao_grafica.common.model.WindowSize;
import pguel.computacao_grafica.common.view.PointView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Ex2 {
    public static BiConsumer<Figure, Point> onLeftClick = (figure, point) -> {figure.setCurrentPoint(point);};
    public static BiConsumer<Figure, Point> onRightClick = (figure, point) -> {figure.setCurrentPoint(point);};

    public static void main(String[] args) {
        Figure figure = new Figure();
        OrthoBounds ortho = new OrthoBounds(0, 500, 0, 500);
        WindowSize wsize = new WindowSize(500, 500);
        PointView ptv = new PointView(figure, ortho);
        Map<Integer, Runnable> actions = new HashMap<>();

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLJPanel panel = new GLJPanel(capabilities);


        Runnable onRightKey = () -> {
            Point current = figure.getCurrentPoint();
            if (current != null){ current.setX(current.getX() + 5); }
        };

        Runnable onLeftKey = () -> {
            Point current = figure.getCurrentPoint();
            if (current != (null)) { current.setX(current.getX() - 5); }
        };

        Runnable onUpKey = () -> {
            Point current = figure.getCurrentPoint();
            if (current != (null)) { current.setY(current.getY() + 5); }
        };

        Runnable onDownKey = () -> {
            Point current = figure.getCurrentPoint();
            if (current != (null)) { current.setY(current.getY() - 5); }
        };

        actions.put(KeyEvent.VK_RIGHT, onRightKey);
        actions.put(KeyEvent.VK_LEFT, onLeftKey);
        actions.put(KeyEvent.VK_UP, onUpKey);
        actions.put(KeyEvent.VK_DOWN, onDownKey);

        MouseController mouseController = new MouseController(
                panel,
                figure,
                ortho,
                wsize,
                onLeftClick,
                onRightClick
        );

        KeyboardController keyboardController = new KeyboardController(actions, panel);

        panel.addGLEventListener(ptv);
        panel.addMouseListener(mouseController);
        panel.addKeyListener(keyboardController);
        panel.setSize((int) wsize.getWidth(), (int) wsize.getHeight());

        JFrame frame = new JFrame("Exercicio 2/02");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize((int) wsize.getWidth(), (int) wsize.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}

