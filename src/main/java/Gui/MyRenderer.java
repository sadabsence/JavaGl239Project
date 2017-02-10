package Gui;

import problem.PointClass;
import problem.ProblemClass;
import problem.Vector2;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import static com.jogamp.opengl.util.ImmModeSink.GL_QUADS;

/**
 * Created by teacher on 10.02.17.
 */
public class MyRenderer {
    // рисование сцены
    public static void drawScene(GL2 gl, ProblemClass problem) {
        gl.glPointSize(3);
        gl.glBegin(GL.GL_POINTS);
        for (PointClass point : problem.getPoints()) {
            Vector2 pos = point.getPos();
            if (point.isCrossed())
                gl.glColor3d(1.0, 0.0, 0.0);
            else
                switch (point.getSetVal()) {
                    case PointClass.SET_1:
                        gl.glColor3d(0.0, 1.0, 0.0);
                        break;
                    case PointClass.SET_2:
                        gl.glColor3d(0.0, 0.0, 1.0);
                        break;
                }
            gl.glVertex2d(pos.getX(), pos.getY());
        }
        gl.glEnd();
        gl.glPointSize(1);
    }

}
