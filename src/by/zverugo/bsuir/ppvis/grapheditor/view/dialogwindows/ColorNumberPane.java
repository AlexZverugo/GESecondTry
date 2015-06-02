package by.zverugo.bsuir.ppvis.grapheditor.view.dialogwindows;

import by.zverugo.bsuir.ppvis.grapheditor.util.GraphEditorKeys;
import by.zverugo.bsuir.ppvis.grapheditor.util.StaticResource;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Created by Alex on 12.05.2015.
 */
public class ColorNumberPane extends JOptionPane {

    private JFrame frame;

    public ColorNumberPane(JFrame frame){
        this.frame = frame;
    }


    public String getColorNumber() {
        return (String)showInputDialog(frame,
                GraphEditorKeys.ENTER_NUMBER_MSG,GraphEditorKeys.SELECTION, PLAIN_MESSAGE,
                new ImageIcon(StaticResource.OPEN_IMAGE.path()),null,null);
    }
}
