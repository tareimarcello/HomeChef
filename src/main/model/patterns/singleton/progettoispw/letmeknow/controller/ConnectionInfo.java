package progettoispw.letmeknow.controller;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import progettoispw.letmeknow.controller.user.EmailInfo;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ConnectionInfo {
    private static final String FILENAME="src/main/resources/progettoispw/letmeknow/connection/ConnectionParameters.xml";
    private static String user;
    private static String pass;
    private static String dbUrl;
    private static String driverClassName;
    private static void getElements(){
            ParsingXMLFile parsing =new ParsingXMLFile();
            NodeList list = parsing.setEnvironment(FILENAME);
            for(int temp=0; temp < list.getLength(); temp++){
                Node node = list.item(temp);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    //Get attributes
                    //Actual getSTMT parameters from XML
                    user = element.getElementsByTagName("username").item(0).getTextContent();
                    pass = element.getElementsByTagName("password").item(0).getTextContent();
                    dbUrl = element.getElementsByTagName("url").item(0).getTextContent();
                    driverClassName = element.getElementsByTagName("driverName").item(0).getTextContent();
                }
            }
    }

    public Map<String,String> getConnectionInfo(){

        getElements();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", user);
        parameters.put("password", pass);
        parameters.put("url", dbUrl);
        parameters.put("driverName", driverClassName);

        return parameters;
    }

}