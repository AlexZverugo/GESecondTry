package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.logic.LinePainter;
import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.storages.GraphStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by Alex on 24.03.2015.
 */
public class LineBuilder extends MouseAdapter {

    private GEToolBar toolBar;
    private LineStorage lineStorage;
    private GraphStorage graphStorage;
    private Vertex currentVertex;
    private Tab tabPanel;
    private LinePainter linePainter;



    public LineBuilder(Vertex currentVertex, JComponent tabPanel, GraphStorage graphStorage) {
        this.tabPanel = (Tab)tabPanel;
        toolBar = this.tabPanel.getToolBar();
        this.graphStorage = graphStorage;
        lineStorage = this.tabPanel.getLineStorage();
        this.currentVertex = currentVertex;
        linePainter = this.tabPanel.getLinePainter();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (toolBar.getArcButton().isSelected()) {
            if (lineStorage.getCurrentIndx() == 0) {
                lineStorage.setPoint(currentVertex.getCoordinate());
//                lineStorage.setPointOfLine(currentVertex.getCoordinate());
                lineStorage.setVertexSelected(true);
                graphStorage.setTempVertex(currentVertex);
                moveMouse();
            } else
            if (lineStorage.getCurrentIndx() == 1) {
                lineStorage.setPoint(currentVertex.getCoordinate());
//                lineStorage.setPointOfLine(currentVertex.getCoordinate());
                lineStorage.setVertexSelected(false);
                linePainter.clearLastTempLine();
                linePainter.drawLine(lineStorage.getFirstPoint().x, lineStorage.getFirstPoint().y,
                        lineStorage.getSecondPoint().x, lineStorage.getSecondPoint().y);
                lineStorage.reset();
                tabPanel.setState(true);
                tabPanel.repaint();

            }
        }
    }

    private void moveMouse() {
        tabPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                if (lineStorage.isVertexSelected()) {
                    linePainter.drawTempLine(currentVertex.getCoordinate().x, currentVertex.getCoordinate().y, e.getX(), e.getY());
                    tabPanel.repaint();
                }
                if (!lineStorage.isVertexSelected() && lineStorage.checkBuffer()) {
                    tabPanel.removeMouseMotionListener(this);
                }
                if (!toolBar.getArcButton().isSelected() && lineStorage.getFirstPoint() != null) {
                    linePainter.clearLastTempLine();
                    tabPanel.removeMouseMotionListener(this);
                    lineStorage.removePointOfLine(currentVertex.getCoordinate());
                    tabPanel.repaint();
                    lineStorage.reset();
                }

            }
        });
    }


}
