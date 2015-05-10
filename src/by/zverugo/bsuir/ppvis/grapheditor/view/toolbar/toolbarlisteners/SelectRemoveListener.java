package by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.toolbarlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alex on 23.03.2015.
 */
public class SelectRemoveListener implements ActionListener {

    private GEToolBar toolBar;

    public SelectRemoveListener(JToolBar toolBar) {
        this.toolBar = (GEToolBar) toolBar;
    }

    public void actionPerformed(ActionEvent e) {
        if (toolBar.getDeleteVertexButton().isSelected()) {
            toolBar.getArcButton().setSelected(false);
            toolBar.getVertexButton().setSelected(false);
            toolBar.getMoveVertexButton().setSelected(false);
        }
    }
}
