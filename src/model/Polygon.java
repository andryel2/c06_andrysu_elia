package model;

import java.util.ArrayList;
import java.util.List;


public class Polygon {

    private ArrayList<Point> points;

    public Polygon(){
        points = new ArrayList<>();
    }

    public void add(Point point){
        points.add(point);
    }

    public Point get(int index){
        return points.get(index);
    }

    public int size(){
        return points.size();
    }

    public void clear(){
        points.clear();
    }


    public List<Line> getLines(){
        List<Line> lines = new ArrayList<>();
        for(int i = 0; i< points.size()-1;i++) {
            lines.add(new Line(points.get(i),points.get(i+1),0));
        }
        lines.add(new Line(points.get(points.size()-1),points.get(0),0));
        return lines;
    }


}
