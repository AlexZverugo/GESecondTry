package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder;

import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.vertexlisteners.LineBuilder;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.vertexlisteners.VertexNameBuilder;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.vertexlisteners.VertexRemover;
import by.zverugo.bsuir.ppvis.grapheditor.storages.GraphStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class Vertex extends JPanel {

    private JFrame frame;
    private Tab tabPanel;
    private GEToolBar toolBar;
    private String name;
    private JLabel vertexLabel;
    private JPanel thisObjectLink;
    private VertexStorage vertexStorage;
    private LineStorage lineStorage;
    private GraphStorage graphStorage;
    private int coordX;
    private int coordY;

    public Vertex(JFrame frame, JComponent tabPanel, GEToolBar toolBar,
                  VertexStorage vertexStorage, LineStorage lineStorage, int x, int y, GraphStorage graphStorage) {
        this.frame = frame;
        this.tabPanel = (Tab) tabPanel;
        this.toolBar = toolBar;
        this.vertexStorage = vertexStorage;
        this.lineStorage = lineStorage;
        this.graphStorage = graphStorage;
        coordX = x;
        coordY = y;
        name = "";
        thisObjectLink = this;
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
//        g2.setClip(ellipse);
        //g2.clip(ellipse);
        g2.fill(ellipse);
        //setOpaque(false);
    //g.getFontMetrics().stringWidth("fddf");
}


    private void callAndRemoveVertex() {
        addMouseListener(new VertexNameBuilder(toolBar, frame, name, vertexLabel, tabPanel, coordX, coordY));
        addMouseListener(new VertexRemover(toolBar, this, tabPanel, vertexLabel, vertexStorage,graphStorage));
        addMouseListener(new LineBuilder(toolBar, lineStorage, this, tabPanel, frame,graphStorage));
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                System.out.println("IN");
//                tabPanel.setComponentSelected(true);
//            }
//
//            public void mouseExited (MouseEvent e) {
//                System.out.println("OUT");
//                tabPanel.setComponentSelected(false);
//            }
//        });
    }

    public Point getCoordinate() {

        return new Point(coordX, coordY);
    }


}
