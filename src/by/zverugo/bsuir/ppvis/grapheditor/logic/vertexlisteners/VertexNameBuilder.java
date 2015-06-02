package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.view.dialogwindows.VertexNameAndLineWeightCreator;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;

public class VertexNameBuilder extends MouseAdapter {

    private GEToolBar toolBar;
    private JFrame frame;
    private String name;
    private JLabel vertexLabel;
    private Vertex currentVertex;
    private int coordX;
    private int coordY;
    private Tab tabPanel;
    private Font font;



    public VertexNameBuilder(Vertex currentVertex, String name, JLabel vertexLabel, JComponent tabPanel, int x, int y) {
        this.tabPanel = (Tab)tabPanel;
        toolBar = this.tabPanel.getToolBar();
        frame = this.tabPanel.getFrame();
        this.currentVertex = currentVertex;
        this.name = name;
        this.vertexLabel = vertexLabel;
        coordX = x;
        coordY = y;
        font = new Font(name,Font.PLAIN,32);

    }

    public void mouseClicked(MouseEvent e) {
        if (!toolBar.getArcButton().isSelected() && !toolBar.getVertexButton()
                .isSelected() && !toolBar.getDeleteVertexButton()
                .isSelected()) {
            VertexNameAndLineWeightCreator vertexNameAndLineWeightCreatorDialog = new VertexNameAndLineWeightCreator(frame, name);
            name = vertexNameAndLineWeightCreatorDialog.createVertexName();
            vertexLabel.setText(name);
            currentVertex.setName(name);
            vertexLabel.setBounds(coordX + 8, coordY + 8,
                        (int) font.getStringBounds(name, new FontRenderContext(null, true, true)).getWidth(),15);
            tabPanel.add(vertexLabel);
            tabPanel.repaint();
        }
    }
}
