package by.zverugo.bsuir.ppvis.grapheditor.logic.linebuilder;


import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.storages.GraphStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;


public class Line extends JPanel {

    private Point startDrawingPoint;
    private Point finishDrawingPoint;
    private String weight;
    private JLabel lineLabel;
    private GEToolBar toolBar;
    private JFrame frame;
    private  JComponent tabPanel;
    private Line thisLine;
    private GraphStorage graphStorage;
    private Point firstVertex;
    private Point secondVertex;

    public Line(Point A, Point B,GEToolBar toolBar,JFrame frame, JComponent tabPanel,
                GraphStorage graphStorage, Point firstVertex, Point secondVertex){
        this.toolBar = toolBar;
        this.frame = frame;
        this.tabPanel = tabPanel;
        this.graphStorage = graphStorage;
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        startDrawingPoint = A;
        finishDrawingPoint = B;
        weight = "";
        lineLabel = new JLabel(weight);
        setBackground(Color.BLUE);
        thisLine = this;
        addLinesListeners();
        setOpaque(false);
    }

    private void addLinesListeners (){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(toolBar.getDeleteVertexButton().isSelected()){
                    graphStorage.removeLine(tabPanel,thisLine);
                    tabPanel.repaint();
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        //g2.setStroke(new BasicStroke(1.0f, 0, 0, 1.0f, new float[]{10, 10}, 1.0f));
        Line2D line = new Line2D.Double(startDrawingPoint.x, startDrawingPoint.y, finishDrawingPoint.x, finishDrawingPoint.y);
        g2.draw(line);
//        g2.setClip(line);
//        setOpaque(false);
    }

    public Point getFirstVertex() {

        return firstVertex;
    }

    public Point getSecondVertex () {

        return secondVertex;
    }
}