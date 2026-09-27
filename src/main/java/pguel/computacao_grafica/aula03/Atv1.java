package pguel.computacao_grafica.aula03;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import pguel.computacao_grafica.common.model.Drawing;
import pguel.computacao_grafica.common.model.OrthoBounds;
import pguel.computacao_grafica.common.model.WindowSize;
import pguel.computacao_grafica.common.util.DrawingReader;
import pguel.computacao_grafica.common.view.DrawingView;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Atv1 {
    public static void main(String[] args) {
        Drawing dino;
        OrthoBounds ortho = new OrthoBounds(0, 500, 0, 500);
        WindowSize wsize = new WindowSize(500, 500);

        try {
            dino = DrawingReader.readDrawing("./src/main/resources/dino.dat");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
            return;
        }

        DrawingView dwv = new DrawingView(dino, ortho);

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLJPanel panel = new GLJPanel(capabilities);



        panel.addGLEventListener(dwv);
        panel.setSize((int) wsize.getWidth(), (int) wsize.getHeight());

        JFrame frame = new JFrame("Exercicio 1/03");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize((int) wsize.getWidth(), (int) wsize.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
