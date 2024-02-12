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

    final File reservationsFile = new File("src/server/res/reservations.xml");

    DateTime dateTimeFormatter = new DateTime();

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

        // Set the file to be read
        getReservationsFile();

        List<ParkingSpot> parkingSpotList = new ArrayList<>();

        NodeList nodeList = document.getElementsByTagName("parkingSpot");

        for (int x = 0 ; x < nodeList.getLength(); x++) {
            Node currNode = nodeList.item(x);


            String parkingIdentifier = currNode.getAttributes().item(0).getTextContent();

            ParkingSpot parkingSpot = new ParkingSpot(parkingIdentifier);

            // Create a reservationsList associated with the parkingIdentifier
            List<Reservations> reservationsList = new ArrayList<>();

            // StartTime, EndTime, User
            NodeList infoPerIdentifier = currNode.getChildNodes();

            for (int y =0; y <infoPerIdentifier.getLength() ; y++) {
                Node reservationNode = infoPerIdentifier.item(y);
                if (reservationNode.getNodeType() == Node.ELEMENT_NODE) {

                    NodeList reservationInfo = reservationNode.getChildNodes();

                    ArrayList<String> reservationParticulars = new ArrayList<>();
                    for (int z = 0; z < reservationInfo.getLength(); z++) {
                        Node reservationChildNode = reservationInfo.item(z);
                        if (reservationChildNode.getNodeType() == Node.ELEMENT_NODE)
                            reservationParticulars.add(reservationInfo.item(z).getTextContent());
                    }
                    String startTime = dateTimeFormatter.createTime(reservationParticulars.get(0));
                    String endTime = dateTimeFormatter.createTime(reservationParticulars.get(1));

                    String dateFromFile = dateTimeFormatter.createDate(reservationNode.getAttributes().item(0).getTextContent());

                    boolean dateExists = false;
                    for (Reservations reservation : reservationsList) {
                        if (reservation.getDate().equals(dateFromFile)) {
                            reservation.addReservation(startTime,endTime,reservationParticulars.get(2));
                            dateExists = true;
                        }
                    }
                    if (!dateExists) {
                        // Create a reservation listing with the date
                        HashMap<TimeRange, String> timeRangeStringHashMap = new HashMap<>();
                        timeRangeStringHashMap.put(new TimeRange(startTime,endTime),reservationParticulars.get(2));
                        Reservations newReservation = new Reservations(dateFromFile, timeRangeStringHashMap);
                        reservationsList.add(newReservation);
                        //System.out.println(newReservation);
                    }
                }
            }
            parkingSpot.addReservationsList(reservationsList);
            parkingSpotList.add(parkingSpot);
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

    /**
     * This method will return the reservation details of a specified user
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




    public static void main(String[] args) {
        ReservationParser parser = new ReservationParser();
        List<ParkingSpot> parkingSpotList = parser.getParkingInformation();

        for (int x = 0 ; x < parkingSpotList.size(); x++) {
            System.out.println(parkingSpotList.get(x));
        }

        System.out.println("C1 Parking Slot: " + parser.getParkingSlotInformationByIdentifier("C1").getReservationsList().toString());
        System.out.println("C2 Parking Slot: " + parser.getParkingSlotInformationByIdentifier("C2").getReservationsList().toString());

        System.out.println("ramon: " + parser.getUserReservations("ramon").toString());
    }


}
