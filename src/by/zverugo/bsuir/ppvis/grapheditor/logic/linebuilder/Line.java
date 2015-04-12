package by.zverugo.bsuir.ppvis.grapheditor.logic.linebuilder;


import by.zverugo.bsuir.ppvis.grapheditor.logic.linebuilder.linelistener.LineWeightSetter;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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




    public Line(Point A, Point B,GEToolBar toolBar,JFrame frame, JComponent tabPanel){
        this.toolBar = toolBar;
        this.frame = frame;
        this.tabPanel = tabPanel;
        startPoint = A;
        finishPoint = B;
        weight = "";
        lineLabel = new JLabel(weight);
        setLineName();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        //g2.setStroke(new BasicStroke(1.0f, 0, 0, 1.0f, new float[]{10, 10}, 1.0f));
        Line2D line = new Line2D.Double(startPoint.x,startPoint.y,finishPoint.x,finishPoint.y);

        g2.draw(line);
        setOpaque(false);
    }

    private void setLineName(){
        addMouseListener(new LineWeightSetter(weight,lineLabel,toolBar,frame,tabPanel,0,0));
    }
}
