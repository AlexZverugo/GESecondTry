package by.zverugo.bsuir.ppvis.grapheditor.controllers;

import by.zverugo.bsuir.ppvis.grapheditor.util.GraphEditorKeys;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.TabsContainer;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabCreator implements ActionListener {

    private JFrame frame;
    private TabsContainer tabbedPane;
    private GEToolBar toolBar;
    private Tab tab;
    private JMenuItem saveButton;
    private JScrollPane scroll;

    public TabCreator(JFrame frame, TabsContainer tabbedPane, GEToolBar toolBar) {
        this.frame = frame;
        this.tabbedPane = tabbedPane;
        this.toolBar = toolBar;
    }

    public void actionPerformed(ActionEvent ae) {
        tab = new Tab(frame,toolBar,tabbedPane);
        scroll = new JScrollPane(tab);
        tab.setLayout(null);
        tabbedPane.addTab(GraphEditorKeys.NEW_TAB_NAME, scroll);
        tabbedPane.setSelectedComponent(scroll);
        frame.add(tabbedPane);
        frame.validate();
        frame.repaint();
    }

}

