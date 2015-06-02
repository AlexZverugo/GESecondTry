package by.zverugo.bsuir.ppvis.grapheditor.storages;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;

import java.awt.Point;
import java.util.LinkedList;

/**
 * Created by Alex on 23.03.2015.
 */
public class LineStorage {
    private Point[] buffer;
    private Vertex startVertex;
    private LinkedList <Line> lineList;
    private int currentIndex;
    private boolean flag;

    public LineStorage(){
        buffer = new Point[2];
        lineList = new LinkedList <Line>();
        currentIndex = 0;
    }

    public boolean checkBuffer() {
        if (currentIndex == 2) {
            return true;
        } else {
            return false;
        }
    }

    public void setVertexBuffer(Vertex vertex) {
        startVertex = vertex;
    }

    public Vertex getStartVertex() {

        return startVertex;
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
        if (currentIndex == 2)
            return buffer[1];
        else
            return null;
    }


    public void setPoint(Point vertex) {
        buffer[currentIndex] = vertex;
        currentIndex++;
    }

    public void reset(){
        buffer[0] = null;
        buffer[1] = null;
        startVertex = null;
        currentIndex = 0;
    }

    public void setLine(Line line){

        lineList.add(line);
    }

    public void removeLine(Line line){
        lineList.remove(line);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }


//    public void removePointOfLine(Point point){
//        lineList.remove(point);
//    }

    public Line getForRemove(Point point){

        for (Line line : lineList){
            if ((line.getFinishPoint().x == point.x && line.getFinishPoint().y == point.y)
                    || (line.getStartPoint().x == point.x && line.getStartPoint().y == point.y)) {
                lineList.remove(line);
                return line;
            }
        }

        return null;
    }

    public int getLineListSize() {
        return lineList.size();
    }

    public LinkedList<Line> getLineList() {

        return lineList;
    }
}
