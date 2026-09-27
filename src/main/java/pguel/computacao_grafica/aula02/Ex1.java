package pguel.computacao_grafica.aula02;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import pguel.computacao_grafica.common.controller.MouseController;
import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.OrthoBounds;
import pguel.computacao_grafica.common.model.Point;
import pguel.computacao_grafica.common.model.WindowSize;
import pguel.computacao_grafica.common.view.PointView;

import javax.swing.*;
import java.util.function.BiConsumer;

public class Ex1 {
    public static BiConsumer<Figure, Point> onLeftClick = (figure, point) -> {};
    public static BiConsumer<Figure, Point> onRightClick = (figure, point) -> {figure.popPoint(point);};

    public static void main(String[] args) {
        Figure figure = new Figure();
        OrthoBounds ortho = new OrthoBounds(0, 500, 0, 500);
        WindowSize wsize = new WindowSize(500, 500);

        PointView ptv = new PointView(figure, ortho);

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLJPanel panel = new GLJPanel(capabilities);

        MouseController controller = new MouseController(
                panel,
                figure,
                ortho,
                wsize,
                onLeftClick,
                onRightClick
        );

        panel.addGLEventListener(ptv);
        panel.addMouseListener(controller);
        panel.setSize((int) wsize.getWidth(), (int) wsize.getHeight());

        JFrame frame = new JFrame("Exercicio 1/02");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize((int) wsize.getWidth(), (int) wsize.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}

