package by.zverugo.bsuir.ppvis.grapheditor.view.tabs;

import by.zverugo.bsuir.ppvis.grapheditor.logic.linebuilder.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.VertexCreator;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.VertexStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.CompositeContext;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

/**
 * Created by Alex on 23.03.2015.
 */
public class Tab extends JPanel {

    private JFrame frame;
    private GEToolBar toolBar;
    private VertexStorage vertexStorage;
    private LineStorage lineStorage;
    private TabsContainer tabbedPane;
    private Point lastStartPoint;
    private Point lastFinishPoint;
    private  Line2D previousLine;
    private Line2D currentLine;

    public Tab( JFrame frame, GEToolBar toolBar,TabsContainer tabbedPane) {
        this.frame = frame;
        this.toolBar = toolBar;
        this.tabbedPane = tabbedPane;
        buildStorages();
        addTabListeners();
        setBackground(Color.WHITE);
        lastStartPoint = new Point(0,0);
        lastFinishPoint = new Point(0,0);
        previousLine = new Line2D.Double(0,0,0,0);

    }


    private void addTabListeners (){
        addMouseListener(new VertexCreator(this, toolBar, frame,vertexStorage,lineStorage));
    }

    private void buildStorages(){
        vertexStorage = new VertexStorage();
        lineStorage = new LineStorage();
    }

    public void drawTempLine(Point startPoint, Point finishPoint){
        Graphics2D g2 = (Graphics2D) getGraphics();
        currentLine = new Line2D.Double(startPoint.x,startPoint.y,finishPoint.x,finishPoint.y);
        g2.draw(currentLine);
    }

    public Line2D getFinalLine () {
        return currentLine;
    }

    public void setPreviousPoints(){
        previousLine = currentLine;
    }

    public void deleteTempLine() {
        Graphics2D g2 = (Graphics2D) getGraphics();
        g2.setColor(Color.WHITE);
        g2.draw(previousLine);
    }

}
