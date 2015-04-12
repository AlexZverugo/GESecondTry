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

    public int checkBuffer() {
        if (currentIndx == 0) {
            return 0;
        } else {
            return 1;
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

        return buffer[1];
    }

    public void setLine(JComponent component){
        lineList.add(component);
    }

    public void setPoint(int x, int y){
        buffer[currentIndx] = new Point(x,y);
        currentIndx++;
    }

    public void reset(){
        buffer[0] = null;
        buffer[1] = null;
        currentIndx = 0;
    }
}
