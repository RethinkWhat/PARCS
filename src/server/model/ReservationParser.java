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

    DateTime dateTime = new DateTime();

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
        System.out.println(parkingSpotList);
        for (int x = 0 ; x < parkingSpotList.size(); x++) {
            List<Reservations> reservations = parkingSpotList.get(x).getReservationsList();
            System.out.println(reservations);



//            for (int y = 0; y < parkingSpotList.get(x).getReservationsList().size(); y++) {
//
//                List<Reservations> reservations = parkingSpotList.get(x).getReservationsList();
//                HashMap<TimeRange, String> map = reservations.get(y).getTimeAndUserMap();
//                System.out.println(reservations);
//                System.out.println("");
//                for (int z = 0; z < reservations.size(); z++) {
//                        if (reservations.get(z).getDate().compareTo(dateTime.getDateTime()) > 0) {
//                            size += 1;
//                        }
//                }
//            }
        }
        return size;
    }

    public Map<String, Reservations> getUserReservations(String userName){
        /**
         * Key: Parking Slot identifier
         * Value: Reservations
         */
        Map<String, Reservations> userReservations = new HashMap<>();

        return userReservations;
    }



    public static void main(String[] args) {
        ReservationParser parser = new ReservationParser();
        List<ParkingSpot> parkingSpotList = parser.getParkingInformation();
        System.out.println(parkingSpotList);

        for (int x = 0 ; x < parkingSpotList.size(); x++) {
            System.out.println(parkingSpotList.get(x));
        }




      //  System.out.println("C1 Parking Slot: " + parser.getParkingSlotInformationByIdentifier("C1").getReservationsList().toString());
      //  System.out.println("C2 Parking Slot: " + parser.getParkingSlotInformationByIdentifier("C2").getReservationsList().toString());
    }


}
