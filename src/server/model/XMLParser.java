package server.model;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import utilities.User;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLParser {

    DocumentBuilder builder;
    Document document;


    public void getUserAccountsFile() {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(new File("src/server/model/userAccounts.xml"));
            document.getDocumentElement().normalize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<User> getUserList() {
        //Method which sets the value for which file to access by document builder
        getUserAccountsFile();

        NodeList nodeList = document.getElementsByTagName("user");

        List<User> userList = new ArrayList<>();
        for (int x = 0 ; x < nodeList.getLength(); x++) {

            Node first = nodeList.item(x);
            NodeList childAttributes = first.getChildNodes();
            ArrayList<String> userInfo = new ArrayList<>();
            userInfo.add(first.getAttributes().item(0).getTextContent());
            Node currNode;
            for (int i = 0; i < childAttributes.getLength(); i++) {
                currNode = childAttributes.item(i);
                if (currNode.getNodeType() == Node.ELEMENT_NODE) {
                    userInfo.add(String.valueOf(currNode.getTextContent()));
                }
            }
            userList.add(new User(userInfo));
        }
        return userList;
    }

    public Map<String, String> getUserLoginCredentials() {
        //Method which sets the value for which file to access by document builder
        getUserAccountsFile();

        NodeList nodeList = document.getElementsByTagName("user");

        Map<String, String> userList = new HashMap();
        for (int x = 0 ; x < nodeList.getLength(); x++) {

            Node first = nodeList.item(x);
            NodeList childAttributes = first.getChildNodes();
            Node currNode;
            for (int i = 0; i < childAttributes.getLength(); i++) {
                currNode = childAttributes.item(i);
                if (currNode.getNodeType() == Node.ELEMENT_NODE) {
                    if (currNode.getNodeName().equals("password")) {
                        userList.put(first.getAttributes().item(0).getTextContent(),currNode.getTextContent());
                        break;
                    }
                }
            }

        }
        return userList;
    }



    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, Exception {
        XMLParser obj = new XMLParser();
        Map<String, String> userList = obj.getUserLoginCredentials();
        System.out.println(userList.get("test"));;
    }
}
