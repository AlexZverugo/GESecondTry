package by.zverugo.bsuir.ppvis.grapheditor.view.tabs;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

/**
 * Created by Alex on 23.04.2015.
 */
public class TempDrawPanel extends JComponent {

    private int height;
    private int width;
    private  Line2D previousLine;
    private Line2D currentLine;

    public TempDrawPanel (int height, int width) {
        this.height = height;
        this.width = width;
        setBackground(Color.BLUE);
        setSize(width,height);
        previousLine = new Line2D.Double(0,0,0,0);
//        setOpaque(false);

    }

    public void drawTempLine(Point startPoint, Point finishPoint){
            Graphics2D g2 = (Graphics2D) getGraphics();
            currentLine = new Line2D.Double(startPoint.x,startPoint.y,finishPoint.x,finishPoint.y);
            g2.draw(currentLine);
    }

    public Line2D getFinalLine () {
        return currentLine;
    }

    public void setPreviousPoints(){
        previousLine = currentLine;
    }

    public void deleteTempLine() {
            Graphics2D g2 = (Graphics2D) getGraphics();
            g2.setColor(Color.WHITE);
            g2.draw(previousLine);
    }
}
