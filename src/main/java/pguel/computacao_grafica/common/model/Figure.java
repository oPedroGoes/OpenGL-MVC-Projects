package pguel.computacao_grafica.common.model;

import pguel.computacao_grafica.common.util.PointUtils;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Figure {
    private List<Point> fig;
    private Point currentPoint;

    public Figure(){
        fig = new ArrayList<>();
    }
    public Point getCurrentPoint(){
        return currentPoint;
    }

    public List<Point> getPoints(){
        return Collections.unmodifiableList(fig);
    }
    public void setCurrentPoint(Point currentPoint){
        this.currentPoint = currentPoint;
    }
    public boolean addPoint(Point newPoint){
        System.out.println("Adicionando ponto: " + newPoint.getX() + " " + newPoint.getY());
        return fig.add(newPoint);
    }
    public boolean popPoint(Point oldPoint) {
        System.out.println("popPoint" + oldPoint.getX() + " y=" + oldPoint.getY());
        return fig.remove(oldPoint);
    }
    public Point popPoint(int index){
        return fig.remove(index);
    }

    public Point findNearest(Point clickPoint){
        if (fig.isEmpty()){
            throw new IllegalStateException("A figura deve possuir pontos para que se encontre o mais próximo.");
        }
        Point nearest = fig.get(0);
        float distNearest = PointUtils.distance(clickPoint, fig.get(0));
        for(Point itPoint : fig){
            float newDist = PointUtils.distance(clickPoint, itPoint);
            if (newDist < distNearest){
                nearest = itPoint;
                distNearest = newDist;
            }
        }

        return nearest;
    }
}
