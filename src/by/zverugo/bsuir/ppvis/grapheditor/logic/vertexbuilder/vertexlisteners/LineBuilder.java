package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.vertexlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.logic.linebuilder.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Alex on 24.03.2015.
 */
public class LineBuilder extends MouseAdapter {

    private GEToolBar toolBar;
    private LineStorage lineStorage;
    private JFrame frame;
    private Vertex currentVertex;
    private JComponent tabPanel;

    public LineBuilder(GEToolBar toolBar, LineStorage lineStorage, Vertex currentVertex, JComponent tabPanel, JFrame frame) {
        this.toolBar = toolBar;
        this.frame = frame;
        this.lineStorage = lineStorage;
        this.currentVertex = currentVertex;
        this.tabPanel = tabPanel;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (toolBar.getArcButton().isSelected()) {
            if (lineStorage.checkBuffer() == 0)
                lineStorage.setPoint(currentVertex.getCoordinate().x, currentVertex.getCoordinate().y);
            else {
                lineStorage.setPoint(currentVertex.getCoordinate().x, currentVertex.getCoordinate().y);
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



        Line line = new Line(new Point(0, 0), new Point(lineStorage.getSecondPoint().x
                - lineStorage.getFirstPoint().x, lineStorage.getSecondPoint().y
                - lineStorage.getFirstPoint().y), toolBar, frame, tabPanel);

        //line.setBackground(Color.blue);

        line.setBounds(lineStorage.getFirstPoint().x, lineStorage.getFirstPoint().y,
                lineStorage.getSecondPoint().x - lineStorage.getFirstPoint().x,
                lineStorage.getSecondPoint().y - lineStorage.getFirstPoint().y);

        return line;
    }

    private Line paintLineSecondCase() {
        Line line = new Line(new Point(0, lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y),
                new Point(lineStorage.getSecondPoint().x - lineStorage.getFirstPoint().x, 0), toolBar, frame, tabPanel);

//        line.setBackground(Color.blue);

        line.setBounds(lineStorage.getFirstPoint().x, lineStorage.getFirstPoint().y -
                        (lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y),
                lineStorage.getSecondPoint().x - lineStorage.getFirstPoint().x,
                lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y);

        return line;
    }

    private Line paintLineThirdCase() {
        Line line = new Line(new Point(0, lineStorage.getSecondPoint().y
                - lineStorage.getFirstPoint().y), new Point(lineStorage.getFirstPoint().x
                - lineStorage.getSecondPoint().x, 0), toolBar, frame, tabPanel);

        //line.setBackground(Color.blue);


        line.setBounds(lineStorage.getSecondPoint().x, lineStorage.getSecondPoint().y -
                        (lineStorage.getSecondPoint().y - lineStorage.getFirstPoint().y),
                lineStorage.getFirstPoint().x - lineStorage.getSecondPoint().x,
                lineStorage.getSecondPoint().y - lineStorage.getFirstPoint().y);

        return line;
    }

    private Line paintLineFoursCase() {

        Line line = new Line(new Point(0, 0), new Point(lineStorage.getFirstPoint().x
                - lineStorage.getSecondPoint().x, lineStorage.getFirstPoint().y
                - lineStorage.getSecondPoint().y), toolBar, frame, tabPanel);

      //  line.setBackground(Color.blue);

        line.setBounds(lineStorage.getSecondPoint().x, lineStorage.getSecondPoint().y,
                lineStorage.getFirstPoint().x - lineStorage.getSecondPoint().x,
                lineStorage.getFirstPoint().y - lineStorage.getSecondPoint().y);

        return line;
    }

}
