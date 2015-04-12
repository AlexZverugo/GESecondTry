package by.zverugo.bsuir.ppvis.grapheditor.logic.linebuilder.linelistener;

import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;

/**
 * Created by Alex on 27.03.2015.
 */
public class LineWeightSetter extends MouseAdapter {

    private String weight;
    private JLabel lineLabel;
    private GEToolBar toolBar;
    private JFrame frame;
    private JComponent tabPanel;
    private Font font;
    private int x;
    private int y;

    public LineWeightSetter(String weight, JLabel lineLabel,GEToolBar toolBar, JFrame frame,JComponent tabPanel,
                            int x, int y){
        this.weight = weight;
        this.lineLabel = lineLabel;
        this.toolBar = toolBar;
        this.frame = frame;
        this.x = x;
        this.y = y;
        font = new Font(weight,Font.PLAIN,32);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!toolBar.getArcButton().isSelected() && !toolBar.getVertexButton()
                .isSelected() && !toolBar.getDeleteVertexButton()
                .isSelected()){

            lineLabel.setBounds(x, y,
                    (int) font.getStringBounds(weight, new FontRenderContext(null, true, true)).getWidth(),15);

            tabPanel.add(lineLabel);
            tabPanel.repaint();

        }
    }
}
