package pguel.computacao_grafica.aula07;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.*;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;

import pguel.computacao_grafica.common.builder.TransformBuilder;
import pguel.computacao_grafica.common.controller.KeyboardController;
import pguel.computacao_grafica.common.controller.MouseController;
import pguel.computacao_grafica.common.model.Drawing;
import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.Matrix3;
import pguel.computacao_grafica.common.model.OrthoBounds;
import pguel.computacao_grafica.common.model.Point;
import pguel.computacao_grafica.common.model.WindowSize;
import pguel.computacao_grafica.common.util.DrawingReader;
import pguel.computacao_grafica.common.util.DrawingWriter;
import pguel.computacao_grafica.common.util.FigureUtils;
import pguel.computacao_grafica.common.util.Transform2D;
import pguel.computacao_grafica.common.view.CurveView;

public class Atv1 {
    private static final String SAVE_FILE = "curva.dat";
    private static final float TRANSLATION_STEP = 10f;
    private static final float ROTATION_STEP = (float) Math.toRadians(5);
    private static final float SCALE_STEP = 1.1f;

    public static void main(String[] args) {
        Figure controls = new Figure();
        OrthoBounds ortho = new OrthoBounds(0, 500, 0, 500);
        WindowSize wsize = new WindowSize(500, 500);
        CurveView cv = new CurveView(controls, ortho);
        Map<Integer, Runnable> actions = new HashMap<>();
        Map<Character, Runnable> charActions = new HashMap<>();

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

        actions.put(KeyEvent.VK_RIGHT, () -> translate(controls, TRANSLATION_STEP, 0));
        actions.put(KeyEvent.VK_LEFT, () -> translate(controls, -TRANSLATION_STEP, 0));
        actions.put(KeyEvent.VK_UP, () -> translate(controls, 0, TRANSLATION_STEP));
        actions.put(KeyEvent.VK_DOWN, () -> translate(controls, 0, -TRANSLATION_STEP));

        charActions.put('r', () -> aroundCenter(controls, TransformBuilder.rotation(-ROTATION_STEP)));
        charActions.put('R', () -> aroundCenter(controls, TransformBuilder.rotation(ROTATION_STEP)));
        charActions.put('e', () -> aroundCenter(controls, TransformBuilder.scale(SCALE_STEP, SCALE_STEP)));
        charActions.put('E', () -> aroundCenter(controls, TransformBuilder.scale(1 / SCALE_STEP, 1 / SCALE_STEP)));
        charActions.put('s', () -> save(controls));
        charActions.put('o', () -> open(controls));

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

        KeyboardController keyboardController = new KeyboardController(actions, charActions, panel);

        panel.addGLEventListener(cv);
        panel.addMouseListener(mouseController);
        panel.addMouseMotionListener(mouseController);
        panel.addKeyListener(keyboardController);
        panel.setFocusable(true);
        panel.setPreferredSize(new Dimension((int) wsize.getWidth(), (int) wsize.getHeight()));

        JFrame frame = new JFrame("Atividade 06/1");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }

    private static void translate(Figure controls, float dx, float dy){
        Transform2D.applyInPlace(controls, TransformBuilder.translation(dx, dy));
    }

    private static void aroundCenter(Figure controls, Matrix3 transform){
        if (controls.getPoints().isEmpty()){
            return;
        }
        Matrix3 matrix = TransformBuilder.around(transform, FigureUtils.centroid(controls));
        Transform2D.applyInPlace(controls, matrix);
    }

    private static void save(Figure controls){
        Drawing drawing = new Drawing();
        drawing.addFigure(controls);
        try {
            DrawingWriter.writeDrawing(drawing, SAVE_FILE);
        } catch (FileNotFoundException e) {
            System.err.println("Não foi possível salvar em " + SAVE_FILE);
        }
    }

    private static void open(Figure controls){
        try {
            Drawing drawing = DrawingReader.readDrawing(SAVE_FILE);
            if (!drawing.getFigures().isEmpty()){
                FigureUtils.replacePoints(controls, drawing.getFigures().get(0));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo " + SAVE_FILE + " não encontrado");
        }
    }
}
