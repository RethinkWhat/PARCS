package server.model;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ReservationParser {


    DocumentBuilder builder;
    Document document;

    final File reservationsFile = new File("src/server/res/reservations.xml");

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
    public ParkingSpot getParkingInformation() {

        // Set the file to be read
        getReservationsFile();

        ParkingSpot parkingSpot;


        NodeList nodeList = document.getElementsByTagName("parkingSpot");
        for (int x = 0 ; x < nodeList.getLength(); x++) {
            Node currNode = nodeList.item(x);

            String parkingIdentifier = currNode.getAttributes().item(0).getTextContent();

            // Create a reservationsList associated with the parkingIdentifier
            List<Reservations> reservationsList = new ArrayList<>();

            // StartTime, EndTime, User
            NodeList infoPerIdentifier = currNode.getChildNodes();

            for (int y =0; y <infoPerIdentifier.getLength() ; y++) {
                Node reservationNode = infoPerIdentifier.item(y);
                if (reservationNode.getNodeType() == Node.ELEMENT_NODE) {

                    System.out.println("TEXT CONTENT: " + reservationNode.getTextContent());
                    NodeList reservationInfo = reservationNode.getChildNodes();

                    ArrayList<String> reservationParticulars = new ArrayList<>();
                    for (int z = 0; z < reservationInfo.getLength(); z++) {
                        reservationParticulars.add(reservationInfo.item(z).getTextContent());
                    }

                    String start[] =  reservationParticulars.get(0).split(":");
                    String end[] =  reservationParticulars.get(1).split(":");

                    Time startTime = new Time(Integer.valueOf(start[0]), Integer.valueOf(start[1]), Integer.valueOf(start[2]));
                    Time endTime = new Time(Integer.valueOf(end[0]), Integer.valueOf(end[1]), Integer.valueOf(end[2]));



                    String[] reservationDate = reservationNode.getAttributes().item(0).getTextContent().split("/");
                    Date dateFromFile = new Date(
                            Integer.valueOf(reservationDate[0]),
                            Integer.valueOf(reservationDate[1]),
                            Integer.valueOf(reservationDate[2])
                    );
                    boolean dateExists = false;
                    for (Reservations reservation : reservationsList) {
                        if (reservation.getDate() == dateFromFile) {
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
                    }

                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ReservationParser parser = new ReservationParser();
        parser.getParkingInformation();
    }


}
