package by.zverugo.bsuir.ppvis.grapheditor.storages;

import javax.swing.JComponent;
import java.util.LinkedList;

/**
 * Created by Alex on 23.03.2015.
 */

public class VertexStorage {

    private LinkedList <JComponent> vertexList;
    private int vertexCount;

    public VertexStorage() {
        vertexList = new LinkedList<JComponent>();
        vertexCount = 0;
    }

    public JComponent getVertex(int indx)  {

        return vertexList.get(indx);
    }

    public void setVertex(JComponent component) {
        vertexList.add(component);
        vertexCount++;
    }

    public void removeVertex(JComponent component){
        vertexList.remove(component);
        vertexCount--;
    }

    public  int getSize () {

        return vertexList.size();
    }

    public LinkedList getVertexList() {

        return vertexList;
    }
}
