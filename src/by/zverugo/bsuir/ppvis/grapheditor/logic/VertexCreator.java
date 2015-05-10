package by.zverugo.bsuir.ppvis.grapheditor.logic;

import by.zverugo.bsuir.ppvis.grapheditor.storages.GraphStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VertexCreator extends MouseAdapter {

    private GEToolBar toolBar;
    private JFrame frame;
    private Tab tabPanel;
    private LineStorage lineStorage;
    private GraphStorage graphStorage;

    public VertexCreator(JComponent tabPanel, JFrame frame, GraphStorage graphStorage) {
        this.frame = frame;
        this.tabPanel = (Tab) tabPanel;
        toolBar = this.tabPanel.getToolBar();
        lineStorage = this.tabPanel.getLineStorage();
        this.graphStorage = graphStorage;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (toolBar.getVertexButton().isSelected()) {
            int centerX = e.getX() + 9;
            int centerY = e.getY() + 9;
            Vertex vertex = new Vertex(frame, tabPanel, centerX, centerY, graphStorage);
            vertex.setBounds(e.getX(), e.getY(), 16, 16);
            tabPanel.add(vertex);
            vertex.validate();
            vertex.repaint();

        }
    }
}


