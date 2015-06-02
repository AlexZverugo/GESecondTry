package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.LinePainter;
import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

/**
 * Created by Alex on 08.05.2015.
 */
public class VertexMover  implements MouseMotionListener {

    private Tab tabPanel;
    private Vertex currentVertex;
    private GEToolBar toolBar;
    private LineStorage lineStorage;
    private LinkedList<Line> lines;
    private LinkedList<Vertex> fixedVertex;
    private LinePainter linePainter;
    private boolean flag;

    public VertexMover (JComponent tabPanel, JComponent currentVertex) {
        this.tabPanel = (Tab) tabPanel;
        this.currentVertex = (Vertex) currentVertex;
        this.toolBar = this.tabPanel.getToolBar();
        lineStorage = this.tabPanel.getLineStorage();
        linePainter = new LinePainter(this.tabPanel);
        lines = new LinkedList<Line>();
        fixedVertex = new LinkedList<Vertex>();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(toolBar.getMoveVertexButton().isSelected()){


            for (Line line : lineStorage.getLineList()) {
                if (line.getStartPoint().x == currentVertex.getCoordinate().x
                        && line.getStartPoint().y == currentVertex.getCoordinate().y) {
                    linePainter.removeLine(line.getStartPoint().x, line.getStartPoint().y,
                            line.getFinishPoint().x, line.getFinishPoint().y);
                    fixedVertex.add(line.getFinishVertex());
                    lines.add(line);
                } else
                if (line.getFinishPoint().x == currentVertex.getCoordinate().x
                        && line.getFinishPoint().y == currentVertex.getCoordinate().y) {
                    linePainter.removeLine(line.getStartPoint().x, line.getStartPoint().y,
                            line.getFinishPoint().x, line.getFinishPoint().y);
                    fixedVertex.add(line.getStartVertex());
                    lines.add(line);

                }
            }

            flag = false;


            for (Line line : lines) {
                if (line.getStartPoint().x == currentVertex.getCoordinate().x
                        && line.getStartPoint().y == currentVertex.getCoordinate().y) {
                    linePainter.removeLine(line.getStartPoint().x,line.getStartPoint().y,line.getFinishPoint().x,line.getFinishPoint().y);
                    linePainter.drawLine(fixedVertex.get(lines.indexOf(line)).getCoordinate().x,
                            fixedVertex.get(lines.indexOf(line)).getCoordinate().y,e.getComponent().getX() + e.getX(), e.getComponent().getY() + e.getY());
                    line.setStart(new Point(e.getComponent().getX() + e.getX(), e.getComponent().getY() + e.getY()));
                } else {
                    linePainter.removeLine(fixedVertex.get(lines.indexOf(line)).getCoordinate().x,
                            fixedVertex.get(lines.indexOf(line)).getCoordinate().y, line.getFinishPoint().x, line.getFinishPoint().y);
                    linePainter.drawLine(e.getComponent().getX() + e.getX(), e.getComponent().getY() + e.getY(), fixedVertex.get(lines.indexOf(line)).getCoordinate().x,
                            fixedVertex.get(lines.indexOf(line)).getCoordinate().y);
                    line.setFinish(new Point(e.getComponent().getX() + e.getX(),e.getComponent().getY() + e.getY()));
                }

            }


            currentVertex.setBounds(e.getComponent().getX() + e.getX(),e.getComponent().getY() + e.getY(),16, 16);
            currentVertex.setCoordX(e.getComponent().getX() + e.getX());
            currentVertex.setCoordY(e.getComponent().getY() + e.getY());
            tabPanel.repaint();



//            tabPanel.add(currentVertex);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
