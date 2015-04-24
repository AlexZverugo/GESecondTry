package by.zverugo.bsuir.ppvis.grapheditor.view.tabs;

import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.VertexCreator;
import by.zverugo.bsuir.ppvis.grapheditor.storages.GraphStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.VertexStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

/**
 * Created by Alex on 23.03.2015.
 */
public class Tab extends JPanel {

    private JFrame frame;
    private GEToolBar toolBar;
    private VertexStorage vertexStorage;
    private LineStorage lineStorage;
    private GraphStorage graphStorage;
    private TabsContainer tabbedPane;
    private Point lastStartPoint;
    private Point lastFinishPoint;
    private  Line2D previousLine;
    private Line2D currentLine;
    private TempDrawPanel tempDrawPanel;
    private boolean flag;
    private AbstractButton saveButton;

    public Tab( JFrame frame, GEToolBar toolBar,TabsContainer tabbedPane, AbstractButton saveButton) {
        this.frame = frame;
        this.toolBar = toolBar;
        this.tabbedPane = tabbedPane;
        this.saveButton = saveButton;
        buildStorages();
        addTabListeners();
        setBackground(Color.WHITE);
        lastStartPoint = new Point(0,0);
        lastFinishPoint = new Point(0,0);
//        previousLine = new Line2D.Double(0,0,0,0);
        tempDrawPanel = new TempDrawPanel(getHeight(),getWidth());
        add(tempDrawPanel, (Integer)0);
//        setComponentSelected(false);

    }


    private void addTabListeners (){
        addMouseListener(new VertexCreator(this, toolBar, frame,vertexStorage,lineStorage,graphStorage));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void buildStorages(){
        vertexStorage = new VertexStorage();
        lineStorage = new LineStorage();
        graphStorage = new GraphStorage();
    }

//    public void drawTempLine(Point startPoint, Point finishPoint){
//        if(!isComponentSelected()) {
//            Graphics2D g2 = (Graphics2D) getGraphics();
//            currentLine = new Line2D.Double(startPoint.x,startPoint.y,finishPoint.x,finishPoint.y);
//            g2.draw(currentLine);
//        }
//    }
//
//    public Line2D getFinalLine () {
//        return currentLine;
//    }
//
//    public void setPreviousPoints(){
//        previousLine = currentLine;
//    }
//
//    public void deleteTempLine() {
//        if(!isComponentSelected()) {
//            Graphics2D g2 = (Graphics2D) getGraphics();
//            g2.setColor(Color.WHITE);
//            g2.draw(previousLine);
//        }
//    }

    public boolean isComponentSelected() {
        return flag;
    }

//    public void setComponentSelected(boolean flag) {
//        this.flag = flag;
//
//    }

    public TempDrawPanel getTempDrawPanel () {
        return tempDrawPanel;
    }

}
