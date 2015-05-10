package by.zverugo.bsuir.ppvis.grapheditor.view.tabs;

import by.zverugo.bsuir.ppvis.grapheditor.controllers.LineRemover;
import by.zverugo.bsuir.ppvis.grapheditor.logic.LinePainter;
import by.zverugo.bsuir.ppvis.grapheditor.logic.VertexCreator;
import by.zverugo.bsuir.ppvis.grapheditor.storages.GraphStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.VertexStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.dialogwindows.LineWeight;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Created by Alex on 23.03.2015.
 */
public class Tab extends JPanel {

    private JFrame frame;
    private GEToolBar toolBar;
    private LineStorage lineStorage;
    private GraphStorage graphStorage;
    private VertexStorage vertexStorage;
    private TabsContainer tabbedPane;
    private boolean flag;
    private JMenuItem saveButton;
    private BufferedImage temp;
    private BufferedImage bufferedImage;
    private LinePainter linePainter;

    public Tab( JFrame frame, GEToolBar toolBar,TabsContainer tabbedPane,JMenuItem saveButton) {
        this.frame = frame;
        this.toolBar = toolBar;
        this.tabbedPane = tabbedPane;
        this.saveButton = saveButton;
        flag = false;
        buildLinePainter();
        buildStorages();
        addTabListeners();
        setBackground(Color.WHITE);
        temp = new BufferedImage(1500,1500,BufferedImage.TYPE_INT_ARGB);
        bufferedImage = new BufferedImage(1500,1500,BufferedImage.TYPE_INT_ARGB);
    }


    private void buildLinePainter() {
        linePainter = new LinePainter(this);
    }

    public LinePainter getLinePainter() {

        return linePainter;
    }

    private void addTabListeners (){
        addMouseListener(new VertexCreator(this, frame,graphStorage));
        addMouseListener(new LineRemover(this));
        addMouseListener(new LineWeight(this));
        saveButton.addActionListener(new FileCreator(graphStorage));
    }

    private void buildStorages(){
        lineStorage = new LineStorage();
        vertexStorage = new VertexStorage();
        graphStorage = new GraphStorage();
    }

    public JMenuItem getSaveButton () {

        return saveButton;
    }

    public GraphStorage getGraphStorage () {

        return graphStorage;
    }


    public BufferedImage getTempImage() {

        return temp;
    }

    public  BufferedImage getBufferedImage() {

        return  bufferedImage;
    }

    public void setState(boolean flag) {
        this.flag = flag;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(bufferedImage, null,null);
             g2.drawImage(temp, null,null);
    }


    public LineStorage getLineStorage () {

        return lineStorage;
    }

    public GEToolBar getToolBar() {

        return toolBar;
    }

    public JFrame getFrame() {

        return frame;
    }

    public VertexStorage getVertexStorage() {

        return vertexStorage;
    }
}


