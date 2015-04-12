package by.zverugo.bsuir.ppvis.grapheditor.view;


import by.zverugo.bsuir.ppvis.grapheditor.builder.impl.MenuBuilder;
import by.zverugo.bsuir.ppvis.grapheditor.util.GraphEditorKeys;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.TabsContainer;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class MainFrame {

    private JFrame frame;
    private TabsContainer tabbedPane;
    private GEToolBar toolBar;

    public MainFrame() {
        frame = new JFrame(GraphEditorKeys.GRAPH_EDITOR_FRAME_TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        mainPanel.setBackground(Color.BLUE);

        setUpWindowSize();

        tabbedPane = new TabsContainer();

        //buildScrollBar();
        buildToolBar();
        buildMenu();

        frame.setVisible(true);
    }


    private void buildToolBar() {
        toolBar = new GEToolBar();
        frame.add(toolBar, BorderLayout.WEST);
    }


    private void buildScrollBar() {
        JScrollBar verticalScroll = new JScrollBar();
        JScrollBar horizontalScroll = new JScrollBar(Adjustable.HORIZONTAL);
        frame.add(horizontalScroll, BorderLayout.SOUTH);
        frame.add(verticalScroll, BorderLayout.EAST);
    }


    private void setUpWindowSize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        frame.setSize(screenHeight / 2, screenWidth / 3);
        frame.setLocationRelativeTo(null);
    }

    private void buildMenu() {
        JMenuBar mainMenu = new JMenuBar();

        MenuBuilder menuBuilder = new MenuBuilder();

        JMenu fileMenu = menuBuilder
                .text(GraphEditorKeys.FILE).frame(frame).tabbedPane(tabbedPane).toolBar(toolBar).newItem()
                .openItem().closeItem().saveItem().saveAsItem().exitItem().exitSeparator()
                .mnemonic(KeyEvent.VK_F).build();

        menuBuilder.reset();

        JMenu editMenu = menuBuilder
                .text(GraphEditorKeys.EDIT).frame(frame).tabbedPane(tabbedPane).toolBar(toolBar)
                .copyItem().cutItem().pasteItem().mnemonic(KeyEvent.VK_E).build();

        mainMenu.add(fileMenu);
        mainMenu.add(editMenu);

        frame.setJMenuBar(mainMenu);
    }
}
