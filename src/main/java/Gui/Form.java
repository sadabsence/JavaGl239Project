package Gui;

import problem.PointClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// форма нашего приложения
public class Form extends JFrame {

    private static final String FILE_NAME = "points.txt";

    // панель для отображения OpenGL
    private JPanel GLPlaceholder;
    private JPanel root;
    private JLabel camText;
    private JTextField xPointField;
    private JTextField yPointField;
    private JButton randomBtn;
    private JTextField pointCntField;
    private JButton loadFromFileBtn;
    private JButton saveToFileBtn;
    private JButton clearBtn;
    private JButton solveBtn;
    private JLabel problemText;
    private JButton addPoint;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;

    final Timer timer;

    // рисовалка OpenGL
    private final RendererGL renderer;

    // Конструктор формы
    public Form() {
        super("OpenGL Tutorial");
        // инициализируем OpenGL
        renderer = new RendererGL();
        // связываем элемент на форме с рисовалкой OpenGL
        GLPlaceholder.setLayout(new BorderLayout());
        GLPlaceholder.add(renderer.getCanvas());
        // указываем главный элемент формы
        getContentPane().add(root);
        // задаём размер формы
        setSize(getPreferredSize());
        // показываем форму
        setVisible(true);
        // обработчик зарытия формы
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(() -> {
                    renderer.close();
                    timer.stop();
                    System.exit(0);
                }).start();
            }
        });
        // тинициализация таймера, срабатывающего раз в 100 мсек
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onTime();
            }
        });
        timer.start();
        initWidgets();
    }

    private void initWidgets() {
        // задаём текст полю описания задачи
        problemText.setText(convertToMultiline(problemStr));
        // делаем первое радио выбранным
        radioButton1.setSelected(true);
        radioButton2.setSelected(false);

        addPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x = Double.parseDouble(xPointField.getText());
                double y = Double.parseDouble(yPointField.getText());
                int setVal = radioButton1.isSelected()? PointClass.SET_1:PointClass.SET_2;
                renderer.problem.addPoint(x,y,setVal);
            }
        });
        randomBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.addRandomPoints(Integer.parseInt(pointCntField.getText()));
            }
        });
        loadFromFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.loadFromFile(FILE_NAME);
            }
        });
        saveToFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.saveToFile(FILE_NAME);
            }
        });
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.deletePoints();
            }
        });
        solveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.solve();
            }
        });
    }

    private void onTime() {
        // события по таймеру
    }

    // преобразование в html
    private static String convertToMultiline(String orig) {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }

    private static final String problemStr = "ПОСТАНОВКА ЗАДАЧИ:\nЗаданы два множества точек в пространстве.\n Требуется построить пересечения и разность этих множеств";

    public static void main(String[] args) {
        new Form();
    }

}
