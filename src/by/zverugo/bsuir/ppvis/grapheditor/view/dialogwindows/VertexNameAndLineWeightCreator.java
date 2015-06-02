package by.zverugo.bsuir.ppvis.grapheditor.view.dialogwindows;

import by.zverugo.bsuir.ppvis.grapheditor.util.GraphEditorKeys;
import by.zverugo.bsuir.ppvis.grapheditor.util.StaticResource;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VertexNameAndLineWeightCreator extends JOptionPane {

    private JFrame frame;
    private String currentName;

    public VertexNameAndLineWeightCreator(JFrame frame, String currentName){
        this.frame = frame;
        this.currentName = currentName;

    }

    public String createVertexName() {
        String returnedName = (String)showInputDialog(frame,
                GraphEditorKeys.ENTER_MSG,GraphEditorKeys.SELECTION, PLAIN_MESSAGE,
                new ImageIcon(StaticResource.OPEN_IMAGE.path()),null,currentName);
        if(returnedName != null){
            return returnedName;
        } else {
            return currentName;
        }
    }

}
