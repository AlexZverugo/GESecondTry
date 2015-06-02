package by.zverugo.bsuir.ppvis.grapheditor.view.toolbar;

import by.zverugo.bsuir.ppvis.grapheditor.algorithm.GraphColorMaker;
import by.zverugo.bsuir.ppvis.grapheditor.util.StaticResource;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import by.zverugo.bsuir.ppvis.grapheditor.xml.FileOpener;
import by.zverugo.bsuir.ppvis.grapheditor.xml.GraphFileChooser;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alex on 12.05.2015.
 */
public class TabToolBar extends JToolBar {

    private Tab tabPanel;
    private JButton saveButton;
    private JButton openButton;
    private JButton colorButton;
    private JButton informationButton;
    private GraphFileChooser graphFileChooser;
    private FileOpener fileOpener;
    private GraphColorMaker graphColorMaker;

    public TabToolBar(Tab tabPanel) {
        this.tabPanel = tabPanel;
        saveButton = buildSaveButton();
        openButton = buildOpenButton();
        colorButton = new JButton(new ImageIcon(StaticResource.GRAPH_IMAGE.path()));
        informationButton = buildInformationButton();
//        setPreferredSize(new Dimension(20, 100));
//        setBorder(new LineBorder(Color.BLUE));
        setVisible(true);
        addButtonsListeners();
        toolBarOptionsAndItems();
    }


    private JButton buildSaveButton() {
        JButton saveButton = new JButton(new ImageIcon(StaticResource.SAVE_IMAGE.path()));

        return saveButton;
    }

    private JButton buildOpenButton() {
        JButton openButton = new JButton(new ImageIcon(StaticResource.OPEN_IMAGE.path()));

        return openButton;
    }

    private JButton buildInformationButton() {
        JButton informationButton = new JButton("Inf.");

        return informationButton;
    }

    private void toolBarOptionsAndItems() {
        setOrientation(SwingConstants.VERTICAL);
        setFloatable(false);
        add(informationButton);
        add(saveButton);
        add(openButton);
        add(colorButton);
    }


    private void addButtonsListeners() {
        graphColorMaker = new GraphColorMaker(tabPanel);
        fileOpener = new FileOpener(tabPanel.getTabbedPane(), tabPanel.getFrame(),tabPanel.getToolBar());
        graphFileChooser = new GraphFileChooser(tabPanel);
        saveButton.addActionListener(graphFileChooser);
        openButton.addActionListener(fileOpener);
        colorButton.addActionListener(graphColorMaker);
        informationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Number of Vertexes: "
                        + tabPanel.getVertexStorage().getVertexListSize() + "\n" + "Number of Edges: "
                        + tabPanel.getLineStorage().getLineListSize());
            }
        });
    }

}
