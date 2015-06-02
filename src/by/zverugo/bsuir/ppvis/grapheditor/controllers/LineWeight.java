package by.zverugo.bsuir.ppvis.grapheditor.controllers;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Line;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.dialogwindows.VertexNameAndLineWeightCreator;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.util.LinkedList;

/**
 * Created by Alex on 27.03.2015.
 */
public class LineWeight extends MouseAdapter {

    private GEToolBar toolBar;
    private JFrame frame;
    private Tab tabPanel;
    private JLabel lineLabel;
    private String weight;
    private Font font;
    private LineStorage lineStorage;

    public LineWeight(Tab tabPanel){
        this.tabPanel = tabPanel;
        toolBar = tabPanel.getToolBar();
        frame = tabPanel.getFrame();
        lineStorage = tabPanel.getLineStorage();
        weight = " ";
        font = new Font(weight,Font.PLAIN,32);
    }

    public void mouseClicked(MouseEvent e) {
        if (!toolBar.getArcButton().isSelected() && !toolBar.getVertexButton()
                .isSelected() && !toolBar.getDeleteVertexButton()
                .isSelected()) {

            LinkedList<Line> lineList = lineStorage.getLineList();

            for(Line line : lineList){
                    int middleX = (line.getStartPoint().x + line.getFinishPoint().x)/2;
                    int middleY = (line.getStartPoint().y + line.getFinishPoint().y)/2;

                    if((e.getX() > middleX  - 5 && e.getX() < middleX + 5)  && (e.getY() > middleY - 5 &&  e.getY() < middleY + 5)) {
                        invokeOptionPane(line, e.getX(),e.getY());
                        break;
                    }
            }
        }
    }

    private void invokeOptionPane (Line line, int x, int y){
        lineLabel = new JLabel();
        VertexNameAndLineWeightCreator vertexNameAndLineWeightCreatorDialog = new VertexNameAndLineWeightCreator(frame,"");
        weight = vertexNameAndLineWeightCreatorDialog.createVertexName();
        line.setWeight(weight);
        lineLabel.setText(weight);
        line.setWeightLabel(lineLabel);
        lineLabel.setForeground(Color.RED);
        lineLabel.setBounds(x + 3, y + 3,
                (int) font.getStringBounds(weight, new FontRenderContext(null, true, true)).getWidth(), 15);
        tabPanel.add(lineLabel);
        tabPanel.repaint();
    }
}
