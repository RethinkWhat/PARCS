package server.model;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserParser {

    DocumentBuilder builder;
    Document document;

    final File userAccountsFile = new File("src/server/model/userAccounts.xml");


    /**
     * Method to retrieve the user accounts file and to be used for the methods found in this class
     */
    private void getUserAccountsFile() {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(userAccountsFile);
            document.getDocumentElement().normalize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Method to retrieve all the user objects in the xml file
     * @return List<User>
     */
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

    /**
     * Get a Map of all the users that has username as the key and the associated password as the value
     * Will be used for validating login attempt by server
     * @return
     */
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


    public void editUserInfo(String username, String tagToEdit, String newInformation) {

        getUserAccountsFile(); // Set document

        NodeList nodeList = document.getElementsByTagName("user");

        for (int x = 0; x < nodeList.getLength(); x ++) {
            Node curr = nodeList.item(x);
            if (curr.getAttributes().item(0).getTextContent().equalsIgnoreCase(username)) {

                NodeList currChildren = curr.getChildNodes();
                for (int y = 0 ; y < currChildren.getLength() ; y++) {
                    Node currNode = currChildren.item(y);

                    if (currNode.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println(currChildren.item(y).getNodeName() + " : " + tagToEdit);
                        if (currChildren.item(y).getNodeName().equalsIgnoreCase(tagToEdit)) {
                            System.out.println("reached");
                            currChildren.item(y).setTextContent(newInformation);
                            break;
                        }
                    }
                }
            }
        }
    }

    /** Method to create a user node to add to xml file */
    private Node createUserNode(String username, String type, String password,
                                String lastName, String firstName, String phoneNumber, ArrayList<Vehicle> vehicles) {


        Element userElement = document.createElement("user");
        userElement.setAttribute("user", username);

        Element typeNode = document.createElement("type");
        typeNode.setTextContent(type);
        userElement.appendChild(typeNode);

        Element passwordNode = document.createElement("password");
        passwordNode.setTextContent(password);
        userElement.appendChild(passwordNode);


        Element lastNameNode = document.createElement("lastName");
        lastNameNode.setTextContent(lastName);
        userElement.appendChild(lastNameNode);

        Element firstNameNode = document.createElement("firstName");
        firstNameNode.setTextContent(firstName);
        userElement.appendChild(firstNameNode);

        Element phoneNumberNode = document.createElement("phoneNumber");
        phoneNumberNode.setTextContent(phoneNumber);
        userElement.appendChild(phoneNumberNode);

        if (vehicles !=null) {
            for (Vehicle vehicle: vehicles) {
                Element vehicleNode = document.createElement("vehicle");
                vehicleNode.setTextContent(vehicle.toString());
                userElement.appendChild(vehicleNode);
            }
        }
        return userElement;


    }

    public void createUser(String username, String type, String password, String lastName, String firstName,
                           String phoneNumber, ArrayList<Vehicle> vehicles) {

        getUserAccountsFile();

        Node userNode = createUserNode(username, type, password, lastName, firstName, phoneNumber, vehicles);

        document.getDocumentElement().appendChild(userNode);
        DOMSource source = new DOMSource(document);

        TransformerFactory factory = null;
        Transformer transformer = null;
        try {
            factory = TransformerFactory.newInstance();
            transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StreamResult result = new StreamResult(userAccountsFile);
            transformer.transform(source,result);

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public String getUserFullName(String username) {
        String name = null;
        List<User> userList = getUserList();
        for (User user : userList) {
            if (user.getUsername().equals(username))
                name = user.getFirstName() + " " + user.getLastName();
        }
        return name;
    }

    /**
     * Retrieves the full information of the user:
     * 1. Last Name
     * 2. First Name
     * 3. Username
     * 4. Contact Number
     * @param username The specified username.
     * @return The full information of the user as comma-separated values.
     */
    public String getFullUserInfo(String username) {
        String lastName = null;
        String firstName = null;
        String contactNo = null;
        List<User> userList = getUserList();
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                lastName = user.getLastName();
                firstName = user.getFirstName();
                contactNo = user.getPhoneNumber();
            }
        }
        return lastName + "," + firstName + "," + username + "," + contactNo;
    }

    public boolean isAdmin(String username) {
        String name = null;
        List<User> userList = getUserList();
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return user.getType().equals("admin");
        }
        return false;
    }

    public boolean doesUsernameExist(String username) {
        getUserAccountsFile();

        NodeList nodeList = document.getElementsByTagName("user");

        for (int x =0 ; x<nodeList.getLength(); x++) {
            if (nodeList.item(x).getTextContent().equalsIgnoreCase(username))
                return true;
        }
        return false;
    }





    /** Will be deleted later */
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, Exception {
        UserParser obj = new UserParser();

        obj.createUser("laclac", "user","password","lacanilao","patrick","+639177900153",
                null);

    }
}
