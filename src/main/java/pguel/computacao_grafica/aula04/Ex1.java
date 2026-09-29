package pguel.computacao_grafica.aula04;

import java.util.function.Consumer;

import javax.swing.*;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;

import pguel.computacao_grafica.common.builder.TeardropBuilder;
import pguel.computacao_grafica.common.controller.MouseController;
import pguel.computacao_grafica.common.model.Drawing;
import pguel.computacao_grafica.common.model.OrthoBounds;
import pguel.computacao_grafica.common.model.Point;
import pguel.computacao_grafica.common.model.Teardrop;
import pguel.computacao_grafica.common.model.WindowSize;
import pguel.computacao_grafica.common.util.DrawingUtils;
import pguel.computacao_grafica.common.view.DrawingView;

public class Ex1 {
    private static final float AXIS_ANGLE = (float) Math.toRadians(90);
    private static final float TANGENT_LENGTH = 90f;
    private static final float HALF_ANGLE = (float) Math.toRadians(20);

    public static void main(String[] args) {
        Drawing drawing = new Drawing();
        OrthoBounds ortho = new OrthoBounds(0, 500, 0, 500);
        WindowSize wsize = new WindowSize(500, 500);
        DrawingView dwv = new DrawingView(drawing, ortho);

        Consumer<Point> onLeftClick = (click) -> {
            Teardrop teardrop = new Teardrop(click, AXIS_ANGLE, TANGENT_LENGTH, HALF_ANGLE);
            drawing.addFigure(TeardropBuilder.build(teardrop));
        };
        Consumer<Point> onRightClick = (click) -> DrawingUtils.clear(drawing);

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

        panel.addGLEventListener(dwv);
        panel.addMouseListener(mouseController);
        panel.setSize((int) wsize.getWidth(), (int) wsize.getHeight());

        JFrame frame = new JFrame("Exercicio 1/04");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize((int) wsize.getWidth(), (int) wsize.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
