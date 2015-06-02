package by.zverugo.bsuir.ppvis.grapheditor.controllers;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.LinePainter;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
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
            LinkedList<Line> lineList = lineStorage.getLineList();

            for(Line line : lineList){
                int middleX = (line.getStartPoint().x + line.getFinishPoint().x)/2;
                int middleY = (line.getStartPoint().y + line.getFinishPoint().y)/2;

                    if((e.getX() > middleX  - 5 && e.getX() < middleX + 5)  && (e.getY() > middleY - 5 &&  e.getY() < middleY + 5)) {
                        linePainter.removeLine(line.getStartPoint().x, line.getStartPoint().y,
                                line.getFinishPoint().x, line.getFinishPoint().y);
                        lineList.remove(line);
                        tabPanel.getVertexStorage().removeLineFromGraph(line.getStartVertex(),line.getFinishVertex());
                        tabPanel.remove(line.getWeightLabel());
                        tabPanel.repaint();
                        break;
                    }
                }


            for(Line line : lineList){
                linePainter.drawLine(line.getStartPoint().x, line.getStartPoint().y,
                            line.getFinishPoint().x, line.getFinishPoint().y);
            }
        }
    }

}
