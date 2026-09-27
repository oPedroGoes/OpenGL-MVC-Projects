package pguel.computacao_grafica.common.view;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.OrthoBounds;
import pguel.computacao_grafica.common.model.Point;

public class PointView implements GLEventListener {

    private Figure figure;
    private OrthoBounds ortho;
    private GL2 gl;

    public PointView(Figure figure, OrthoBounds ortho){
        this.figure = figure;
        this.ortho = ortho;
    }

    @Override
    public void init(GLAutoDrawable drawable){
        gl = drawable.getGL().getGL2();
        gl.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glOrtho(ortho.getLeft(), ortho.getRight(), ortho.getBottom(), ortho.getTop(),-1.0, 1.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    @Override
    public void display(GLAutoDrawable drawable){
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glPointSize(50);

        gl.glBegin(GL2.GL_POINTS);
            gl.glColor3f(1.0f, 1.0f, 0.5f);
            for (Point point : figure.getPoints()){
                gl.glVertex2f(point.getX(), point.getY());
            }
        gl.glEnd();
        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){
        gl = drawable.getGL().getGL2();
        if (height <= 0) height = 1;
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void dispose(GLAutoDrawable drawable){

    }

}
