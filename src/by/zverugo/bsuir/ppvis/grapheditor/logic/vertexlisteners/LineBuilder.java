package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.LinePainter;
import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.VertexStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by Alex on 24.03.2015.
 */
public class LineBuilder extends MouseAdapter {

    private GEToolBar toolBar;
    private LineStorage lineStorage;
    private Vertex currentVertex;
    private Tab tabPanel;
    private LinePainter linePainter;
    private VertexStorage vertexStorage;



    public LineBuilder(Vertex currentVertex, JComponent tabPanel) {
        this.tabPanel = (Tab)tabPanel;
        toolBar = this.tabPanel.getToolBar();
        lineStorage = this.tabPanel.getLineStorage();
        this.currentVertex = currentVertex;
        linePainter = this.tabPanel.getLinePainter();
        vertexStorage = ((Tab) tabPanel).getVertexStorage();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (toolBar.getArcButton().isSelected()) {
            if (lineStorage.getCurrentIndex() == 0) {
                lineStorage.setPoint(currentVertex.getCoordinate());
                lineStorage.setVertexSelected(true);
                lineStorage.setVertexBuffer(currentVertex);
                moveMouse();
            } else
            if (lineStorage.getCurrentIndex() == 1) {
                if (currentVertex.getCoordinate().equals(lineStorage.getFirstPoint())) {
                    lineStorage.setPoint(currentVertex.getCoordinate());
                    Line line = new Line(lineStorage.getFirstPoint(), lineStorage.getSecondPoint());
                    lineStorage.setLine(line);
                    line.setVertexes(lineStorage.getStartVertex(),currentVertex);
                    lineStorage.setVertexSelected(false);
                    linePainter.clearLastTempLine();
                    linePainter.drawLoop(currentVertex.getCoordinate().x, currentVertex.getCoordinate().y);
                    lineStorage.reset();
                    tabPanel.repaint();
                    return;
                }
                lineStorage.setPoint(currentVertex.getCoordinate());
                Line line = new Line(lineStorage.getFirstPoint(), lineStorage.getSecondPoint());
                lineStorage.setLine(line);
                line.setVertexes(lineStorage.getStartVertex(),currentVertex);
                vertexStorage.setVertexToList(vertexStorage.getVertexIndex(lineStorage.getStartVertex()), currentVertex);
                vertexStorage.setVertexToList(vertexStorage.getVertexIndex(currentVertex),lineStorage.getStartVertex());
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
                if (!toolBar.getArcButton().isSelected() && lineStorage.isVertexSelected()) {
                    linePainter.clearLastTempLine();
                    tabPanel.removeMouseMotionListener(this);
                    tabPanel.repaint();
                    lineStorage.reset();
                }

            }
        });
    }


}
