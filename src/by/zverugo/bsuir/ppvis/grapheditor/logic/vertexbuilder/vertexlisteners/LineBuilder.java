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
                tabPanel.addMouseMotionListener(new MouseAdapter() {
                    public void mouseMoved(MouseEvent e) {
                        if (lineStorage.isVertexSelected()) {
                            tabPanel.deleteTempLine();
                            tabPanel.drawTempLine(currentVertex.getCoordinate(), new Point(e.getX(), e.getY()));
                            tabPanel.setPreviousPoints();
                            tabPanel.repaint();

                        }
                        if (!lineStorage.isVertexSelected() && lineStorage.checkBuffer()) {

                        }
                    }

                });
            } else
            if (lineStorage.getCurrentIndx() == 1) {
                lineStorage.setPoint(currentVertex.getCoordinate());
                lineStorage.setVertexSelected(false);
            }

        }
    }


}
