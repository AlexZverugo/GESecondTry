package by.zverugo.bsuir.ppvis.grapheditor.xml;

import by.zverugo.bsuir.ppvis.grapheditor.util.GraphEditorKeys;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.TabsContainer;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Alex on 10.05.2015.
 */
public class FileOpener implements ActionListener {
    private JFileChooser fileChooser;
    private TabsContainer tabsContainer;
    private Tab tabPanel;
    private JFrame frame;
    private GEToolBar toolBar;
    private JScrollPane scroll;


    public FileOpener (TabsContainer tabsContainer, JFrame frame, GEToolBar toolBar) {
        this.tabsContainer = tabsContainer;
        this.frame = frame;
        this.toolBar = toolBar;
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(GraphEditorKeys.SAVING_FOLDER));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            int result = fileChooser.showDialog(null, GraphEditorKeys.OPEN_FILE);
            if (result == JFileChooser.APPROVE_OPTION) {
                if (fileChooser.getSelectedFile().getName().contains(GraphEditorKeys.DOT_XML)) {
                    createTab(fileChooser.getSelectedFile().getName());
                    new FileReader(tabPanel, fileChooser.getSelectedFile().getPath());
                }

            }

    }

    private void createTab(String name) {
        tabPanel = new Tab(frame,toolBar,tabsContainer);
        scroll = new JScrollPane(tabPanel);
        tabPanel.setLayout(null);
        tabsContainer.addTab(name, scroll);
        tabsContainer.setSelectedComponent(scroll);
        frame.add(tabsContainer);
        frame.validate();
        frame.repaint();
    }


}
