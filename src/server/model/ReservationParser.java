package server.model;


import client.model.DateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

import java.sql.Time;
import java.util.*;

public class ReservationParser {


    DocumentBuilder builder;
    Document document;


    DateTime dateTime = new DateTime();

    HashMap<String, Integer> timeHashMap;

    final File reservationsFile = new File("src/server/res/reservations.xml");

    DateTime dateTimeFormatter = new DateTime();

    public ArrayList<String> populateTime() {
        ArrayList<String> timeArray = new ArrayList<>();
        String[] time =  {"6:00", "7:00", "8:00", "9:00",
                "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
        for (String timeItem : time)
            timeArray.add(timeItem);
        return timeArray;
    }
    private void getReservationsFile() {
        try {
            builder = DocumentBuilderFactory.newNSInstance().newDocumentBuilder();
            document = builder.parse(reservationsFile);
            document.getDocumentElement().normalize();
        } catch (IOException | SAXException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to retrieve parking information
     */
    public List<ParkingSpot> getParkingInformation() {
        getReservationsFile();

        List<ParkingSpot> parkingSpotList = new ArrayList<>();

        NodeList nodeList = document.getElementsByTagName("parkingSpot");

        for (int x = 0; x < nodeList.getLength(); x++) {
            // Will hold the parking spot object in each iteration
            ParkingSpot currParkingSpot = new ParkingSpot();
            Node currSpot = nodeList.item(x);

            if (currSpot.getNodeType() == Node.ELEMENT_NODE) {
                Element currSpotAsElement = (Element) currSpot;
                String currParkingSpotIdentifier = currSpotAsElement.getAttribute("identifier");

                // Set the parking identifier for curr parking spot
                currParkingSpot.setIdentifier(currParkingSpotIdentifier);

                NodeList reservationList = currSpotAsElement.getElementsByTagName("reservation");
                for (int y = 0; y< reservationList.getLength(); y++) {
                    Node currReservationNode = reservationList.item(y);

                    if (currReservationNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element currReservationAsElement = (Element) currReservationNode;

                        String date = currReservationAsElement.getAttribute("day");
                        String startTime = currReservationAsElement.getElementsByTagName("startTime").item(0).getTextContent();
                        String endTime = currReservationAsElement.getElementsByTagName("endTime").item(0).getTextContent();
                        String user = currReservationAsElement.getElementsByTagName("user").item(0).getTextContent();

                        TimeRange currReservationTimeRange = new TimeRange(startTime, endTime);
                        Reservations currReservation = new Reservations();

                        currReservation.setDate(date);
                        currReservation.getTimeAndUserMap().put(currReservationTimeRange, user);
                        currParkingSpot.getReservationsList().add(currReservation);
                    }
                }
            }
            parkingSpotList.add(currParkingSpot);
        }
        return parkingSpotList;
    }

    /**
     * This method will return a ParkingSpot's current information
     * @param identifier
     * @return
     */
    public ParkingSpot getParkingSlotInformationByIdentifier(String identifier){
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setIdentifier(identifier);

        getReservationsFile();

        NodeList nodeList = document.getElementsByTagName("parkingSpot");


        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currParkingSpotNode = nodeList.item(i);

            // Check if the current node is an element node
            if (currParkingSpotNode.getNodeType() == Node.ELEMENT_NODE) {
                Element parkingSpotElement = (Element) currParkingSpotNode;
                String currParkingSpotIdentifier = parkingSpotElement.getAttribute("identifier");

                if (currParkingSpotIdentifier.equalsIgnoreCase(identifier)) {

                    //Get the reservation nodes
                    NodeList reservationList = parkingSpotElement.getElementsByTagName("reservation");

                    for (int j = 0; j < reservationList.getLength(); j++) {
                        //Getting the current reservation node with respect to j
                        Node reservationNode = reservationList.item(j);

                        if (reservationNode.getNodeType() == Node.ELEMENT_NODE) {

                            //Casting reservation node to an Element object
                            Element reservationElement = (Element) reservationNode;

                            String date = reservationElement.getAttribute("day");
                            String startTime = reservationElement.getElementsByTagName("startTime").item(0).getTextContent();
                            String endTime = reservationElement.getElementsByTagName("endTime").item(0).getTextContent();
                            String user = reservationElement.getElementsByTagName("user").item(0).getTextContent();

                            TimeRange currReservationTimeRange = new TimeRange(startTime, endTime);
                            Reservations currReservation = new Reservations();

                            currReservation.setDate(date);
                            currReservation.getTimeAndUserMap().put(currReservationTimeRange, user);
                            parkingSpot.getReservationsList().add(currReservation);
                        }
                    }
                    break;
                }
            }
        }
        return parkingSpot;
    }

    public int countBookings() {
        ReservationParser parser = new ReservationParser();
        List<ParkingSpot> parkingSpotList = parser.getParkingInformation();
        int size = 0;

        for (ParkingSpot parkingSpot : parkingSpotList) {
            List<Reservations> reservationList =  parkingSpot.getReservationsList();
            for (Reservations reservation : reservationList ) {
                size++;
            }
        }
        return size;
    }

    /**
     * Returns all reservation of a user with its corresponding parking slot
     * @param userName
     * @return
     */
    public Map<String, Reservations> getUserReservations(String userName) {
        getReservationsFile();

        /**
         * Key: Parking Slot Identifier
         * Value: Reservations object
         *
         * EXAMPLE:
         * {"C1", Reservations}
         */
        Map<String, Reservations> userReservations = new HashMap<>();

        NodeList nodeList = document.getElementsByTagName("parkingSpot");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node currParkingSpotNode = nodeList.item(i);

            if (currParkingSpotNode.getNodeType() == Element.ELEMENT_NODE){

                //Getting the current parking spot
                Element currParkingSpotElement = (Element) nodeList.item(i);

                NodeList reservationList = currParkingSpotNode.getChildNodes();

                for (int j = 0; j < reservationList.getLength(); j++) {
                    Node reservationNode = reservationList.item(j);

                    if (reservationNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element currReservationElement = (Element) reservationNode;

                        //Getting the username in a certain reservation
                        String currUsername = currReservationElement.getElementsByTagName("user").item(0).getTextContent();

                        //Checks if the passed username is equals to the current reservation's username
                        if (userName.equalsIgnoreCase(currUsername)) {
                            String day = currReservationElement.getAttribute("day");
                            String startTime = currReservationElement.getElementsByTagName("startTime").item(0).getTextContent();
                            String endTime = currReservationElement.getElementsByTagName("endTime").item(0).getTextContent();

                            TimeRange currTimeRange = new TimeRange(startTime, endTime);

                            //Creating a new reservation and adding the current reservation's time range and username to the map of Reservation object
                            Reservations reservations = new Reservations();
                            reservations.getTimeAndUserMap().put(currTimeRange, currUsername);
                            reservations.setDate(day);

                            String parkingSpotIdentifier = currParkingSpotElement.getAttribute("identifier");

                            //Adding the reservations of the user to the userReservations map
                            userReservations.put(parkingSpotIdentifier, reservations);
                        }else {
                            continue;
                        }
                    }
                }
            }

        }
        return userReservations;
    }

    public List<TimeRange> getParkingSpotAvailability(String identifier, String date){
        System.out.println("DATE PASSED IN: " + date);
        System.out.println("IDENTIFIER: " + identifier);
        getReservationsFile();

        //This will have a list of time ranges that is booked in a certain date and a certain parking spot identifier
        List<TimeRange> bookedTimeRange = new ArrayList<>();

        Element root = document.getDocumentElement();

        NodeList parkingSpotNodes = root.getElementsByTagName("parkingSpot");

        //Iterate to all parkingSpot nodes
        for (int i = 0; i < parkingSpotNodes.getLength(); i++){

            Element currParkingSpotElement = (Element) parkingSpotNodes.item(i);

            //Checks if the current parking spot node has an identifier similar to the identifier passed as an argument
            if (currParkingSpotElement.getAttribute("identifier").equalsIgnoreCase(identifier)){

                NodeList reservationNodes = currParkingSpotElement.getElementsByTagName("reservation");

                for (int j = 0; j < reservationNodes.getLength(); j++){
                    Element currReservationElement = (Element) reservationNodes.item(j);

                    //Checks if current date of a reservation is equals to the date passed as an argument
                    if (currReservationElement.getAttribute("day").equalsIgnoreCase(date)){

                        TimeRange validTimeRange = new TimeRange(currReservationElement.getElementsByTagName("startTime").item(0).getTextContent(), currReservationElement.getElementsByTagName("endTime").item(0).getTextContent());

                        //Adds the TimeRange of the reservation to the booked time ranges in a certain parking spot identifier
                        bookedTimeRange.add(validTimeRange);
                    }
                }

            }
        }
        return bookedTimeRange;
    }

    /**
     * This method will create a reservation node for the xml file
     * If the passed identifier already exists, it will add a reservation under that identifier
     * Else, it will create a new parking spot in the xml file with the new reservation
     * @param identifier
     * @param date
     * @param startTime
     * @param duration
     * @param username
     */
    public void createReservationNode(String identifier, String date, String startTime, String duration, String username){
        getReservationsFile();

        Element root = document.getDocumentElement();

        NodeList parkingSpotNodes = root.getElementsByTagName("parkingSpot");

        Element reservationElement = document.createElement("reservation");
        reservationElement.setAttribute("day", date);

        Element startTimeElement = document.createElement("startTime");
        startTimeElement.setTextContent(startTime);
        reservationElement.appendChild(startTimeElement);

        Element endTimeElement = document.createElement("endTime");
        String[] timeParts = startTime.split(":");
        int endTime = Integer.parseInt(timeParts[0]) + Integer.parseInt(duration);
        endTimeElement.setTextContent(Integer.toString(endTime) + ":00");
        reservationElement.appendChild(endTimeElement);

        Element usernameElement = document.createElement("user");
        usernameElement.setTextContent(username);
        reservationElement.appendChild(usernameElement);

        // Searching if the passed identifier already exists
        for (int i = 0; i < parkingSpotNodes.getLength(); i++){
            Element currParkingSpotElement = (Element) parkingSpotNodes.item(i);

            //If the passed identifier already exists, it will add that reservation under the passed identifier
            if (currParkingSpotElement.getAttribute("identifier").equalsIgnoreCase(identifier)){
                currParkingSpotElement.appendChild(reservationElement);
                transform();
                return;
            }
        }

        // This adds a new parking spot with the reservation node if the passed identifier does not exist
        Element parkingSpotElement = document.createElement("parkingSpot");
        parkingSpotElement.setAttribute("identifier", identifier);
        parkingSpotElement.appendChild(reservationElement);
        root.appendChild(parkingSpotElement);
        transform();
    }

    /**
     * A transformer that will write into the reservationsFile
     */
    private void transform(){
        DOMSource source = new DOMSource(document);

        try{

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

            StreamResult result = new StreamResult(reservationsFile);

            transformer.transform(source,result);

        }catch (TransformerException te){
            te.printStackTrace();
        }
    }

    public List<String> availableTime(String date, String duration, String identifier) {
        ArrayList<String> timeArray = populateTime();
        DateTime dateTime = new DateTime();
        List<TimeRange> bookedTimeRange = getParkingSpotAvailability(date, identifier);

        System.out.println("BOOKED TIME: " + bookedTimeRange);

        List<String> allBookings = new ArrayList<>();
        List<String> allTime = new ArrayList<>(timeArray);
        List<String> toReturnTime = new ArrayList<>();
        int durationAsInt = Integer.parseInt(duration);

        for (TimeRange timeRange : bookedTimeRange) {
            allBookings.addAll(timeRange.getStartToEndTime());
        }

        for (String time : allTime) {
            boolean isAvailable = true;

            for (int i = 0; i < durationAsInt; i++) {
                if (allBookings.contains(dateTime.addDuration(time, i)) || (Integer.parseInt(dateTime.addDuration(time, i).split(":")[0]) + durationAsInt) > 18) {
                    isAvailable = false;
                    break;  // Break if any of the incremented times is booked
                }
            }

            if (isAvailable) {
                toReturnTime.add(time);
            }
        }

        System.out.println("RETURNING: " + toReturnTime);
        return toReturnTime;
    }


    public List<Integer> computeDuration(String startTime, String endTime){
        List<Integer> duration = new ArrayList<>();

        String[] startTimeParts = startTime.split(":");
        String[] endTimeParts = endTime.split(":");

        int totalHours = Integer.parseInt(endTimeParts[0]) - Integer.parseInt(startTimeParts[0]);

        duration.add(totalHours);
        duration.add(00);

        return duration;
    }

    public List<String> getClosestReservation(String username){
        getReservationsFile();

        List<String> reservationInformation = new ArrayList<>();
        reservationInformation.add("XX");
        reservationInformation.add("24:00");
        reservationInformation.add("00:00");
        reservationInformation.add("default");

        Element root = document.getDocumentElement();

        NodeList parkingSpotNodes = root.getElementsByTagName("parkingSpot");

        for (int i = 0; i < parkingSpotNodes.getLength(); i++){
            Element currParkingSpotElement = (Element) parkingSpotNodes.item(i);

            NodeList reservationNodes = currParkingSpotElement.getElementsByTagName("reservation");

            for (int j = 0; j < reservationNodes.getLength(); j++){

                Element currReservationElement = (Element) reservationNodes.item(j);

                String[] currStartTimeParts = currReservationElement.getElementsByTagName("startTime").item(0).getTextContent().split(":");


                if (currReservationElement.getElementsByTagName("user").item(0).getTextContent().equalsIgnoreCase(username) && compareStartTime(currStartTimeParts[0], reservationInformation.get(0))){
                    reservationInformation.set(0, currReservationElement.getParentNode().getAttributes().item(0).getTextContent());
                    reservationInformation.set(1, currReservationElement.getElementsByTagName("startTime").item(0).getTextContent());
                    reservationInformation.set(2, currReservationElement.getElementsByTagName("endTime").item(0).getTextContent());
                    reservationInformation.set(3, currReservationElement.getElementsByTagName("user").item(0).getTextContent());
                }

            }
        }

        return reservationInformation;
    }

    /**
     * Will return true if the first startTime is less than the second startTime
     * @param st1
     * @param st2
     * @return
     */
    private boolean compareStartTime(String st1, String st2){
        String[] st1Parts = st1.split(":");
        String[] st2Parts = st2.split(":");

        if (Integer.parseInt(st1Parts[0]) < Integer.parseInt(st2Parts[0])){
            return true;
        }

        return false;
    }



    public static void main(String[] args) {
        ReservationParser parser = new ReservationParser();
        List<ParkingSpot> parkingSpotList = parser.getParkingInformation();
        System.out.println(parkingSpotList);
        System.out.println(parser.countBookings());

        for (int x = 0 ; x < parkingSpotList.size(); x++) {
            System.out.println(parkingSpotList.get(x));
        }

        System.out.println(parser.computeDuration("15:00","20:00"));
    }
}
