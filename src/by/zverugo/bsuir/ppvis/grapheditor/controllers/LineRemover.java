package by.zverugo.bsuir.ppvis.grapheditor.controllers;

import by.zverugo.bsuir.ppvis.grapheditor.logic.LinePainter;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 * Created by Alex on 08.05.2015.
 */
public class LineRemover extends MouseAdapter{

    private Tab tabPanel;
    private LineStorage lineStorage;
    private GEToolBar toolBar;
    private LinePainter linePainter;

    public  LineRemover (JComponent tabPanel) {
        this.tabPanel = (Tab)tabPanel;
        lineStorage = this.tabPanel.getLineStorage();
        toolBar = this.tabPanel.getToolBar();
        linePainter = this.tabPanel.getLinePainter();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(toolBar.getDeleteVertexButton().isSelected()){
            LinkedList<Point> lineList = lineStorage.getLineList();
            boolean isEven = false;
            Point firstPoint = new Point();
            Point point;

            for(int lineIndex = 0; lineIndex < lineList.size(); lineIndex++){
                point = lineList.get(lineIndex);
                if(!isEven){
                    firstPoint = point;
                    isEven = true;
                } else {
                    int middleX = (firstPoint.x + point.x)/2;
                    int middleY = (firstPoint.y + point.y)/2;

                        if((e.getX() > middleX  - 5 && e.getX() < middleX + 5)  && (e.getY() > middleY - 5 &&  e.getY() < middleY + 5)) {
                            linePainter.removeLine(firstPoint.x, firstPoint.y, point.x, point.y);
                            lineList.remove(lineIndex);
                            lineList.remove(lineIndex - 1);
                            tabPanel.repaint();
                            break;
                        }

                    isEven = false;
                }
            }

            isEven = false;

            for(Point tempPoint : lineList){
                if(!isEven){
                    firstPoint = tempPoint;
                    isEven = true;
                } else {
                    linePainter.drawLine(firstPoint.x, firstPoint.y, tempPoint.x, tempPoint.y);
                    isEven = false;
                }

            }
        }
    }

}
