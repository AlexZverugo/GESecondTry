package by.zverugo.bsuir.ppvis.grapheditor.controllers;

import by.zverugo.bsuir.ppvis.grapheditor.util.GraphEditorKeys;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.TabsContainer;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabCreate implements ActionListener {

    private JFrame frame;
    private TabsContainer tabbedPane;
    private GEToolBar toolBar;
    private Tab tab;
    private AbstractButton saveButton;

    public TabCreate(JFrame frame, TabsContainer tabbedPane, GEToolBar toolBar, AbstractButton saveButton) {
        this.frame = frame;
        this.tabbedPane = tabbedPane;
        this.toolBar = toolBar;
        this.saveButton = saveButton;
    }

    public void actionPerformed(ActionEvent ae) {
        tab = new Tab(frame,toolBar,tabbedPane,saveButton);
        tab.setLayout(null);
        tabbedPane.addTab(GraphEditorKeys.NEW_TAB_NAME + (tabbedPane.getTabCount() + 1), tab);
        tabbedPane.setSelectedComponent(tab);
        frame.add(tabbedPane);
        frame.validate();
        frame.repaint();
    }

}

