package by.zverugo.bsuir.ppvis.grapheditor.view.tabs;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.storages.GraphStorage;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex on 24.04.2015.
 */
public class FileCreator implements ActionListener {

    private GraphStorage graphStorage;

    public FileCreator(GraphStorage graphStorage) {
        this.graphStorage = graphStorage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        List<List<JComponent>> graph = graphStorage.getGraphList();
        String fileName = "temp.txt";
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);

            for (List<JComponent> list : graph) {
                outputStream.write("&".getBytes());

                Vertex vertex = (Vertex) list.get(0);
                outputStream.write(vertex.getCoordinate().x);
                outputStream.write(vertex.getCoordinate().y);
                outputStream.write(vertex.getName().getBytes());

                for (int lineIndex = 1; lineIndex < list.size(); lineIndex++) {

                }
            }
            outputStream.close();
            System.out.println("Done");
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }
}
