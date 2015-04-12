package by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.toolbarlisteners;

import javax.swing.JToggleButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alex on 23.03.2015.
 */
public class SelectArcListener implements ActionListener {

    private JToggleButton vertexButton;
    private JToggleButton arcButton;
    private JToggleButton deleteVertexButton;

    public SelectArcListener(JToggleButton vertexButton, JToggleButton arcButton, JToggleButton deleteVertexButton) {
        this.vertexButton = vertexButton;
        this.arcButton = arcButton;
        this.deleteVertexButton = deleteVertexButton;
    }

    public void actionPerformed(ActionEvent e) {
        if (arcButton.isSelected()) {
            vertexButton.setSelected(false);
            deleteVertexButton.setSelected(false);
        }
    }
}
