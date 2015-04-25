package by.zverugo.bsuir.ppvis.grapheditor.builder.impl;

import by.zverugo.bsuir.ppvis.grapheditor.builder.Builder;
import by.zverugo.bsuir.ppvis.grapheditor.controllers.ExitControl;
import by.zverugo.bsuir.ppvis.grapheditor.controllers.TabClose;
import by.zverugo.bsuir.ppvis.grapheditor.controllers.TabCreate;
import by.zverugo.bsuir.ppvis.grapheditor.storages.VertexStorage;
import by.zverugo.bsuir.ppvis.grapheditor.util.GraphEditorKeys;
import by.zverugo.bsuir.ppvis.grapheditor.util.StaticResource;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.FileOpener;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.TabsContainer;
import by.zverugo.bsuir.ppvis.grapheditor.view.toolbar.GEToolBar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Alex on 15.03.2015.
 */
public class MenuBuilder implements Builder<JMenu> {
    private JMenu menu;
    private JFrame frame;
    private TabsContainer tabbedPane;
    private String text;
    private int mnemonic;
    private boolean exitSeparator;
    private VertexStorage vertexStorage;

    // Menu Items
    private JMenuItem newItem;
    private JMenuItem openItem;
    private JMenuItem closeItem;
    private JMenuItem saveItem;
    private JMenuItem saveAsItem;
    private JMenuItem exitItem;
    private JMenuItem copyItem;
    private JMenuItem cutItem;
    private JMenuItem pasteItem;
    private GEToolBar toolBar;

    public MenuBuilder text(String text) {
        this.text = text;

        return this;
    }


    public MenuBuilder mnemonic(int mnemonic) {
        this.mnemonic = mnemonic;

        return this;
    }

    public MenuBuilder exitSeparator() {
        this.exitSeparator = true;

        return this;
    }

    public MenuBuilder frame(JFrame frame) {
        this.frame = frame;

        return this;
    }

    public MenuBuilder tabbedPane(TabsContainer tabbedPane) {
        this.tabbedPane = tabbedPane;

        return this;
    }


    public MenuBuilder toolBar(GEToolBar toolBar) {
        this.toolBar = toolBar;

        return this;
    }

    public MenuBuilder newItem() {
        JMenuItem newItem = new JMenuItem(GraphEditorKeys.NEW, KeyEvent.VK_N);
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        newItem.addActionListener(new TabCreate(frame,tabbedPane, toolBar,saveItem));
        this.newItem = newItem;

        return this;
    }

    public MenuBuilder openItem() {
        JMenuItem openItem = new JMenuItem(GraphEditorKeys.OPEN, new ImageIcon(StaticResource.OPEN_IMAGE.path()));
        openItem.setMnemonic(KeyEvent.VK_O);
        this.openItem = openItem;
        openItem.addActionListener(new FileOpener(frame,tabbedPane, toolBar,saveItem));

        return this;
    }

    public MenuBuilder closeItem() {
        JMenuItem closeItem = new JMenuItem(GraphEditorKeys.CLOSE, KeyEvent.VK_C);
        closeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        closeItem.addActionListener(new TabClose(tabbedPane));
        this.closeItem = closeItem;

        return this;
    }

    public MenuBuilder saveItem() {
        this.saveItem = new JMenuItem(GraphEditorKeys.SAVE, KeyEvent.VK_S);

        return this;
    }

    public MenuBuilder saveAsItem() {
        this.saveAsItem = new JMenuItem(GraphEditorKeys.SAVE_AS, KeyEvent.VK_A);

        return this;
    }

    public MenuBuilder exitItem() {
        JMenuItem exitItem = new JMenuItem(GraphEditorKeys.EXIT, KeyEvent.VK_E);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        exitItem.addActionListener(new ExitControl());
        this.exitItem = exitItem;

        return this;
    }

    public MenuBuilder copyItem() {
        copyItem = new JMenuItem(GraphEditorKeys.COPY, new ImageIcon(StaticResource.COPY_IMAGE.path()));
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));

        return this;
    }

    public MenuBuilder cutItem() {
        cutItem = new JMenuItem(GraphEditorKeys.CUT, new ImageIcon(StaticResource.SCISSORS_IMAGE.path()));
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));

        return this;
    }

    public MenuBuilder pasteItem() {
        pasteItem = new JMenuItem(GraphEditorKeys.PASTE, new ImageIcon(StaticResource.PASTE_IMAGE.path()));
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));

        return this;
    }

    @Override
    public JMenu build() {
        validateRequiredParams();

        menu = new JMenu();

        menu.setText(text);
        menu.setMnemonic(mnemonic);
        addMenuItem(newItem);
        addMenuItem(openItem);
        addMenuItem(saveItem);
        addMenuItem(saveAsItem);
        addMenuItem(closeItem);
        if (exitSeparator) {
            menu.addSeparator();
        }
        addMenuItem(exitItem);
        addMenuItem(copyItem);
        addMenuItem(cutItem);
        addMenuItem(pasteItem);

        return menu;
    }

    private void validateRequiredParams() {
        if (text == null) {
            throw new IllegalStateException(GraphEditorKeys.TEXT_NOT_NULL_ERR_MSG);
        }
        if (frame == null) {
            throw new IllegalStateException(GraphEditorKeys.FRAME_NOT_NULL_ERR_MSG);
        }
        if (tabbedPane == null) {
            throw new IllegalStateException(GraphEditorKeys.TABBED_PANE_NOT_NULL_ERR_MSG);
        }
        if (toolBar == null){
            throw new IllegalStateException(GraphEditorKeys.TOOL_BAR_NOT_NULL_ERR_MSG);
        }
    }

    private void addMenuItem(JMenuItem item) {
        if (item != null) {
            menu.add(item);
        }
    }

    public void reset() {
        menu = null;
        frame = null;
        tabbedPane = null;
        text = null;
        mnemonic = 0;
        exitSeparator = false;
        newItem = null;
        openItem = null;
        closeItem = null;
        saveItem = null;
        saveAsItem = null;
        exitItem = null;
        copyItem = null;
        cutItem = null;
        pasteItem = null;
    }
}
