package by.zverugo.bsuir.ppvis.grapheditor.view.tabs;

import by.zverugo.bsuir.ppvis.grapheditor.logic.linebuilder.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.vertexbuilder.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.storages.GraphStorage;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
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
                Vertex vertex = (Vertex) list.get(0);
                outputStream.write(vertex.getCoordinate().x);
                outputStream.write(vertex.getCoordinate().y);
                outputStream.write(vertex.getName().getBytes());

                for (int lineIndex = 1; lineIndex < list.size(); lineIndex++) {
                    Line line = (Line) list.get(1);
                    outputStream.write(line.getFirstVertex().x);
                    outputStream.write(line.getFirstVertex().y);
                    outputStream.write(line.getSecondVertex().x);
                    outputStream.write(line.getSecondVertex().y);
                }

                outputStream.write("&".getBytes());

            }
            outputStream.close();
            System.out.println("Done");
        } catch (IOException ex) {
            System.out.println("Error");
        }

//        try{
//            FileOutputStream fout = new FileOutputStream("D:\\dev\\GESecondTry\\files\\graph.ge");
//            ObjectOutputStream oos = new ObjectOutputStream(fout);
//            oos.writeObject(graphStorage);
//            oos.close();
//            System.out.println("Done");
//
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }

    }
}
