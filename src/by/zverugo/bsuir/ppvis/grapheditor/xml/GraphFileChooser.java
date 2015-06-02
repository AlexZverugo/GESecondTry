package by.zverugo.bsuir.ppvis.grapheditor.xml;

import by.zverugo.bsuir.ppvis.grapheditor.util.GraphEditorKeys;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;

import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Alex on 10.05.2015.
 */
public class GraphFileChooser implements ActionListener {

    private JFileChooser fileChooser;
    private Tab tabPanel;


    public GraphFileChooser(Tab tabPanel) {
        this.tabPanel = tabPanel;
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(GraphEditorKeys.SAVING_FOLDER));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            int result = fileChooser.showDialog(null, GraphEditorKeys.SAVE_FILE);
            if (result == JFileChooser.APPROVE_OPTION) {
                new FileWriter(tabPanel, fileChooser.getSelectedFile().getPath());
            }
    }


}
