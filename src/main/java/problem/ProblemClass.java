package problem;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// класс для решения задачи
public class ProblemClass {
    ArrayList<PointClass> points;

    public ProblemClass() {
        points = new ArrayList<>();
    }
    // добавить точку по координатам и номеру множества
    public void addPoint(double x, double y, int setVal) {
        PointClass point = new PointClass(x, y, setVal);
        points.add(point);
    }
    // решение задачи
    public void solve(){
        // перебираем пары точек
        for(PointClass p : points){
            for(PointClass p2:points){
                // если точки являются разыми
                if(p!=p2){
                    // если координаты у них совпадают
                    if(p.getPos().equals(p2.getPos())){
                        p.setCrossed(true);
                        p2.setCrossed(true);
                    }
                }
            }
        }
    }
    // добавить готовую точку
    public void addPoint(PointClass point) {
        points.add(point);
    }
    // загрузить точки из файла
    public void loadFromFile(String filename) {
        points.clear();
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                int setVal = sc.nextInt();
                sc.nextLine();
                PointClass point = new PointClass(x,y,setVal);
                points.add(point);
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: "+ex);
        }
    }
    // сохранение в файл
    public void saveToFile(String filename) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(filename));
            for(PointClass point : points){
                Vector2 pos = point.getPos();
                out.printf("%.2f %.2f %d\n",pos.getX(),pos.getY(),point.getSetVal());
            }
            out.close();
        }
        catch(IOException ex) {
            System.out.println("Ошибка записи в файл: "+ex);
        }
    }
    public ArrayList<PointClass> getPoints() {
        return points;
    }
    // добавляем заданное число случайных точек
    public void addRandomPoints(int n){
        for (int i = 0; i < n; i++) {
            PointClass p = PointClass.getRandomPoint();
            points.add(p);
        }
    }
    // удалить точки
    public void deletePoints(){
        points.clear();
    }
}
