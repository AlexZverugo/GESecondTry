package by.zverugo.bsuir.ppvis.grapheditor.logic;

import javax.swing.JLabel;
import java.awt.Point;

/**
 * Created by Alex on 10.05.2015.
 */
public class Line {

    private Point start;
    private Point finish;
    private String weight;
    private JLabel weightLabel;
    private Vertex startVertex;
    private Vertex finishVertex;

    public Line(Point start, Point finish) {
        this.start = start;
        this.finish = finish;
        weight = " ";
        weightLabel = new JLabel(weight);
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {

        return weight;
    }

    public  void setWeightLabel(JLabel weightLabel) {
        this.weightLabel = weightLabel;
    }

    public JLabel getWeightLabel() {

        return weightLabel;
    }

    public Point getStartPoint() {

        return start;
    }

    public Point getFinishPoint() {

        return finish;
    }

    public void setVertexes(Vertex startVertex,Vertex finishVertex) {
        this.startVertex = startVertex;
        this.finishVertex = finishVertex;
    }

    public Vertex getStartVertex() {

        return startVertex;
    }

    public Vertex getFinishVertex() {

        return  finishVertex;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setFinish(Point finish) {
        this.finish = finish;
    }
}
