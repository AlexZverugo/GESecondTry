package by.zverugo.bsuir.ppvis.grapheditor.logic.linebuilder;


import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.Vertex;
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
import java.awt.Polygon;
import java.awt.geom.Line2D;


public class Line extends JPanel {

    private Point startPoint;
    private Point finishPoint;
    private String weight;
    private JLabel lineLabel;
    private GEToolBar toolBar;
    private JFrame frame;
    private  JComponent tabPanel;
    private Vertex currentVertex;
    private Polygon triangle;

    public Line(Point A, Point B,GEToolBar toolBar,JFrame frame, JComponent tabPanel,Polygon triangle){
        this.toolBar = toolBar;
        this.frame = frame;
        this.tabPanel = tabPanel;
        this.triangle = triangle;
        startPoint = A;
        finishPoint = B;
        weight = "";
        lineLabel = new JLabel(weight);
        setBackground(Color.WHITE);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        //g2.setStroke(new BasicStroke(1.0f, 0, 0, 1.0f, new float[]{10, 10}, 1.0f));
        Line2D line = new Line2D.Double(getX(),getY(),getX() + getWidth(),getY() + getHeight());
        g2.draw(line);
        g2.drawPolygon(triangle);
        g2.fill(triangle);
        setOpaque(false);
    }
}