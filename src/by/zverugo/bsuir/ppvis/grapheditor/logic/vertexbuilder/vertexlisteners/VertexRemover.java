package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.vertexlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.storages.VertexStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VertexRemover extends MouseAdapter {
    private GEToolBar toolBar;
    private JPanel vertexLink;
    private JComponent tabPanel;
    private JLabel vertexLabel;
    private VertexStorage vertexStorage;

    public VertexRemover(GEToolBar toolBar, JPanel vertexLink, JComponent tabPanel, JLabel vertexLabel, VertexStorage vertexStorage) {
        this.toolBar = toolBar;
        this.vertexLink = vertexLink;
        this.tabPanel = tabPanel;
        this.vertexLabel = vertexLabel;
        this.vertexStorage = vertexStorage;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (toolBar.getDeleteVertexButton().isSelected()) {
            tabPanel.remove(vertexLink);
            tabPanel.remove(vertexLabel);
            vertexStorage.removeVertex(vertexLink);
            tabPanel.repaint();
        }
    }
}
