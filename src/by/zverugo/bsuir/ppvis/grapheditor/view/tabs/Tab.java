package by.zverugo.bsuir.ppvis.grapheditor.view.tabs;

import by.zverugo.bsuir.ppvis.grapheditor.controllers.LineRemover;
import by.zverugo.bsuir.ppvis.grapheditor.logic.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.LinePainter;
import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.logic.VertexCreator;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.VertexStorage;
import by.zverugo.bsuir.ppvis.grapheditor.controllers.LineWeight;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.TabToolBar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by Alex on 23.03.2015.
 */
public class Tab extends JPanel {

    private JFrame frame;
    private GEToolBar toolBar;
    private TabToolBar tabToolBar;
    private LineStorage lineStorage;
    private VertexStorage vertexStorage;
    private TabsContainer tabbedPane;
    private boolean flag;
    private BufferedImage temp;
    private BufferedImage bufferedImage;
    private LinePainter linePainter;

    public Tab( JFrame frame, GEToolBar toolBar, TabsContainer tabbedPane) {
        this.frame = frame;
        this.toolBar = toolBar;
        this.tabbedPane = tabbedPane;
        flag = false;
        buildLinePainter();
        buildStorages();
        addTabListeners();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1840,940));
        temp = new BufferedImage(1840,940,BufferedImage.TYPE_INT_ARGB);
        bufferedImage = new BufferedImage(1840,940,BufferedImage.TYPE_INT_ARGB);
        buildTabToolBar();
    }


    private void buildLinePainter() {
        linePainter = new LinePainter(this);
    }

    public LinePainter getLinePainter() {

        return linePainter;
    }

    private void buildTabToolBar() {
        tabToolBar = new TabToolBar(this);
        tabToolBar.setBounds(0,0,35,120);
        add(tabToolBar);
    }

    private void addTabListeners() {
        addMouseListener(new VertexCreator(this, frame));
        addMouseListener(new LineRemover(this));
        addMouseListener(new LineWeight(this));

//        saveButton.addActionListener(new FileCreator());
    }

    private void buildStorages() {
        lineStorage = new LineStorage();
        vertexStorage = new VertexStorage();
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

    public TabsContainer getTabbedPane() {

        return tabbedPane;
    }

    public TabToolBar getTabToolBar() {

        return tabToolBar;
    }

    public void buildGraph() {
        LinkedList <Line> lineList = lineStorage.getLineList();
        LinkedList <Vertex> vertexList = vertexStorage.getVertexList();

        for (Line line : lineList) {
            Vertex startVertex = null;
            Vertex finishVertex = null;

            for (Vertex vertex : vertexList) {
                if (vertex.getCoordinate().x == line.getStartPoint().x
                        && vertex.getCoordinate().y == line.getStartPoint().y) {
                    startVertex = vertex;
                }
                if (vertex.getCoordinate().x == line.getFinishPoint().x
                        && vertex.getCoordinate().y == line.getFinishPoint().y) {
                    finishVertex = vertex;
                }
            }

            line.setVertexes(startVertex, finishVertex);
            if (line.getStartPoint().x == line.getFinishPoint().x
                    && line.getStartPoint().y == line.getFinishPoint().y) {
                linePainter.drawLoop(line.getStartPoint().x, line.getStartPoint().y);

            } else {
            linePainter.drawLine(line.getStartPoint().x, line.getStartPoint().y,
                    line.getFinishPoint().x, line.getFinishPoint().y);
            String weight = line.getWeight();
            if (weight != null && weight.equals(" ")) {
                JLabel weightLabel = line.getWeightLabel();
                weightLabel.setText(weight);
                Font font = new Font(weight, Font.PLAIN, 32);
                int middleX = (line.getStartPoint().x + line.getFinishPoint().x) / 2;
                int middleY = (line.getStartPoint().y + line.getFinishPoint().y) / 2;

                weightLabel.setBounds(middleX + 3, middleY + 3,
                        (int) font.getStringBounds(weight, new FontRenderContext(null, true, true)).getWidth(), 15);
                add(weightLabel);
            }
        }
        }


        for (Vertex vertex : vertexList) {
            vertex.setBounds(vertex.getCoordinate().x - 9, vertex.getCoordinate().y - 9 , 16, 16);
            add(vertex);
            vertex.repaint();
            String name = vertex.getName();
            if (name != null && !name.equals(" ")) {
                JLabel vertexLabel = vertex.getVertexLabel();
                vertexLabel.setText(name);
                Font font = new Font(name,Font.PLAIN,32);

                vertexLabel.setBounds(vertex.getCoordinate().x + 8, vertex.getCoordinate().y + 8,
                        (int) font.getStringBounds(name, new FontRenderContext(null, true, true)).getWidth(),15);
                add(vertexLabel);
            }
        }

        for (Line line : lineList){
            vertexStorage.setLineTwoVertex(line.getStartVertex(),line.getFinishVertex());
        }
    }
}


