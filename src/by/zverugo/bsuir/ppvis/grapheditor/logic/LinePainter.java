package by.zverugo.bsuir.ppvis.grapheditor.logic;

import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;

import javax.swing.JComponent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Created by Alex on 09.05.2015.
 */
public class LinePainter {

    private Tab tabPanel;

    public LinePainter (Tab tabPanel) {
        this.tabPanel = tabPanel;
    }

    public void drawLine (int x1, int y1, int x2, int y2){
        Graphics g = tabPanel.getBufferedImage().getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(new Color(255, 255, 255, 0));
        g2.setColor(Color.black);
        int middleX = (x1 + x2)/2;
        int middleY = (y1 + y2)/2;
        g2.drawOval(middleX - 2,middleY - 2,4,4);
        g2.drawLine(x1, y1, x2, y2);
    }

    public void removeLine(int x1, int y1, int x2, int y2) {
        Graphics2D g2 = (Graphics2D) tabPanel.getBufferedImage().getGraphics();

        g2.setStroke(new BasicStroke(5));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(new Color(255, 255, 255, 0));
        g2.setColor(Color.white);
        int middleX = (x1 + x2)/2;
        int middleY = (y1 + y2)/2;
        g2.drawOval(middleX - 2,middleY - 2,4,4);
        g2.drawLine(x1, y1, x2, y2);
    }

    public void drawTempLine(int x1, int y1, int x2, int y2) {

        Graphics g = tabPanel.getTempImage().getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(new Color(255, 255, 255, 0));
        g2.setColor(Color.black);
        g2.clearRect(0, 0, 1500, 1500);
        g2.drawLine(x1, y1, x2, y2);
    }

    public void clearLastTempLine() {
        Graphics g = tabPanel.getTempImage().getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.setBackground(new Color(255, 255, 255, 0));
        g2.clearRect(0, 0, 1500, 1500);
    }
}
