package by.zverugo.bsuir.ppvis.grapheditor.controllers;

import by.zverugo.bsuir.ppvis.grapheditor.util.GraphEditorKeys;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.TabsContainer;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabCreator implements ActionListener {

    private JFrame frame;
    private TabsContainer tabbedPane;
    private GEToolBar toolBar;
    private Tab tab;
    private JMenuItem saveButton;

    public TabCreator(JFrame frame, TabsContainer tabbedPane, GEToolBar toolBar, JMenuItem saveButton) {
        this.frame = frame;
        this.tabbedPane = tabbedPane;
        this.toolBar = toolBar;
        this.saveButton = saveButton;
    }

    public void actionPerformed(ActionEvent ae) {
        tab = new Tab(frame,toolBar,tabbedPane,saveButton);
        tab.setLayout(null);
        tabbedPane.addTab(GraphEditorKeys.NEW_TAB_NAME, tab);
        tabbedPane.setSelectedComponent(tab);
        frame.add(tabbedPane);
        frame.validate();
        frame.repaint();
    }

}

