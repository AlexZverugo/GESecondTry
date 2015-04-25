package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder;

import by.zverugo.bsuir.ppvis.grapheditor.storages.GraphStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VertexCreator extends MouseAdapter {

    private GEToolBar toolBar;
    private JFrame frame;
    private JComponent tabPanel;
    private LineStorage lineStorage;
    private GraphStorage graphStorage;

    public VertexCreator(JComponent tabPanel, GEToolBar toolBar, JFrame frame,
                         LineStorage lineStorage, GraphStorage graphStorage) {
        this.frame = frame;
        this.toolBar = toolBar;
        this.tabPanel = tabPanel;
        this.lineStorage = lineStorage;
        this.graphStorage = graphStorage;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (toolBar.getVertexButton().isSelected()) {
            Vertex vertex = new Vertex(frame, tabPanel, toolBar, lineStorage, e.getX() + 9, e.getY() + 9, graphStorage);
            vertex.setBounds(e.getX(), e.getY(), 16, 16);
            tabPanel.add(vertex);
            vertex.validate();
            vertex.repaint();

        }
    }
}


