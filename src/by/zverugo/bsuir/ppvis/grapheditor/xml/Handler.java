package by.zverugo.bsuir.ppvis.grapheditor.xml;

import by.zverugo.bsuir.ppvis.grapheditor.logic.Line;
import by.zverugo.bsuir.ppvis.grapheditor.logic.Vertex;
import by.zverugo.bsuir.ppvis.grapheditor.storages.LineStorage;
import by.zverugo.bsuir.ppvis.grapheditor.storages.VertexStorage;
import by.zverugo.bsuir.ppvis.grapheditor.util.XMLTags;
import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.JFrame;
import java.awt.Point;

/**
 * Created by Alex on 10.05.2015.
 */
public class Handler extends DefaultHandler {

    private Tab tabPanel;
    private JFrame frame;
    private LineStorage lineStorage;
    private VertexStorage vertexStorage;
    private String name;
    private String vertexElementName;
    private String lineElementName;

    private int startX;
    private int startY;
    private int finishX;
    private int finishY;


    public Handler(Tab tabPanel) {
        this.tabPanel = tabPanel;
        frame = tabPanel.getFrame();
        lineStorage = tabPanel.getLineStorage();
        vertexStorage = tabPanel.getVertexStorage();
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
       name = qName;
    }

    public void endElement(String uri, String localName, String qName) throws SAXException{

        if (qName.equals(XMLTags.VERTEX)){
            Vertex vertex = new Vertex(frame,tabPanel, finishX, finishY);
            vertex.setName(vertexElementName);
        }
        if (qName.equals(XMLTags.LINE)) {
            Line line = new Line(new Point(startX,startY), new Point(finishX,finishY));
            line.setWeight(lineElementName);
            lineStorage.setLine(line);
        }
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String value = new String(ch, start, length).trim();
        vertexCharacters(value);
        lineCharacters(value);
    }

    private void vertexCharacters(String value) {
        if (name.equals(XMLTags.COORDINATE_X)){
            try {
                Integer x = new Integer(value);
                finishX = x;
            } catch (NumberFormatException exc) {
                System.out.println(exc.toString());
            }
        }
        if (name.equals(XMLTags.COORDINATE_Y)){
            try {
                Integer y = new Integer(value);
                finishY = y;
            } catch (NumberFormatException exc) {
                System.out.println(exc.toString());
            }
        }
        if (name.equals(XMLTags.VERTEX_NAME)){
            vertexElementName = value;
        }

    }

    private void  lineCharacters(String value) {

        if (name.equals(XMLTags.LINE_START_X)){
            try {
                Integer x = new Integer(value);
                startX = x;
            } catch (NumberFormatException exc) {
                System.out.println(exc.toString());
            }
        }
        if (name.equals(XMLTags.LINE_START_Y)){
            try {
                Integer y = new Integer(value);
                startY = y;
            } catch (NumberFormatException exc) {
                System.out.println(exc.toString());
            }
        }

        if (name.equals(XMLTags.LINE_FINISH_X)){
            try {
                Integer x = new Integer(value);
                finishX = x;
            } catch (NumberFormatException exc) {
                System.out.println(exc.toString());
            }
        }
        if (name.equals(XMLTags.LINE_FINISH_Y)){
            try {
                Integer y = new Integer(value);
                finishY = y;
            } catch (NumberFormatException exc) {
                System.out.println(exc.toString());
            }
        }

        if (name.equals(XMLTags.WEIGHT)) {
            lineElementName = value;
        }
    }

}
