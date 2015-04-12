package by.zverugo.bsuir.ppvis.grapheditor.view.toolbar;

import by.zverugo.bsuir.ppvis.grapheditor.util.StaticResource;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.toolbarlisteners.SelectArcListener;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.toolbarlisteners.SelectRemoveListener;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.toolbarlisteners.SelectVertexListener;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

/**
 * Created by Alex on 23.03.2015.
 */
public class GEToolBar extends JToolBar {

    private JToggleButton vertexButton;
    private JToggleButton arcButton;
    private JToggleButton deleteVertexButton;

    public GEToolBar() {
        vertexButton = buildVertexButton();
        arcButton = buildArcButton();
        deleteVertexButton = buildDeleteVertexButton();
        setButtonsSelected();
        toolBarOptionsAndItems();
    }

    private JToggleButton buildVertexButton(){
        JToggleButton vertexButton = new JToggleButton(new ImageIcon(StaticResource.VERTEX_IMAGE.path()));

        return vertexButton;
    }

    private JToggleButton buildArcButton () {
        JToggleButton  arcButton = new JToggleButton(new ImageIcon(StaticResource.ARC_IMAGE.path()));

        return arcButton;
    }

    private JToggleButton buildDeleteVertexButton() {
        JToggleButton deleteVertexButton = new JToggleButton(new ImageIcon(StaticResource.DELETE_VERTEX_IMAGE.path()));

        return deleteVertexButton;
    }

    private void setButtonsSelected() {
        vertexButton.addActionListener(new SelectVertexListener(vertexButton, arcButton, deleteVertexButton));
        arcButton.addActionListener(new SelectArcListener(vertexButton, arcButton, deleteVertexButton));
        deleteVertexButton.addActionListener(new SelectRemoveListener(vertexButton, arcButton, deleteVertexButton));

    }

    public JToggleButton getVertexButton() {

        return vertexButton;
    }

    public JToggleButton getArcButton() {

        return arcButton;
    }

    public JToggleButton getDeleteVertexButton() {

        return deleteVertexButton;
    }


    private void toolBarOptionsAndItems() {
        setOrientation(SwingConstants.VERTICAL);
        setFloatable(false);
        add(vertexButton);
        add(arcButton);
        add(deleteVertexButton);
    }
}
