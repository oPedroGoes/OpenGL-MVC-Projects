package pguel.computacao_grafica;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.*;

public class HelloTriangulo implements GLEventListener {

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.1f, 0.1f, 0.15f, 1.0f);
    }

    @Override
    public void reshape(GLAutoDrawable d, int x, int y, int w, int h) {
        GL2 gl = d.getGL().getGL2();
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-1, 1, -1, 1, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glColor3f(1, 0, 0); gl.glVertex2f(-0.6f, -0.5f);
        gl.glColor3f(0, 1, 0); gl.glVertex2f( 0.6f, -0.5f);
        gl.glColor3f(0, 0, 1); gl.glVertex2f( 0.0f,  0.6f);
        gl.glEnd();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) { }

    public static void main(String[] args) {
        GLCanvas canvas = new GLCanvas(new GLCapabilities(GLProfile.get(GLProfile.GL2)));
        canvas.addGLEventListener(new HelloTriangulo());

        JFrame frame = new JFrame("Hello Triângulo - JOGL");
        frame.getContentPane().add(canvas);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new FPSAnimator(canvas, 60).start();
    }
}