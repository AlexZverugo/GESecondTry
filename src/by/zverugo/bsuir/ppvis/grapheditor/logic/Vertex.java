package by.zverugo.bsuir.ppvis.grapheditor.logic;

import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners.LineBuilder;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners.VertexMover;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners.VertexNameBuilder;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners.VertexRemover;
import by.zverugo.bsuir.ppvis.grapheditor.storages.VertexStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Vertex extends JPanel {

    private JFrame frame;
    private Tab tabPanel;
    private GEToolBar toolBar;
    private String name;
    private JLabel vertexLabel;
    private VertexStorage vertexStorage;
    private Color vertexColor;
    private String color;
    JLabel colorLabel;
    private boolean colorMarker;
    private int coordX;
    private int coordY;

    public Vertex(JFrame frame, JComponent tabPanel, int x, int y) {
        this.frame = frame;
        this.tabPanel = (Tab) tabPanel;
        toolBar = this.tabPanel.getToolBar();
        vertexStorage = this.tabPanel.getVertexStorage();
        coordX = x;
        coordY = y;
        name = " ";
        color = "";
        colorMarker = false;
        vertexLabel = new JLabel(name);
        colorLabel = new JLabel(color);
        colorLabel.setBounds(coordX - 25,coordY - 25,20,14);
        this.tabPanel.add(colorLabel);
        vertexColor = Color.gray;
        addVertexToContainer();
        callAndRemoveVertex();
        setOpaque(false);
    }

    private void addVertexToContainer() {
        vertexStorage.setVertex(this);
        vertexStorage.setLineToGraph();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D ellipse = new Ellipse2D.Double(0, 0, 15, 15);
        g2.setColor(vertexColor);
        g2.draw(ellipse);
        g2.fill(ellipse);
    //g.getFontMetrics().stringWidth("fddf");
}


    private void callAndRemoveVertex() {
        addMouseListener(new VertexNameBuilder(this, name, vertexLabel, tabPanel, coordX, coordY));
        addMouseListener(new VertexRemover(this, tabPanel, vertexLabel));
        addMouseListener(new LineBuilder(this, tabPanel));
//        addMouseMotionListener(new VertexMover(tabPanel, this));
    }

    public Point getCoordinate() {
        return new Point(coordX, coordY);
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public JLabel getVertexLabel() {

        return vertexLabel;
    }

    public boolean isColorMarker() {

        return colorMarker;
    }

    public String getColor() {

        return color;
    }

    public void setErrorVertexColor(Color color) {
        vertexColor = color;
        repaint();
    }

    public void setVertexColor(String color) {
        this.color = color;
        colorMarker = true;
        colorLabel.setText(color);
        colorLabel.setForeground(Color.MAGENTA);
        tabPanel.repaint();
    }

    public void removeVertexColor() {
        colorMarker = false;
        color = "";
        colorLabel.setText("");
    }
}
