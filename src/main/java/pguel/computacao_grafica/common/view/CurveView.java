package pguel.computacao_grafica.common.view;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import pguel.computacao_grafica.common.builder.BSplineBuilder;
import pguel.computacao_grafica.common.model.Figure;
import pguel.computacao_grafica.common.model.OrthoBounds;
import pguel.computacao_grafica.common.model.Point;

public class CurveView implements GLEventListener {

    private Figure controls;
    private OrthoBounds ortho;
    private GL2 gl;

    public CurveView(Figure controls, OrthoBounds ortho){
        this.controls = controls;
        this.ortho = ortho;
    }

    @Override
    public void init(GLAutoDrawable drawable){
        gl = drawable.getGL().getGL2();
        gl.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glOrtho(ortho.getLeft(), ortho.getRight(), ortho.getBottom(), ortho.getTop(), -1.0, 1.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    @Override
    public void display(GLAutoDrawable drawable){
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glColor3f(0.4f, 0.4f, 0.4f);
        gl.glBegin(GL2.GL_LINE_STRIP);
        for (Point point : controls.getPoints()){
            gl.glVertex2f(point.getX(), point.getY());
        }
        gl.glEnd();

        gl.glColor3f(1.0f, 1.0f, 0.5f);
        gl.glBegin(GL2.GL_LINE_STRIP);
        for (Point point : BSplineBuilder.build(controls).getPoints()){
            gl.glVertex2f(point.getX(), point.getY());
        }
        gl.glEnd();

        gl.glPointSize(8);
        gl.glBegin(GL2.GL_POINTS);
        for (Point point : controls.getPoints()){
            if (point == controls.getCurrentPoint()){
                gl.glColor3f(1.0f, 0.3f, 0.3f);
            } else {
                gl.glColor3f(0.4f, 0.8f, 1.0f);
            }
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
