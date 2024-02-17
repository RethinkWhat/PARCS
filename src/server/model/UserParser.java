package server.model;

// w3c imports
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// xml imports
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

// IO imports
import java.io.File;

// util imports
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserParser {

    DocumentBuilder builder;
    Document document;

    final File userAccountsFile = new File("src/server/res/userAccounts.xml");


    /**
     * Method to retrieve the user accounts file and to be used for the methods found in this class
     */
    private void getUserAccountsFile() {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(userAccountsFile);
            document.getDocumentElement().normalize();
        } catch (IOException | SAXException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
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
                        if (currChildren.item(y).getNodeName().equalsIgnoreCase(tagToEdit)) {
                            System.out.println("reached");
                            currChildren.item(y).setTextContent(newInformation);
                            break;
                        }
                    }
                }
            }
        }
        transform();
    }

    /** Method to create a user node to add to xml file */
    private Node createUserNode(String username, String type, String password,
                                String lastName, String firstName, String phoneNumber, ArrayList<Vehicle> vehicles) {


        Element userElement = document.createElement("user");
        userElement.setAttribute("username", username);

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

        transform();
    }

    private void transform() {
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
        String password = null;
        List<User> userList = getUserList();
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                lastName = user.getLastName();
                firstName = user.getFirstName();
                contactNo = user.getPhoneNumber();
                password = user.getPassword();
            }
        }
        return lastName + "," + firstName + "," + username + "," + contactNo + "," + password;
    }

    /**
     * Changes the user's password.
     * @param username The specified username of the user.
     * @param password The specified password of the user.
     */
    public void changePassword(String username, String password) {
        List<User> userList = getUserList();
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                user.setPassword(password);
            }
        }
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

    public void addVehicle(String username, Vehicle vehicle) {
        getUserAccountsFile();

        NodeList nodeList = document.getElementsByTagName("user");

        Element vehicleElement = document.createElement("vehicle");
        vehicleElement.setTextContent(vehicle.toString());

        for (int x =0 ; x < nodeList.getLength(); x++) {
            if (nodeList.item(x).getAttributes().item(0).getTextContent().equalsIgnoreCase(username)) {
                nodeList.item(x).appendChild(vehicleElement);
            }
        }
        transform();
    }

    /**
     * Gets all the vehicle of a certain user returing a map with plate number as the key and vehicle/model as the value
     * @param username
     * @return
     */
    public Map<String, List<String>> getUserVehicles(String username){
        getUserAccountsFile();

        Map<String, List<String>> userVehicles = new HashMap<>();

        Element root = document.getDocumentElement();

        NodeList userNodes = root.getElementsByTagName("user");

        for (int i = 0; i < userNodes.getLength(); i++){
            Element currUserElement = (Element) userNodes.item(i);

            //Checks if the username of a user node is equals to the passed username
            if (currUserElement.getAttribute("username").equalsIgnoreCase(username)){

                NodeList vehicleNodes = currUserElement.getElementsByTagName("vehicle");

                for (int j = 0; j < vehicleNodes.getLength(); j++){

                    Element currVehicle = (Element) vehicleNodes.item(j);

                    //Splits the vehicle element value into 3
                    String[] vehicleInformation = currVehicle.getTextContent().split(",");

                    String vehicle = vehicleInformation[0];
                    String model = vehicleInformation[1];
                    String plateNumber = vehicleInformation[2];

                    List<String> currVehicleModel = new ArrayList<>();
                    currVehicleModel.add(vehicle);
                    currVehicleModel.add(model);

                    userVehicles.put(plateNumber, currVehicleModel);
                }

            }
        }
        return userVehicles;
    }

    public boolean editVehicle(String username, String plateNo, String newInformation) {
        getUserAccountsFile();
        try {
            NodeList nodeList = document.getElementsByTagName("user");

            for (int x = 0; x < nodeList.getLength(); x ++) {
                Node curr = nodeList.item(x);
                if (curr.getAttributes().item(0).getTextContent().equalsIgnoreCase(username)) {

                    NodeList currChildren = curr.getChildNodes();
                    for (int y = 0 ; y < currChildren.getLength() ; y++) {
                        Node currNode = currChildren.item(y);

                        if (currNode.getNodeType() == Node.ELEMENT_NODE) {
                            if (currChildren.item(y).getNodeName().equalsIgnoreCase("vehicle")) {
                                if (currChildren.item(y).getTextContent().contains(plateNo)) {
                                    currChildren.item(y).setTextContent(newInformation);
                                }
                                break;
                            }
                        }
                    }
                }
            }
            transform();

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void deleteUser(String username) {
        getUserAccountsFile();
        NodeList nodeList = document.getElementsByTagName("user");
        for (int x = 0; x < nodeList.getLength(); x++) {
            Node curr = nodeList.item(x);
            if (curr.getAttributes().item(0).getTextContent().equalsIgnoreCase(username)) {
                Node parent = curr.getParentNode();
                parent.removeChild(curr);
            }
        }
        transform();
    }

    /** Will be deleted later */
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, Exception {
        UserParser obj = new UserParser();

        obj.deleteUser("basti");

//        obj.editUserInfo("basti", "lastName", "SicSic");
        //obj.createUser("laclac", "user","password","lacanilao","patrick","+639177900153",
           //     null);
        //obj.addVehicle("ri", new Vehicle("Car", "Civic", "A4949329"));

    }
}
