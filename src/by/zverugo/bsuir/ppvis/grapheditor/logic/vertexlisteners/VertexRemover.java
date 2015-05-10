package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.logic.LinePainter;
import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.storages.GraphStorage;
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
    private GraphStorage graphStorage;
    private LineStorage lineStorage;
    private VertexStorage vertexStorage;
    private LinePainter linePainter;

    public VertexRemover(JPanel currentVertex, JComponent tabPanel,JLabel vertexLabel, GraphStorage graphStorage) {
        this.tabPanel = (Tab)tabPanel;
        this.graphStorage = graphStorage;
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
            Point temp = lineStorage.getForRemove(vertexCoordinate);

            while(temp != null){
                linePainter.removeLine(temp.x, temp.y, vertexCoordinate.x, vertexCoordinate.y);
                temp = lineStorage.getForRemove(vertexCoordinate);
                tabPanel.repaint();
            }

            LinkedList <Point> lineList = lineStorage.getLineList();
            boolean isEven = false;
            Point firstPoint = new Point();

            for(Point point : lineList){
                if(!isEven){
                    firstPoint = point;
                    isEven = true;
                } else {
                    linePainter.drawLine(firstPoint.x, firstPoint.y, point.x, point.y);
                    isEven = false;
                }

            }

            vertexStorage.removeVertex(currentVertex);
            graphStorage.removeVertex(tabPanel, currentVertex);
            tabPanel.remove(vertexLabel);
            tabPanel.repaint();
        }
    }
}
