package by.zverugo.bsuir.ppvis.grapheditor.storages;

import javax.swing.JComponent;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 24.04.2015.
 */
public class GraphStorage {

    private List<LinkedList<JComponent>> graphList;
    private JComponent tempVertex;

    public  GraphStorage () {
        graphList = new LinkedList<LinkedList<JComponent>>();
    }

    public void setLine(JComponent vertex, JComponent line) {

        for (int listsIndex = 0; listsIndex < graphList.size(); listsIndex ++) {

            if (graphList.get(listsIndex).get(0) == tempVertex) {
                graphList.get(listsIndex).add(line);
            } else
            if (graphList.get(listsIndex).get(0) == vertex) {
                graphList.get(listsIndex).add(line);
            }
        }
    }


    public void setTempVertex (JComponent vertex) {
        tempVertex = vertex;
    }

    public void setVertex (JComponent vertex) {
            graphList.add(new LinkedList<JComponent>());
            graphList.get(graphList.size() - 1).add(vertex);
    }

    public void removeLine (JComponent tabPanel,JComponent line) {
        for (int listsIndex = 0; listsIndex < graphList.size(); listsIndex ++) {
            if (graphList.get(listsIndex).indexOf(line)!= -1) {
                tabPanel.remove(line);
                graphList.get(listsIndex).remove(line);
            }
        }
    }


    public void removeVertex(JComponent tabPanel, JComponent vertex) {
        for (int listsIndex = 0; listsIndex < graphList.size(); listsIndex ++) {

            if (graphList.get(listsIndex).indexOf(vertex)!= -1) {
                for (int internalListsIndex = 0;
                     internalListsIndex < graphList.get(listsIndex).size();
                     internalListsIndex ++) {
                    JComponent tempComponent = graphList.get(listsIndex).get(internalListsIndex);
                    tabPanel.remove(tempComponent);

                    for (int secondListsIndex = 0; secondListsIndex < graphList.size(); secondListsIndex ++){

                        if (graphList.get(secondListsIndex).
                                indexOf(tempComponent)!= -1) {
                            graphList.get(secondListsIndex).remove(tempComponent);
                        }
                    }

                    internalListsIndex --;
                }
                graphList.remove(listsIndex);
                break;
            }

        }
    }
}
