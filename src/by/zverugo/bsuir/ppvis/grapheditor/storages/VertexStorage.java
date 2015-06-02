package by.zverugo.bsuir.ppvis.grapheditor.storages;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 10.05.2015.
 */
public class VertexStorage {

    private LinkedList <Vertex> vertexList;
    private List<List<Vertex>> graphList;

    public VertexStorage() {
        vertexList = new LinkedList<Vertex>();
        graphList = new LinkedList<List<Vertex>>();
    }

    public int getVertexIndex(Vertex vertex)  {

        return vertexList.indexOf(vertex);
    }

    public void setVertexToList(int vertexIndex,Vertex vertex) {

        graphList.get(vertexIndex).add(vertex);
    }



    public void setLineToGraph() {
        graphList.add(new LinkedList<Vertex>());
    }

    public void setLineTwoVertex (Vertex startVertex, Vertex finishVertex) {
        graphList.get(vertexList.indexOf(startVertex)).add(finishVertex);
        graphList.get(vertexList.indexOf(finishVertex)).add(startVertex);

    }

    public void removeLineList(int vertexIndex) {
        graphList.remove(vertexIndex);
    }

    public void removeVertexFromGraph(Vertex vertex) {
        int index = vertexList.indexOf(vertex);
        LinkedList<Vertex> currentVertexList = (LinkedList<Vertex>) graphList.get(index);

        for(Vertex temp : currentVertexList) {
            graphList.get(vertexList.indexOf(temp)).remove(vertex);
        }

    }

    public void removeLineFromGraph(Vertex startVertex, Vertex finishVertex) {
        graphList.get(vertexList.indexOf(startVertex)).remove(finishVertex);
        graphList.get(vertexList.indexOf(finishVertex)).remove(startVertex);
    }

    public void setVertex(Vertex vertex) {
        vertexList.add(vertex);
    }

    public void removeVertex(Vertex vertex){
        vertexList.remove(vertex);
    }

    public LinkedList <Vertex> getVertexList() {

        return vertexList;
    }

    public int getVertexListSize() {
        return vertexList.size();
    }

    public List<List<Vertex>> getGraphList() {

        return graphList;
    }

}
