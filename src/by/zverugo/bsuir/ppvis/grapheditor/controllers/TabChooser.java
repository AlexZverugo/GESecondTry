package by.zverugo.bsuir.ppvis.grapheditor.controllers;

import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.FileCreator;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.TabsContainer;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by Alex on 25.04.2015.
 */
public class TabChooser implements ChangeListener {

    private TabsContainer tabbedPane;

    public TabChooser (TabsContainer tabbedPane) {
        this.tabbedPane =  tabbedPane;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
//        Tab currentTab = (Tab)tabbedPane.getSelectedComponent();
        int numberOfTabs = tabbedPane.getTabCount();
        int currentTabIndex = tabbedPane.getSelectedIndex();

        for(int tabsIndex = 0; tabsIndex <  numberOfTabs; tabsIndex++ ){
            Tab tab = (Tab)tabbedPane.getComponentAt(tabsIndex);
            if (currentTabIndex == tabsIndex) {
                tab.getSaveButton().addActionListener(new FileCreator(tab.getGraphStorage()));
                continue;
            }
//            tab.getSaveButton().removeActionListener();

        }



//        System.out.println("IN");
    }
}
