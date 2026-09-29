package pguel.computacao_grafica.aula06;

import java.awt.Dimension;
import java.util.function.Consumer;

import javax.swing.*;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;

import pguel.computacao_grafica.common.controller.MouseController;
import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.OrthoBounds;
import pguel.computacao_grafica.common.model.Point;
import pguel.computacao_grafica.common.model.WindowSize;
import pguel.computacao_grafica.common.view.CurveView;

public class Atv1 {
    public static void main(String[] args) {
        Figure controls = new Figure();
        OrthoBounds ortho = new OrthoBounds(0, 500, 0, 500);
        WindowSize wsize = new WindowSize(500, 500);
        CurveView cv = new CurveView(controls, ortho);

        Consumer<Point> onLeftClick = (click) -> controls.addPoint(click);
        Consumer<Point> onRightClick = (click) -> {
            if (!controls.getPoints().isEmpty()){
                controls.popPoint(controls.findNearest(click));
                controls.setCurrentPoint(null);
            }
        };

        Consumer<Point> onPress = (press) -> {
            if (!controls.getPoints().isEmpty()){
                controls.setCurrentPoint(controls.findNearest(press));
            }
        };
        Consumer<Point> onDrag = (drag) -> {
            Point current = controls.getCurrentPoint();
            if (current != null){
                current.setX(drag.getX());
                current.setY(drag.getY());
            }
        };

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLJPanel panel = new GLJPanel(capabilities);

        MouseController mouseController = new MouseController(
                panel,
                ortho,
                wsize,
                onLeftClick,
                onRightClick,
                onPress,
                onDrag
        );

        panel.addGLEventListener(cv);
        panel.addMouseListener(mouseController);
        panel.addMouseMotionListener(mouseController);
        panel.setPreferredSize(new Dimension((int) wsize.getWidth(), (int) wsize.getHeight()));

        JFrame frame = new JFrame("Atividade 06");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
