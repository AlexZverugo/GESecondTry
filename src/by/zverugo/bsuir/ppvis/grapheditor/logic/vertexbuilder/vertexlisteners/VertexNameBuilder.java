package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.vertexlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.view.dialogwindows.VertexNameAndLineWight;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;

public class VertexNameBuilder extends MouseAdapter {

    private GEToolBar toolBar;
    private JFrame frame;
    private String name;
    private JLabel vertexLabel;
    private int coordX;
    private int coordY;
    private JComponent tabPanel;
    private Font font;



    public VertexNameBuilder(GEToolBar toolBar, JFrame frame, String name,
                             JLabel vertexLabel, JComponent tabPanel, int x, int y) {
        this.toolBar = toolBar;
        this.frame = frame;
        this.name = name;
        this.vertexLabel = vertexLabel;
        this.tabPanel = tabPanel;
        coordX = x;
        coordY = y;
        font = new Font(name,Font.PLAIN,32);

    }

    public void mouseClicked(MouseEvent e) {
        if (!toolBar.getArcButton().isSelected() && !toolBar.getVertexButton()
                .isSelected() && !toolBar.getDeleteVertexButton()
                .isSelected()) {
            VertexNameAndLineWight vertexNameAndLineWightDialog = new VertexNameAndLineWight(frame, name);
            name = vertexNameAndLineWightDialog.createVertexName();
            vertexLabel.setText(name);
            vertexLabel.setBounds(coordX + 8, coordY + 8,
                        (int) font.getStringBounds(name, new FontRenderContext(null, true, true)).getWidth(),15);
            tabPanel.add(vertexLabel);
            tabPanel.repaint();
        }
    }
}
