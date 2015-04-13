package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.vertexlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.logic.linebuilder.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Alex on 24.03.2015.
 */
public class LineBuilder extends MouseAdapter {

    private GEToolBar toolBar;
    private LineStorage lineStorage;
    private JFrame frame;
    private Vertex currentVertex;
    private Tab tabPanel;

    public LineBuilder(GEToolBar toolBar, LineStorage lineStorage, Vertex currentVertex, JComponent tabPanel, JFrame frame) {
        this.toolBar = toolBar;
        this.frame = frame;
        this.lineStorage = lineStorage;
        this.currentVertex = currentVertex;
        this.tabPanel = (Tab)tabPanel;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (toolBar.getArcButton().isSelected()) {
            if (lineStorage.getCurrentIndx() == 0) {
                lineStorage.setPoint(currentVertex.getCoordinate());
                lineStorage.setVertexSelected(true);
                drawTempLine();

            } else
            if (lineStorage.getCurrentIndx() == 1) {
                lineStorage.setPoint(currentVertex.getCoordinate());
                lineStorage.setVertexSelected(false);
                tabPanel.repaint();

                tabPanel.getFinalLine();

                Line line = createAndSetLineComponentBounds();
                lineStorage.setLine(line);
                if (line == null) {
                    lineStorage.reset();
                    return;
                }
                tabPanel.add(line);
                tabPanel.validate();
                tabPanel.repaint();
                lineStorage.reset();
            }
        }
    }

    private void drawTempLine () {
        tabPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                if (lineStorage.isVertexSelected()) {
                    tabPanel.deleteTempLine();
                    tabPanel.drawTempLine(currentVertex.getCoordinate(), new Point(e.getX(), e.getY()));
                    tabPanel.setPreviousPoints();
                }
                if (!lineStorage.isVertexSelected() && lineStorage.checkBuffer()) {
                    tabPanel.removeMouseMotionListener(this);
                }
                if (!toolBar.getArcButton().isSelected()) {
                    tabPanel.removeMouseMotionListener(this);
                    tabPanel.repaint();
                    lineStorage.reset();
                }
            }
        });
    }



    private Line createAndSetLineComponentBounds() {
        if (lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y < 0
                && lineStorage.getFirstPoint().x - lineStorage.getSecondPoint().x < 0) {
            return paintLineFirstCase();
        } else if (lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y > 0
                && lineStorage.getFirstPoint().x - lineStorage.getSecondPoint().x < 0) {
            return paintLineSecondCase();
        } else if (lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y < 0
                && lineStorage.getFirstPoint().x - lineStorage.getSecondPoint().x > 0) {
            return paintLineThirdCase();
        } else if (lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y > 0
                && lineStorage.getFirstPoint().x - lineStorage.getSecondPoint().x > 0) {
            return paintLineFoursCase();
        }
        return null;
    }

    private Line paintLineFirstCase() {

        Point lastPoint = new Point(lineStorage.getSecondPoint().x
                - lineStorage.getFirstPoint().x - 10, lineStorage.getSecondPoint().y
                - lineStorage.getFirstPoint().y - 10);

        Polygon triangle = new Polygon();
        triangle.addPoint(lastPoint.x - 8, lastPoint.y - 8);
        triangle.addPoint(lastPoint.x - 8, lastPoint.y - 18);
        triangle.addPoint(lastPoint.x - 18, lastPoint.y - 8);

        Line line = new Line(new Point(10,10), lastPoint, toolBar, frame, tabPanel, triangle);


        line.setBounds(lineStorage.getFirstPoint().x, lineStorage.getFirstPoint().y,
                lineStorage.getSecondPoint().x - lineStorage.getFirstPoint().x,
                lineStorage.getSecondPoint().y - lineStorage.getFirstPoint().y);

        return line;
    }

    private Line paintLineSecondCase() {
        Polygon triangle = new Polygon();

        Line line = new Line(new Point(0, lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y),
                new Point(lineStorage.getSecondPoint().x - lineStorage.getFirstPoint().x, 0), toolBar, frame, tabPanel,
                triangle);

        line.setBounds(lineStorage.getFirstPoint().x, lineStorage.getFirstPoint().y -
                        (lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y),
                lineStorage.getSecondPoint().x - lineStorage.getFirstPoint().x,
                lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y);

        return line;
    }

    private Line paintLineThirdCase() {
        Polygon triangle = new Polygon();

        Line line = new Line(new Point(0, lineStorage.getSecondPoint().y
                - lineStorage.getFirstPoint().y), new Point(lineStorage.getFirstPoint().x
                - lineStorage.getSecondPoint().x, 0), toolBar, frame, tabPanel,triangle);

        line.setBounds(lineStorage.getSecondPoint().x, lineStorage.getSecondPoint().y -
                        (lineStorage.getSecondPoint().y - lineStorage.getFirstPoint().y),
                lineStorage.getFirstPoint().x - lineStorage.getSecondPoint().x,
                lineStorage.getSecondPoint().y - lineStorage.getFirstPoint().y);

        return line;
    }

    private Line paintLineFoursCase() {

        Polygon triangle = new Polygon();

        Line line = new Line(new Point(0, 0), new Point(lineStorage.getFirstPoint().x
                - lineStorage.getSecondPoint().x, lineStorage.getFirstPoint().y
                - lineStorage.getSecondPoint().y), toolBar, frame, tabPanel,triangle);

        line.setBounds(lineStorage.getSecondPoint().x, lineStorage.getSecondPoint().y,
                lineStorage.getFirstPoint().x - lineStorage.getSecondPoint().x,
                lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y);

        return line;
    }

}
