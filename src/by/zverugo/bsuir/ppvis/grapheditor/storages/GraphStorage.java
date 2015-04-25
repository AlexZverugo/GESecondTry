package by.zverugo.bsuir.ppvis.grapheditor.storages;

import javax.swing.JComponent;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 24.04.2015.
 */
public class GraphStorage implements Serializable {

    private List<List<JComponent>> graphList;
    private JComponent tempVertex;

    public GraphStorage() {
        graphList = new LinkedList<List<JComponent>>();
    }

    public void setLine(JComponent vertex, JComponent line) {

        for (int listsIndex = 0; listsIndex < graphList.size(); listsIndex++) {

            if (graphList.get(listsIndex).get(0) == tempVertex) {
                graphList.get(listsIndex).add(line);
            } else if (graphList.get(listsIndex).get(0) == vertex) {
                graphList.get(listsIndex).add(line);
            }
        }
    }


    public List<List<JComponent>> getGraphList() {

        return graphList;
    }


    public void setTempVertex(JComponent vertex) {
        tempVertex = vertex;
    }

    public void setVertex(JComponent vertex) {
        graphList.add(new LinkedList<JComponent>());
        graphList.get(graphList.size() - 1).add(vertex);
    }

    public void removeLine(JComponent tabPanel, JComponent line) {
        for (int listsIndex = 0; listsIndex < graphList.size(); listsIndex++) {
            if (graphList.get(listsIndex).indexOf(line) != -1) {
                tabPanel.remove(line);
                graphList.get(listsIndex).remove(line);
            }
        }
    }


    public void removeVertex(JComponent tabPanel, JComponent vertex) {
        for (int listsIndex = 0; listsIndex < graphList.size(); listsIndex++) {

            List<JComponent> vertexes = graphList.get(listsIndex);
            if (vertexes.indexOf(vertex) != -1) {
                for (int internalListsIndex = 0;
                     internalListsIndex < vertexes.size();
                     internalListsIndex++) {
                    JComponent tempComponent = vertexes.get(internalListsIndex);
                    tabPanel.remove(tempComponent);

                    for (int secondListsIndex = 0; secondListsIndex < graphList.size(); secondListsIndex++) {

                        List<JComponent> edges = graphList.get(secondListsIndex);
                        if (edges.
                                indexOf(tempComponent) != -1) {
                            edges.remove(tempComponent);
                        }
                    }

                    internalListsIndex--;
                }
                graphList.remove(listsIndex);
                break;
            }

        }
    }
}
