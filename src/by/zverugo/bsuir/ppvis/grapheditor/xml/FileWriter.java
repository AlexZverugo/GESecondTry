package by.zverugo.bsuir.ppvis.grapheditor.xml;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.util.XMLTags;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.LinkedList;

/**
 * Created by Alex on 10.05.2015.
 */
public class FileWriter {

    private Tab tabPanel;
    private Element rootGraph;
    private LinkedList<Vertex> vertexList;
    private LinkedList<Line> lineList;
    private String fileName;


    public FileWriter(Tab tabPanel, String fileName) {
        this.tabPanel = tabPanel;
        this.fileName = fileName;
        vertexList = tabPanel.getVertexStorage().getVertexList();
        lineList = tabPanel.getLineStorage().getLineList();

        try {
            writeFile();
        } catch (ParserConfigurationException exc) {
            System.out.println(exc.toString());
        } catch (TransformerConfigurationException exc) {
            System.out.println(exc.toString());
        } catch (TransformerException exc) {
            System.out.println(exc.toString());
        }
    }

    private void writeFile() throws ParserConfigurationException,TransformerConfigurationException,
            TransformerException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.newDocument();

        rootGraph = document.createElement(XMLTags.ROOT_GRAPH_TAG);
        document.appendChild(rootGraph);

        Element vertexes = document.createElement(XMLTags.VERTEXES);
        rootGraph.appendChild(vertexes);

        for (Vertex tempVertex : vertexList) {
            String x = Integer.toString(tempVertex.getCoordinate().x);
            String y = Integer.toString(tempVertex.getCoordinate().y);

            Element vertex = document.createElement(XMLTags.VERTEX);
            vertexes.appendChild(vertex);

            Element coordinates = document.createElement(XMLTags.COORDINATES);
            vertex.appendChild(coordinates);

            Element coordX = document.createElement(XMLTags.COORDINATE_X);
            coordX.appendChild(document.createTextNode(x));
            coordinates.appendChild(coordX);

            Element coordY = document.createElement(XMLTags.COORDINATE_Y);
            coordY.appendChild(document.createTextNode(y));
            coordinates.appendChild(coordY);

            Element vertexName = document.createElement(XMLTags.VERTEX_NAME);
            String name = tempVertex.getName();
                vertexName.appendChild(document.createTextNode(name));

            vertex.appendChild(vertexName);
        }

        Element lines = document.createElement(XMLTags.LINES);
        rootGraph.appendChild(lines);

        for (Line tempLine : lineList) {
            String startX = Integer.toString(tempLine.getStartPoint().x);
            String startY = Integer.toString(tempLine.getStartPoint().y);
            String finishX = Integer.toString(tempLine.getFinishPoint().x);
            String finishY = Integer.toString(tempLine.getFinishPoint().y);

            Element line = document.createElement(XMLTags.LINE);
            lines.appendChild(line);

            Element startCoordinates = document.createElement(XMLTags.START_POINT_COORDINATES);
            line.appendChild(startCoordinates);

            Element startCoordX = document.createElement(XMLTags.LINE_START_X);
            Element startCoordY = document.createElement(XMLTags.LINE_START_Y);
            startCoordX.appendChild(document.createTextNode(startX));
            startCoordY.appendChild(document.createTextNode(startY));
            startCoordinates.appendChild(startCoordX);
            startCoordinates.appendChild(startCoordY);

            Element finishCoordinates = document.createElement(XMLTags.FINISH_POINT_COORDINATES);
            line.appendChild(finishCoordinates);

            Element finishCoordX = document.createElement(XMLTags.LINE_FINISH_X);
            Element finishCoordY = document.createElement(XMLTags.LINE_FINISH_Y);
            finishCoordX.appendChild(document.createTextNode(finishX));
            finishCoordY.appendChild(document.createTextNode(finishY));
            finishCoordinates.appendChild(finishCoordX);
            finishCoordinates.appendChild(finishCoordY);

            Element weight = document.createElement(XMLTags.WEIGHT);
            String name = tempLine.getWeight();
                weight.appendChild(document.createTextNode(name));

            line.appendChild(weight);

        }

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }


}
