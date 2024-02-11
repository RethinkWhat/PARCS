package server.model;

import java.util.List;

public class ParkingSlot {

    /**
     * The identifier and means to reference the parking spot
     */
    private String identifier;

    // A list to hold the different dates of reservations
    private List<Reservations> reservationsList;
}
