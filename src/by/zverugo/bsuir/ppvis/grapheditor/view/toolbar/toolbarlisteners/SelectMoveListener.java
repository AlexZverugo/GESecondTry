package by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.toolbarlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alex on 08.05.2015.
 */
public class SelectMoveListener implements ActionListener {

    private GEToolBar toolBar;

    public SelectMoveListener(JToolBar toolBar) {
        this.toolBar = (GEToolBar) toolBar;
    }

    public void actionPerformed(ActionEvent e) {
        if (toolBar.getMoveVertexButton().isSelected()) {
            toolBar.getArcButton().setSelected(false);
            toolBar.getVertexButton().setSelected(false);
            toolBar.getDeleteVertexButton().setSelected(false);
        }
    }
}
