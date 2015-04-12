package by.zverugo.bsuir.ppvis.grapheditor.view.tabs;

import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.VertexCreator;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.VertexStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

/**
 * Created by Alex on 23.03.2015.
 */
public class Tab extends JPanel {

    private JFrame frame;
    private GEToolBar toolBar;
    private VertexStorage vertexStorage;
    private LineStorage lineStorage;
    private TabsContainer tabbedPane;

    public Tab( JFrame frame, GEToolBar toolBar,TabsContainer tabbedPane) {
        this.frame = frame;
        this.toolBar = toolBar;
        this.tabbedPane = tabbedPane;
        buildStorages();
        addTabListeners();
        //buildScrollBar();


//        setPreferredSize(new Dimension(1920,1080));
//
//
//        JScrollPane scrolling = new JScrollPane(this,
//                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        frame.add(scrolling);
    }

//    private void buildScrollBar() {
//        JScrollBar verticalScroll = new JScrollBar();
//        JScrollBar horizontalScroll = new JScrollBar(Adjustable.HORIZONTAL);
//        add(horizontalScroll, BorderLayout.SOUTH);
//        add(verticalScroll, BorderLayout.EAST);
//    }

    private void addTabListeners (){
        addMouseListener(new VertexCreator(this, toolBar, frame,vertexStorage,lineStorage));
    }

    private void buildStorages(){
        vertexStorage = new VertexStorage();
        lineStorage = new LineStorage();
    }

    public void drawTempLine(Point startPoint, Point finishPoint){
        Graphics2D g2 = (Graphics2D) getGraphics();
        Line2D line = new Line2D.Double(startPoint.x,startPoint.y,finishPoint.x,finishPoint.y);
        g2.draw(line);
        
    }

}
