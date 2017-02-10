package Gui;

import com.jogamp.opengl.util.FPSAnimator;
import problem.ProblemClass;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;


// рисовалка openGL
class RendererGL implements GLEventListener {
    // специальная переменная для связи с OpenGL
    private GLU glu;
    // то, где рисуется OpenGL-сцена
    private final GLCanvas canvas;
    // перерисовщик окна
    private FPSAnimator animator;
    // класс для решения поставленной задачи
    final ProblemClass problem;


    // вызывается при первой отрисовке кадра
    @Override
    public void init(GLAutoDrawable drawable) {
        // получаем класс для работы с openGL
        GL2 gl = drawable.getGL().getGL2();
        // задаём тип матриц преобразований
        gl.glMatrixMode(GL2.GL_3D);
        // задаём функцию проверки глубины
        gl.glDepthFunc(GL2.GL_LEQUAL);
        // задаём цвет фона
        gl.glClearColor(0.04f, 0.04f, 0.14f, 1.0f);
        // инициализируем объект для работы с glut
        glu = new GLU();
        // запускаем перерисовку кадров с частотой 60 кадров в секунду
        animator = new FPSAnimator(canvas, 60, true);
        animator.start();
    }

    // отрисовка кадра OpenGL
    @Override
    public void display(GLAutoDrawable drawable) {
        // получаем класс для работы с openGL
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Set background color to black and opaque
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);         // Clear the color buffer (background)
        MyRenderer.drawScene(gl,problem);
        // объязываем openGL отрисовать всю сцену и только потом рисовать следующий кадр
        gl.glFlush();
    }

    // конструктор
    RendererGL() {
        // создаём OpenGL-профиль
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        this.canvas = new GLCanvas(capabilities);
        canvas.addGLEventListener(this);
        // переменная класса решения задачи
        problem = new ProblemClass();
    }

    // геттер рисовалки
    GLCanvas getCanvas() {
        return canvas;
    }

    public void close() {
        animator.stop();
        System.out.println("terminated");
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {
        // обработчик закрытия формы
        animator.stop();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        // если изменились размеры окна
        // т.к. у нас размеры окна динамически вычисляются в display(), то нам этот метод не нужен
    }


}