package by.zverugo.bsuir.ppvis.grapheditor.view.tabs;

import by.zverugo.bsuir.ppvis.grapheditor.controllers.TabChooser;

import javax.swing.JTabbedPane;

/**
 * Created by Alex on 23.03.2015.
 */
public class TabsContainer extends JTabbedPane {

    public TabsContainer() {
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setVisible(true);

//        addChangeListener(new TabChooser(this));
    }
}
