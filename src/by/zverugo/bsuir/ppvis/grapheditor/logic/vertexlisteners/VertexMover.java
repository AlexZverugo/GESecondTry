package by.zverugo.bsuir.ppvis.grapheditor.logic.vertexlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Alex on 08.05.2015.
 */
public class VertexMover  implements MouseMotionListener {

    private Tab tabPanel;
    private Vertex currentVertex;
    private GEToolBar toolBar;
    private boolean state;

    public VertexMover (JComponent tabPanel, JComponent currentVertex, GEToolBar toolBar) {
        this.tabPanel = (Tab) tabPanel;
        this.currentVertex = (Vertex) currentVertex;
        this.toolBar = toolBar;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(toolBar.getMoveVertexButton().isSelected()){
            tabPanel.remove(currentVertex);
            currentVertex.setBounds(e.getPoint().x,e.getPoint().y,  16, 16);
            tabPanel.add(currentVertex);
            tabPanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        if(toolBar.getMoveVertexButton().isSelected()) {
//            tabPanel.remove(currentVertex);
//            currentVertex.setBounds(e.getPoint().x,e.getPoint().y,  16, 16);
//            tabPanel.add(currentVertex);
//            tabPanel.repaint();
//        }

    }

//    @Override
//    public void mouseReleased(MouseEvent e) {
//        if(toolBar.getMoveVertexButton().isSelected()){
//            state = false;
//        }
//    }

    private void drawTempLine(int x1, int y1, int x2, int y2) {

        Graphics g = tabPanel.getTempImage().getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(new Color(255, 255, 255, 0));
        g2.setColor(Color.black);
        g2.clearRect(0, 0, 1500, 1500);
        g2.drawLine(x1, y1, x2, y2);
    }
}
