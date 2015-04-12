package by.zverugo.bsuir.ppvis.grapheditor.controllers;

import by.zverugo.bsuir.ppvis.grapheditor.util.GraphEditorKeys;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.TabsContainer;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabCreate implements ActionListener {

    private JFrame frame;
    private TabsContainer tabbedPane;
    private GEToolBar toolBar;
    private Tab tab;

    public TabCreate(JFrame frame, TabsContainer tabbedPane, GEToolBar toolBar) {
        this.frame = frame;
        this.tabbedPane = tabbedPane;
        this.toolBar = toolBar;
    }

    public void actionPerformed(ActionEvent ae) {
        tab = new Tab(frame,toolBar,tabbedPane);
        tab.setLayout(null);
        tabbedPane.addTab(GraphEditorKeys.NEW_TAB_NAME + (tabbedPane.getTabCount() + 1), tab);
        tabbedPane.setSelectedComponent(tab);
        frame.add(tabbedPane);
        frame.validate();
        frame.repaint();
    }

}

