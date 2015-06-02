package by.zverugo.bsuir.ppvis.grapheditor.xml;

import by.zverugo.bsuir.ppvis.grapheditor.view.tabs.Tab;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Alex on 10.05.2015.
 */
public class FileReader {

    private Tab tabPanel;
    private String fileName;

    public FileReader(Tab tabPanel, String fileName) {
        this.tabPanel = tabPanel;
        this.fileName = fileName;

        try {
            readFile();
        } catch (ParserConfigurationException exc){
            System.out.println(exc.toString());
        } catch (IOException exc) {
            System.out.println(exc.toString());
        } catch (SAXException exc) {
            System.out.println(exc.toString());
        }
    }

    public void readFile() throws SAXException, IOException,
            ParserConfigurationException {
        File file = new File(fileName);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser parser = saxParserFactory.newSAXParser();

        parser.parse(file, new Handler(tabPanel));
        tabPanel.buildGraph();
    }



}
