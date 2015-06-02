package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.LinePainter;
import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.VertexStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class VertexRemover extends MouseAdapter {
    private GEToolBar toolBar;
    private Vertex currentVertex;
    private Tab tabPanel;
    private JLabel vertexLabel;
    private LineStorage lineStorage;
    private VertexStorage vertexStorage;
    private LinePainter linePainter;

    public VertexRemover(JPanel currentVertex, JComponent tabPanel,JLabel vertexLabel) {
        this.tabPanel = (Tab)tabPanel;
        this.currentVertex = (Vertex) currentVertex;
        this.vertexLabel = vertexLabel;
        toolBar = this.tabPanel.getToolBar();
        lineStorage = this.tabPanel.getLineStorage();
        vertexStorage = this.tabPanel.getVertexStorage();
        linePainter = this.tabPanel.getLinePainter();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (toolBar.getDeleteVertexButton().isSelected()) {
            Point vertexCoordinate = currentVertex.getCoordinate();
            Line temp = lineStorage.getForRemove(vertexCoordinate);

            while(temp != null){
                if (temp.getStartPoint().x == temp.getFinishPoint().x
                        && temp.getStartPoint().y == temp.getFinishPoint().y) {
                    linePainter.removeLoop(temp.getStartPoint().x, temp.getStartPoint().y);
                    temp = lineStorage.getForRemove(vertexCoordinate);
                    continue;
                }
                linePainter.removeLine(temp.getStartPoint().x, temp.getStartPoint().y,
                        temp.getFinishPoint().x, temp.getFinishPoint().y);
                tabPanel.remove(temp.getWeightLabel());
                temp = lineStorage.getForRemove(vertexCoordinate);
                tabPanel.repaint();
            }

            LinkedList <Line> lineList = lineStorage.getLineList();

            for(Line line : lineList){
                if (line.getStartPoint().x == line.getFinishPoint().x
                        && line.getStartPoint().y == line.getFinishPoint().y) {
                    linePainter.drawLoop(line.getStartPoint().x,line.getStartPoint().y);
                    continue;
                }

                linePainter.drawLine(line.getStartPoint().x, line.getStartPoint().y,
                        line.getFinishPoint().x, line.getFinishPoint().y);
                }

            vertexStorage.removeVertexFromGraph(currentVertex);
            vertexStorage.removeLineList(vertexStorage.getVertexList().indexOf(currentVertex));

            vertexStorage.removeVertex(currentVertex);
            tabPanel.remove(currentVertex);
            tabPanel.remove(vertexLabel);
            tabPanel.repaint();
        }
    }
}
