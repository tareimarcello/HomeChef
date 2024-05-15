package progettoispw.letmeknow.controller;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import progettoispw.letmeknow.Exceptions;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ParsingXMLFile {
    public NodeList setEnvironment(String filename){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filename));
            doc.getDocumentElement().normalize();
            return  doc.getElementsByTagName("parameters");
        }catch (ParserConfigurationException | SAXException | IOException e){
            Exceptions.exceptionConnectionOccurred();
            return null;
        }
    }
}
