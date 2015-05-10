package by.zverugo.bsuir.ppvis.grapheditor.storages;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;

import java.util.LinkedList;

/**
 * Created by Alex on 10.05.2015.
 */
public class VertexStorage {

    private LinkedList <Vertex> vertexList;

    public VertexStorage() {
        vertexList = new LinkedList<Vertex>();
    }

    public Vertex getVertex(int indx)  {

        return vertexList.get(indx);
    }

    public void setVertex(Vertex vertex) {
        vertexList.add(vertex);
    }

    public void removeVertex(Vertex vertex){
        vertexList.remove(vertex);
    }

}
