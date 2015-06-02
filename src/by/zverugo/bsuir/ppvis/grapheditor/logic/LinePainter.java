package by.zverugo.bsuir.ppvis.grapheditor.logic;

import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;

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
        drawArrow(x1, y1, x2, y2);
    }

    public void drawLoop(int x, int y) {
        Graphics g = tabPanel.getBufferedImage().getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(new Color(255, 255, 255, 0));
        g2.setColor(Color.black);
        g2.drawOval(x - 20,y - 20,20,20);
    }

    public void removeLoop(int x, int y) {
        Graphics g = tabPanel.getBufferedImage().getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(5));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(new Color(255, 255, 255, 0));
        g2.setColor(Color.white);
        g2.drawOval(x - 20,y - 20,20,20);
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
        removeArrow(x1, y1, x2, y2);
    }

    public void drawTempLine(int x1, int y1, int x2, int y2) {

        Graphics g = tabPanel.getTempImage().getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(new Color(255, 255, 255, 0));
        g2.setColor(Color.black);
        g2.clearRect(0, 0, 1840,940);
        g2.drawLine(x1, y1, x2, y2);
    }

    public void clearLastTempLine() {
        Graphics g = tabPanel.getTempImage().getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.setBackground(new Color(255, 255, 255, 0));
        g2.clearRect(0, 0, 1840,940);
    }

    private void drawArrow(int x1, int y1, int x2, int y2) {
        Graphics g = tabPanel.getBufferedImage().getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        double length = Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1),2));
        double finishRatio = 25/length;
        double startRatio = 8/length;


        int finishX = (int)((x2 + finishRatio*x1)/(1 + finishRatio));
        int finishY =(int)((y2 + finishRatio*y1)/(1 + finishRatio));

        int startX = (int)((x2 + startRatio*x1)/(1 + startRatio));
        int startY =(int)((y2 + startRatio*y1)/(1 + startRatio));

        double theta = 3.14/6;

        g2.setStroke(new BasicStroke(2));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(new Color(255, 255, 255, 0));
        g2.setColor(Color.black);

        g2.rotate(theta,startX,startY);
        g2.drawLine(startX, startY, finishX, finishY);

        theta = (300 *3.14)/180;
        g2.rotate(theta,startX,startY);

        g2.drawLine(startX, startY, finishX, finishY);
    }

    private void removeArrow(int x1, int y1, int x2, int y2) {
        Graphics g = tabPanel.getBufferedImage().getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        double length = Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1),2));
        double finishRatio = 25/length;
        double startRatio = 8/length;


        int finishX = (int)((x2 + finishRatio*x1)/(1 + finishRatio));
        int finishY =(int)((y2 + finishRatio*y1)/(1 + finishRatio));

        int startX = (int)((x2 + startRatio*x1)/(1 + startRatio));
        int startY =(int)((y2 + startRatio*y1)/(1 + startRatio));

        g2.setStroke(new BasicStroke(4));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(new Color(255, 255, 255, 0));
        g2.setColor(Color.white);

        double theta = 3.14/6;

        g2.rotate(theta,startX,startY);
        g2.drawLine(startX, startY, finishX, finishY);

        theta = (300 *3.14)/180;
        g2.rotate(theta,startX,startY);

        g2.drawLine(startX, startY, finishX, finishY);
    }
}
