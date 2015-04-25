package by.zverugo.bsuir.ppvis.grapheditor.view.tabs;

import by.zverugo.bsuir.ppvis.grapheditor.util.GraphEditorKeys;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 25.04.2015.
 */
public class FileOpener  implements ActionListener{

    private JFrame frame;
    private TabsContainer tabbedPane;
    private GEToolBar toolBar;
    private Tab tab;
    private JMenuItem saveButton;


    public FileOpener (JFrame frame, TabsContainer tabbedPane, GEToolBar toolBar,JMenuItem saveButton) {
        this.frame = frame;
        this.tabbedPane = tabbedPane;
        this.toolBar = toolBar;
        this.saveButton = saveButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       buildTab();

    }

    private void buildTab() {
        tab = new Tab(frame,toolBar,tabbedPane,saveButton);
        tab.setLayout(null);
        tabbedPane.addTab(GraphEditorKeys.NEW_TAB_NAME, tab);
        tabbedPane.setSelectedComponent(tab);
        frame.add(tabbedPane);
        frame.validate();
        frame.repaint();
    }

    private void openFile () {
        String fileName = "temp.txt";

    }
}
