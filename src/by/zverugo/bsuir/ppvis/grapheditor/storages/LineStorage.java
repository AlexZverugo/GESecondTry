package by.zverugo.bsuir.ppvis.grapheditor.storages;

import java.awt.Point;
import java.util.LinkedList;

/**
 * Created by Alex on 23.03.2015.
 */
public class LineStorage {
    private Point[] buffer;
    private LinkedList <Point> lineList;
    private LinkedList <String> weightList;
    private int currentIndx;
    private boolean flag;

    public LineStorage(){
        buffer = new Point[2];
        lineList = new LinkedList <Point>();
        weightList = new LinkedList <String>();
        currentIndx = 0;
    }

    public boolean checkBuffer() {
        if (currentIndx == 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isVertexSelected() {
        return flag;
    }

    public void setVertexSelected(boolean flag) {
        this.flag = flag;
    }

    public Point getFirstPoint(){

        return buffer[0];
    }

    public Point getSecondPoint(){
        if (currentIndx == 2)
            return buffer[1];
        else
            return null;
    }


    public void setPoint(Point vertex) {
        buffer[currentIndx] = vertex;
        currentIndx++;
        lineList.add(vertex);
    }

    public void reset(){
        buffer[0] = null;
        buffer[1] = null;
        currentIndx = 0;
    }

    public int getCurrentIndx() {
        return currentIndx;
    }


//    public void setPointOfLine(Point point){
//        lineList.add(point);
//    }

    public void removePointOfLine(Point point){
        lineList.remove(point);
    }

    public Point getForRemove(Point point){
        Point boundaryPoint;
        int pointIndex = lineList.indexOf(point);
        if (pointIndex == -1)
            return null;

        if (lineList.indexOf(point) % 2 == 0){
            boundaryPoint = lineList.get(pointIndex + 1);
            lineList.remove(pointIndex);
            lineList.remove(pointIndex);
        }
        else {
            boundaryPoint = lineList.get(pointIndex - 1);
            lineList.remove(pointIndex);
            lineList.remove(pointIndex - 1);
        }
        return boundaryPoint;
    }

    public LinkedList<Point> getLineList() {

        return lineList;
    }

    public void setWeight(String weight) {
       weightList.add(weight);
    }

    public void removeWeight(int index){
        weightList.remove(index);
    }

    public LinkedList<String> getWeightList() {

        return weightList;
    }

    public String getWeight(int index) {

        return weightList.get(index);
    }
}
