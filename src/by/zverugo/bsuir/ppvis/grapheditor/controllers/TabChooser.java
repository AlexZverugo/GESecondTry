package by.zverugo.bsuir.ppvis.grapheditor.controllers;

import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.TabsContainer;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;
import by.zverugo.bsuir.ppvis.grapheditor.xml.FileOpener;
import by.zverugo.bsuir.ppvis.grapheditor.xml.GraphFileChooser;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

/**
 * Created by Alex on 25.04.2015.
 */
public class TabChooser implements ChangeListener {

    private TabsContainer tabbedPane;
    private Tab tab;
    private GEToolBar toolBar;

    public TabChooser (TabsContainer tabbedPane) {
        this.tabbedPane =  tabbedPane;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
//        Tab currentTab = (Tab)tabbedPane.getSelectedComponent();
        tab = (Tab) tabbedPane.getSelectedComponent();
        toolBar = tab.getToolBar();

//        addListenersSelectedComponent();

//        int numberOfTabs = tabbedPane.getTabCount();
//        int currentTabIndex = tabbedPane.getSelectedIndex();

//        for (int tabsIndex = 0; tabsIndex < numberOfTabs; tabsIndex++) {
//            Tab tab = (Tab) tabbedPane.getComponentAt(tabsIndex);
//
//
//            if (currentTabIndex == tabsIndex) {
//                continue;
//            }
//        }
    }



//        private void addListenersSelectedComponent() {
//        toolBar.getSaveButton().addActionListener(new GraphFileChooser(tab));
//
//        toolBar.getOpenButton().addActionListener(new FileOpener(tabbedPane, tab.getFrame(), toolBar));
//        }

//        System.out.println("IN");
}
