package by.zverugo.bsuir.ppvis.grapheditor.controllers;

import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.TabsContainer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabCloser implements ActionListener {
    private TabsContainer tabbedPane;

    public TabCloser(TabsContainer tabbedPane){
        this.tabbedPane = tabbedPane;
    }

    public void actionPerformed(ActionEvent e) {
        int select = tabbedPane.getSelectedIndex();
        if (select >= 0){
            tabbedPane.removeTabAt(select);
        }
    }
}
