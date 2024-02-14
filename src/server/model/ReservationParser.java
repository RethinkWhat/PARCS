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
import java.io.File;
import java.io.IOException;

import java.sql.Time;
import java.util.*;

public class ReservationParser {


    DocumentBuilder builder;
    Document document;

    ArrayList<String> timeArray = new ArrayList<>();

    DateTime dateTime = new DateTime();

    HashMap<String, Integer> timeHashMap;

    final File reservationsFile = new File("src/server/res/reservations.xml");

    DateTime dateTimeFormatter = new DateTime();

    public void populateTime() {
        String[] time =  {"6:00", "7:00", "8:00", "9:00",
                "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
        for (String timeItem : time)
            timeArray.add(timeItem);

        timeHashMap = new HashMap<>();

        for (int x =6; x <=18; x++) {
            timeHashMap.put((x+":00"),x);
        }
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

    public List<String> availableTime(String date, String duration, String identifier) {
        populateTime();
        DateTime dateTime = new DateTime();
        int durationAsInt = Integer.parseInt(duration);
        ArrayList<String> availableTime = new ArrayList<>();
        List<TimeRange> bookedTimeRange = getParkingSpotAvailability(date, identifier);

        // List<TimeRange> bookedTimeRange = getParkingSpotAvailability("02/14/24", "C1");

        System.out.println("BOOKED TIME: " + bookedTimeRange);


        ArrayList<String> allBookings = new ArrayList<>();
        ArrayList<String> allTime = timeArray;
        ArrayList<String> toReturnTime = new ArrayList<>();
        for (TimeRange timeRange : bookedTimeRange) {

            // get the start time, the end time, and all the time in between
            List<String> timeList = timeRange.getStartToEndTime();

            // get all the time in the booking except the last where it ends
            for (int x = 0; x < timeList.size() - 1; x++) {
                allBookings.add(timeList.get(x));
            }
        }

        for (int x = 0; x < allTime.size(); x++) {
            if (!(allBookings.contains(allTime.get(x))))
                toReturnTime.add(allTime.get(x));
        }
        return toReturnTime;
    }


    public static void main(String[] args) {
        ReservationParser parser = new ReservationParser();
        List<ParkingSpot> parkingSpotList = parser.getParkingInformation();
        System.out.println(parkingSpotList);
        System.out.println(parser.countBookings());

        for (int x = 0 ; x < parkingSpotList.size(); x++) {
            System.out.println(parkingSpotList.get(x));
        }
    }
}
