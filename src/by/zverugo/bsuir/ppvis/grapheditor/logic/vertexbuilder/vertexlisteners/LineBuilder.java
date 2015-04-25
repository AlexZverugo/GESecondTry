package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.vertexlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.logic.linebuilder.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.storages.GraphStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by Alex on 24.03.2015.
 */
public class LineBuilder extends MouseAdapter {

    private GEToolBar toolBar;
    private LineStorage lineStorage;
    private GraphStorage graphStorage;
    private JFrame frame;
    private Vertex currentVertex;
    private Tab tabPanel;

    public LineBuilder(GEToolBar toolBar, LineStorage lineStorage, Vertex currentVertex,
                       JComponent tabPanel, JFrame frame, GraphStorage graphStorage) {
        this.toolBar = toolBar;
        this.graphStorage = graphStorage;
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
                graphStorage.setTempVertex(currentVertex);
                drawTempLine();

            } else
            if (lineStorage.getCurrentIndx() == 1) {
                lineStorage.setPoint(currentVertex.getCoordinate());
                lineStorage.setVertexSelected(false);
                tabPanel.repaint();

                tabPanel.getTempDrawPanel().getFinalLine();

                Line line = createAndSetLineComponentBounds();
                graphStorage.setLine(currentVertex,line);
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
                    tabPanel.getTempDrawPanel().deleteTempLine();
                    tabPanel.getTempDrawPanel().drawTempLine(currentVertex.getCoordinate(), new Point(e.getX(), e.getY()));
                    tabPanel.getTempDrawPanel().setPreviousPoints();
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
        Point startPoint = new Point(0,0);
        Point lastPoint = new Point(lineStorage.getSecondPoint().x
                - lineStorage.getFirstPoint().x, lineStorage.getSecondPoint().y
                - lineStorage.getFirstPoint().y);



        Line line = new Line(startPoint, lastPoint, toolBar, frame, tabPanel,graphStorage,
                lineStorage.getFirstPoint(), lineStorage.getSecondPoint());

        line.setBounds(lineStorage.getFirstPoint().x, lineStorage.getFirstPoint().y,
                lineStorage.getSecondPoint().x - lineStorage.getFirstPoint().x,
                lineStorage.getSecondPoint().y - lineStorage.getFirstPoint().y);

        return line;
    }

    private Line paintLineSecondCase() {
        Point startPoint = new Point(0, lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y);
        Point lastPoint = new Point(lineStorage.getSecondPoint().x - lineStorage.getFirstPoint().x, 0);

        Line line = new Line(startPoint,lastPoint, toolBar, frame, tabPanel,graphStorage,
                lineStorage.getFirstPoint(), lineStorage.getSecondPoint());

        line.setBounds(lineStorage.getFirstPoint().x, lineStorage.getFirstPoint().y -
                        (lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y),
                lineStorage.getSecondPoint().x - lineStorage.getFirstPoint().x,
                lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y);

        return line;
    }

    private Line paintLineThirdCase() {
        Point startPoint = new Point(0, lineStorage.getSecondPoint().y - lineStorage.getFirstPoint().y);
        Point lastPoint = new Point(lineStorage.getFirstPoint().x - lineStorage.getSecondPoint().x, 0);

        Line line = new Line(startPoint, lastPoint, toolBar, frame, tabPanel,graphStorage,
                lineStorage.getFirstPoint(), lineStorage.getSecondPoint());

        line.setBounds(lineStorage.getSecondPoint().x, lineStorage.getSecondPoint().y -
                        (lineStorage.getSecondPoint().y - lineStorage.getFirstPoint().y),
                lineStorage.getFirstPoint().x - lineStorage.getSecondPoint().x,
                lineStorage.getSecondPoint().y - lineStorage.getFirstPoint().y);

        return line;
    }

    private Line paintLineFoursCase() {
        Point startPoint = new Point(0, 0);
        Point lastPoint = new Point(lineStorage.getFirstPoint().x
                - lineStorage.getSecondPoint().x, lineStorage.getFirstPoint().y
                - lineStorage.getSecondPoint().y);


        Line line = new Line(startPoint, lastPoint, toolBar, frame, tabPanel,graphStorage,
                lineStorage.getFirstPoint(), lineStorage.getSecondPoint());

        line.setBounds(lineStorage.getSecondPoint().x, lineStorage.getSecondPoint().y,
                lineStorage.getFirstPoint().x - lineStorage.getSecondPoint().x,
                lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y);

        return line;
    }

}
