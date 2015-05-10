package by.zverugo.bsuir.ppvis.grapheditor.view.dialogwindows;

import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
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
        weight = "";
        lineLabel = new JLabel();
        font = new Font(weight,Font.PLAIN,32);
    }

    public void mouseClicked(MouseEvent e) {
        if (!toolBar.getArcButton().isSelected() && !toolBar.getVertexButton()
                .isSelected() && !toolBar.getDeleteVertexButton()
                .isSelected()) {

            LinkedList<Point> lineList = lineStorage.getLineList();
            boolean isEven = false;
            Point firstPoint = new Point();

            for(Point point : lineList){
                if(!isEven){
                    firstPoint = point;
                    isEven = true;
                } else {
                    int middleX = (firstPoint.x + point.x)/2;
                    int middleY = (firstPoint.y + point.y)/2;

                    if((e.getX() > middleX  - 5 && e.getX() < middleX + 5)  && (e.getY() > middleY - 5 &&  e.getY() < middleY + 5)) {
                        invokeOptionPane(e.getX(),e.getY());
                        break;
                    }

                    isEven = false;
                }
            }

        }
    }

    private void invokeOptionPane (int x, int y){
        VertexNameAndLineWeight vertexNameAndLineWeightDialog = new VertexNameAndLineWeight(frame, weight);
        weight = vertexNameAndLineWeightDialog.createVertexName();
        lineLabel.setText(weight);
        lineLabel.setBounds(x + 3, y + 3,
                (int) font.getStringBounds(weight, new FontRenderContext(null, true, true)).getWidth(), 15);
        tabPanel.add(lineLabel);
        tabPanel.repaint();
    }
}
