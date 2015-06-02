package by.zverugo.bsuir.ppvis.grapheditor.algorithm;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.util.GraphEditorKeys;
import by.zverugo.bsuir.ppvis.grapheditor.view.dialogwindows.ColorNumberPane;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 12.05.2015.
 */
public class GraphColorMaker implements ActionListener, Runnable {

    private Tab tabPanel;
    private JFrame frame;
    private int colorNumber;
    private List<String> colorList;
    private int colorCounter;
    private LinkedList<List<Vertex>> graphList;
    private LinkedList <Vertex> vertexList;

    public GraphColorMaker(Tab tabPanel) {
        this.tabPanel = tabPanel;
        frame = this.tabPanel.getFrame();
        graphList = (LinkedList<List<Vertex>>) tabPanel.getVertexStorage().getGraphList();
        vertexList = tabPanel.getVertexStorage().getVertexList();
        colorCounter = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LinkedList <Line> lineList = tabPanel.getLineStorage().getLineList();

        for(Line line : lineList){
            if (line.getStartPoint().x == line.getFinishPoint().x
                && line.getStartPoint().y == line.getFinishPoint().y) {
                JOptionPane.showMessageDialog(null,GraphEditorKeys.ERROR_PAINTING_MSG,
                        GraphEditorKeys.ERROR_MSG, JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        for (Vertex tempVertex : vertexList) {
            tempVertex.removeVertexColor();
        }

        if (!setNumberOfColor()) return;

        int maxLinesNumber = findMaxLinesNumber();

        if (!checkErrorSituation(maxLinesNumber)) return;

        createColorList();

        JOptionPane.showMessageDialog(null,GraphEditorKeys.INF_ALGORITHM_MSG,
                GraphEditorKeys.INF_MSG, JOptionPane.INFORMATION_MESSAGE);

        Thread thread = new Thread(this);
        thread.start();

    }

    private void createColorList() {
        colorList = new ArrayList<String>();
        int colorCount = 0;

        while (colorCount != colorNumber) {
            colorList.add("C" + colorCount);
            colorCount++;
        }
    }

    private boolean setNumberOfColor() {
        ColorNumberPane colorNumberPane = new ColorNumberPane(frame);
        String numberString = colorNumberPane.getColorNumber();

        if (numberString != null && !numberString.equals("")) {
            colorNumber = Integer.valueOf(numberString);
        } else {

            return false;
        }

        return true;
    }

    private int findMaxLinesNumber() {
        LinkedList <Vertex> temp = (LinkedList<Vertex>) graphList.get(0);
        int maxLinesNumber = temp.size();

        for (List<Vertex> list: graphList){
            if (list.size() > maxLinesNumber) {
                maxLinesNumber = list.size();
            }
        }

        return  maxLinesNumber;
    }

    private boolean checkErrorSituation(int maxLinesNumber) {

        if (maxLinesNumber != 0 && colorNumber == 1) {
            JOptionPane.showMessageDialog(null,GraphEditorKeys.ERROR_PAINTING_MSG,
                    GraphEditorKeys.ERROR_MSG, JOptionPane.ERROR_MESSAGE);

            return false;
        }

        if (colorNumber > vertexList.size()) {
            JOptionPane.showMessageDialog(null,GraphEditorKeys.ERROR_PAINTING_MSG,
                    GraphEditorKeys.ERROR_MSG, JOptionPane.ERROR_MESSAGE);

            return false;
        }

        return true;
    }


    @Override
    public void run() {
        int countVertex = 0;
        while (true) {
            for (int vertexIndex = 0; vertexIndex < vertexList.size(); vertexIndex++) {
                try {
                    if (!walkGraph(vertexIndex)) {
                        if (countVertex == vertexList.size() - 1) {
                            JOptionPane.showMessageDialog(null,GraphEditorKeys.ERROR_PAINTING_MSG,
                                    GraphEditorKeys.ERROR_MSG, JOptionPane.ERROR_MESSAGE);
                            for (Vertex tempVertex : vertexList) {
                                tempVertex.removeVertexColor();
                            }

                            return;
                        }
                        changeFirstVertex();
                        vertexIndex = -1;
                        countVertex++;
                    }
                } catch (InterruptedException exc) {
                    System.out.println(exc.toString());
                }
            }
          return;
        }
    }

    private void changeFirstVertex() {
        Vertex replacedVertex = vertexList.get(0);
        vertexList.remove(0);
        vertexList.add(replacedVertex);
        LinkedList<Vertex> temp = (LinkedList<Vertex>) graphList.get(0);
        graphList.remove(0);
        graphList.add(temp);

        for (Vertex tempVertex : vertexList) {
            tempVertex.removeVertexColor();
        }
    }

    private boolean walkGraph(int vertex) throws  InterruptedException {
        vertexList.get(vertex).setErrorVertexColor(Color.green);
        Thread.sleep(800);

        if (vertexList.get(vertex).isColorMarker()) {
            vertexList.get(vertex).setErrorVertexColor(Color.yellow);
            Thread.sleep(1000);
            vertexList.get(vertex).setErrorVertexColor(Color.green);
            Thread.sleep(500);
            vertexList.get(vertex).setErrorVertexColor(Color.gray);
            return true;
        }

        LinkedList <Vertex> tempVertexList = (LinkedList<Vertex>) graphList.get(vertex);

        int tempColor = colorCounter;
        boolean flag = false;

        for (int vertexIndex = 0; vertexIndex < tempVertexList.size(); vertexIndex++) {
            if (tempColor == colorCounter && flag){
                vertexList.get(vertex).setErrorVertexColor(Color.gray);
                return false;
            }

            tempVertexList.get(vertexIndex).setErrorVertexColor(Color.red);
            Thread.sleep(1000);

            tempVertexList.get(vertexIndex).setErrorVertexColor(Color.gray);
            Thread.sleep(400);

            if (tempVertexList.get(vertexIndex).getColor().equals(colorList.get(colorCounter))) {
                if (colorCounter < colorList.size() - 1) {
                    colorCounter++;
                    flag = true;
                    vertexIndex = -1;
                } else {
                    colorCounter = 0;
                    vertexIndex = -1;
                }
            }
        }


        Thread.sleep(1000);
        vertexList.get(vertex).setVertexColor(colorList.get(colorCounter));

        if (colorCounter == colorList.size() - 1)
            colorCounter = 0;
        else
            colorCounter++;

        for (Vertex tempVertex : tempVertexList){
            vertexList.get(vertex).setErrorVertexColor(Color.gray);
            if (!walkGraph(vertexList.indexOf(tempVertex))){
                return false;
            }
        }

        vertexList.get(vertex).setErrorVertexColor(Color.gray);

        return true;
    }
}
