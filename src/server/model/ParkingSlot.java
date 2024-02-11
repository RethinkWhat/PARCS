package server.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingSlot {

    /**
     * The identifier and means to reference the parking spot
     */
    private String identifier;

    // A list to hold the different dates of reservations
    private List<Reservations> reservationsList;

    public ParkingSlot(String identifier, List<Reservations> reservationsList) {
        this.identifier = identifier;
        this.reservationsList = reservationsList;
    }

    public ParkingSlot(String identifier) {
        this.identifier = identifier;
        this.reservationsList = new ArrayList<>();
    }

    /**
     * Method to get the identifier of a parking spot
     * @return String
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Method to set the identifier of a parking spot
     * @param identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Method to get the reservationsList
     * @return List<Reservations
     */
    public List<Reservations> getReservationsList() {
        return reservationsList;
    }

    /**
     * Method to set the reservations List
     * @param reservationsList : reservationsList
     */
    public void setReservationsList(List<Reservations> reservationsList) {
        this.reservationsList = reservationsList;
    }
}
