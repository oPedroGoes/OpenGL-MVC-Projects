package pguel.computacao_grafica;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.GLCapabilities;

import javax.swing.JFrame;

public class Basics implements GLEventListener {

    int[][] matriz = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0}
    };

	private GL2 gl;
	public Basics() {
		gl = null;
	}
	
    @Override
    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        gl.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glPointSize(50);
        gl.glBegin(GL2.GL_POINTS);

        gl.glColor3f(1.0f, 0.0f, 0.0f);
        int ROW = 8;
        int COL = 8;
            for (int i = 0; i < ROW; i++){
                for (int j = 0; j < COL; j++){
                    if(matriz[i][j] == 1){
                        float x = -1 + ((float)j/(ROW-1))*2;
                        float y = 1 - ((float)i/(COL-1))*2;
                        gl.glVertex2f(x, y);
                    }
                }
            }

        gl.glEnd();
        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        if (height <= 0) height = 1;
        gl.glViewport(0, 0, width, height);
    }

    public static void main(String[] args) {
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLJPanel panel = new GLJPanel(capabilities);
        panel.addGLEventListener(new Basics());
        panel.setSize(500, 500);

        JFrame frame = new JFrame("Teste");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
