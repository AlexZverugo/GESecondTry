package by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.toolbarlisteners;

import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alex on 23.03.2015.
 */
public class SelectArcListener implements ActionListener {

    private GEToolBar toolBar;

    public SelectArcListener(JToolBar toolBar) {
        this.toolBar = (GEToolBar) toolBar;
    }

    public void actionPerformed(ActionEvent e) {
        if (toolBar.getArcButton().isSelected()) {
            toolBar.getVertexButton().setSelected(false);
            toolBar.getDeleteVertexButton().setSelected(false);
            toolBar.getMoveVertexButton().setSelected(false);
        }
    }
}
