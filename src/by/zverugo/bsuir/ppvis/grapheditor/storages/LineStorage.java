package by.zverugo.bsuir.ppvis.grapheditor.storages;

import javax.swing.JComponent;
import java.awt.Point;
import java.util.LinkedList;

/**
 * Created by Alex on 23.03.2015.
 */
public class LineStorage {
    private Point[] buffer;
    private LinkedList <JComponent> lineList;
    private int currentIndx;
    private boolean flag;

    public LineStorage(){
        buffer = new Point[2];
        lineList = new LinkedList<JComponent>();
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

    public void setLine(JComponent component){
        lineList.add(component);
    }

    public void setPoint(Point vertex) {
        buffer[currentIndx] = vertex;
        currentIndx++;
    }

    public void reset(){
        buffer[0] = null;
        buffer[1] = null;
        currentIndx = 0;
    }

    public int getCurrentIndx() {
        return currentIndx;
    }
}
