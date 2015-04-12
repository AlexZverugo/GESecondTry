package by.zverugo.bsuir.ppvis.grapheditor.logic.linebuilder;

import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JFrame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LineCreator extends MouseAdapter {

    private GEToolBar toolBar;
    private JFrame frame;
    private LineStorage lineStorage;
    private Line line;

    public LineCreator(GEToolBar toolBar, JFrame frame) {
        this.toolBar = toolBar;
        this.frame = frame;
        this.lineStorage = lineStorage;
    }

    public void mouseClicked(MouseEvent e){
        if (toolBar.getArcButton().isSelected()){
            lineStorage.setVertexSelected(true);
            line.setBounds(0,0,0,0);
            //line = new Line(new Point(line.getX(),line.getY()),new Point(line.getWidth(),line.getHeight()), new Poin);
        }
    }


    public void mouseMoved(MouseEvent e) {
        if(lineStorage.isVertexSelected()){

        }
    }

}
