package progettoispw.letmeknow.controller.user;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import progettoispw.letmeknow.Exceptions;
import progettoispw.letmeknow.controller.ParsingXMLFile;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;

public class EmailInfo {
    private static final String FILENAME="src/main/resources/progettoispw/letmeknow/emailMeta/EmailParameters.xml";
    private String email;
    private String password;
    public EmailInfo() {
        getElements();
    }
    private void getElements(){
        ParsingXMLFile parsng=new ParsingXMLFile();
        NodeList list= parsng.setEnvironment(FILENAME);
        for(int temp=0; temp < list.getLength(); temp++){
            Node node = list.item(temp);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                //Get attributes
                //Actual getSTMT parameters from XML
                email = element.getElementsByTagName("email").item(0).getTextContent();
                password = element.getElementsByTagName("password").item(0).getTextContent();
            }
        }
    }
    public String getPassword() {
        return password;
    }
    public  String getEmail() {
        return email;
    }
}