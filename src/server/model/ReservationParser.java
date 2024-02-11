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
import java.util.ArrayList;
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

        // Variable to hold the reservationsList associated with a parking spot
        List<Reservations> reservationsList = new ArrayList<>();

        NodeList nodeList = document.getElementsByTagName("parkingSpot");
        for (int x = 0 ; x < nodeList.getLength(); x++) {
            Node currNode = nodeList.item(x);
            String parkingIdentifier = currNode.getAttributes().item(0).getTextContent();

            Reservations reservations = new Reservations();
            NodeList childNodes = currNode.getChildNodes();

            for (int y = 0; y < childNodes.getLength() ; y++) {
                HashMap<TimeRange, String> timeRangeStringHashMap = new HashMap<>();

            }
        }
        return null;
    }

    public static void main(String[] args) {
        ReservationParser parser = new ReservationParser();
        parser.getParkingInformation();
    }


}
