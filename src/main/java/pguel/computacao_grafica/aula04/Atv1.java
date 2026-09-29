package pguel.computacao_grafica.aula04;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.*;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;

import pguel.computacao_grafica.common.builder.FlowerBuilder;
import pguel.computacao_grafica.common.controller.MouseController;
import pguel.computacao_grafica.common.controller.KeyboardController;
import pguel.computacao_grafica.common.model.Drawing;
import pguel.computacao_grafica.common.model.Flower;
import pguel.computacao_grafica.common.model.OrthoBounds;
import pguel.computacao_grafica.common.model.Point;
import pguel.computacao_grafica.common.model.WindowSize;
import pguel.computacao_grafica.common.util.DrawingUtils;
import pguel.computacao_grafica.common.view.DrawingView;

public class Atv1 {
    private static final int PETALS = 4;
    private static final float PETAL_LENGTH = 75f;
    private static final float PETAL_HALF_ANGLE = (float) Math.toRadians(30);
    private static final float TIP_OFFSET = 35f;

    public static void main(String[] args) {
        Drawing drawing = new Drawing();
        OrthoBounds ortho = new OrthoBounds(0, 500, 0, 500);
        WindowSize wsize = new WindowSize(500, 500);
        DrawingView dwv = new DrawingView(drawing, ortho);
        Map<Integer, Runnable> actions = new HashMap<>();

        Consumer<Point> onLeftClick = (click) -> {
            Flower flower = new Flower(click, PETALS, PETAL_LENGTH, PETAL_HALF_ANGLE, 0);
            DrawingUtils.addAll(drawing, FlowerBuilder.build(flower));
        };

        Consumer<Point> onRightClick = (click) -> {
            Flower flower = new Flower(click, PETALS, PETAL_LENGTH, PETAL_HALF_ANGLE, TIP_OFFSET);
            DrawingUtils.addAll(drawing, FlowerBuilder.build(flower));
        };

        actions.put(KeyEvent.VK_C, () -> DrawingUtils.clear(drawing));

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLJPanel panel = new GLJPanel(capabilities);

        MouseController mouseController = new MouseController(
                panel,
                ortho,
                wsize,
                onLeftClick,
                onRightClick
        );

        KeyboardController keyboardController = new KeyboardController(actions, panel);

        panel.addGLEventListener(dwv);
        panel.addMouseListener(mouseController);
        panel.addKeyListener(keyboardController);
        panel.setFocusable(true);
        panel.setSize((int) wsize.getWidth(), (int) wsize.getHeight());

        JFrame frame = new JFrame("Atividade 1/04");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize((int) wsize.getWidth(), (int) wsize.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
