package by.zverugo.bsuir.ppvis.grapheditor.logic;

import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners.LineBuilder;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners.VertexNameBuilder;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners.VertexRemover;
import by.zverugo.bsuir.ppvis.grapheditor.storages.GraphStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.VertexStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private GraphStorage graphStorage;
    private int coordX;
    private int coordY;

    public Vertex(JFrame frame, JComponent tabPanel, int x, int y, GraphStorage graphStorage) {
        this.frame = frame;
        this.tabPanel = (Tab) tabPanel;
        toolBar = this.tabPanel.getToolBar();
        vertexStorage = this.tabPanel.getVertexStorage();
        this.graphStorage = graphStorage;
        coordX = x;
        coordY = y;
        name = "";
        vertexLabel = new JLabel(name);
        addVertexToContainer();
        callAndRemoveVertex();
        setOpaque(false);
    }

    private void addVertexToContainer() {
        vertexStorage.setVertex(this);
        graphStorage.setVertex(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D ellipse = new Ellipse2D.Double(0, 0, 15, 15);
        g2.draw(ellipse);
        g2.fill(ellipse);
    //g.getFontMetrics().stringWidth("fddf");
}


    private void callAndRemoveVertex() {
        addMouseListener(new VertexNameBuilder(name, vertexLabel, tabPanel, coordX, coordY));
        addMouseListener(new VertexRemover(this, tabPanel, vertexLabel,graphStorage));
        addMouseListener(new LineBuilder(this, tabPanel,graphStorage));
//        addMouseMotionListener(new VertexMover(tabPanel, this, toolBar));
    }

    public Point getCoordinate() {
        return new Point(coordX, coordY);
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
