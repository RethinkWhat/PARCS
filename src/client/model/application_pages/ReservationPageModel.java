package client.model.application_pages;

import client.model.Client;
import client.model.LiveDateTime;


/**
 * Template for ReservationPageModel object.
 * The ReservationPageModel contains the different models presented in the reservation page.
 */
public class ReservationPageModel {
    /**
     * The connection of the client to the server.
     */
    private Client client;
    /**
     * The formatter for the current date and time.
     */
    private  LiveDateTime liveDateTime = new LiveDateTime();
    /**
     * The number of currently available car parking slots
     */
    private int availCarSlots;
    /**
     * The number of currently available motorcycle parking slots.
     */
    private int availMotorSlots;
    /**
     * The number of current bookings the user has.
     */
    private int totalBookings;
    /**
     * The cars of the user.
     */
    private String[] cars;
    /**
     * The motorcycles of the user.
     */
    private String[] motorcycles;

    private String[] reservationTime = {"Select Time:", "6:00", "7:00", "8:00", "9:00",
            "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};

    /**
     * Constructs a ReservationPageModel with a specified client.
     * @param client The specified client.
     */
    public ReservationPageModel(Client client) {
        this.client = client;
    }

    /**
     * Retrieves the current live date and time.
     * @return The current date and time.
     */
    public String getTime() {
        return liveDateTime.getTime();
    }

    /**
     * Retrieves the full name of the user to be displayed in the ReservationPage.
     * @return The full name of the user.
     */
    public String getFullName() {
        client.openSocket();
        client.writeString("reservation");
        client.writeString(client.getUsername());
        String name = client.readString();
        client.closeSocket();
        return name;
    }

    public String[] getReservationTime() {
        return reservationTime;
    }

    public String[] getAvailableTime(String parkingIdentifier) {
        String[] carBookings = getCarBookings(parkingIdentifier);

        // TODO: Math of reservations
        String[] reservations = new String[5];


        return reservations;
    }

    public String[] getCarBookings(String parkingIdentifier) {
        client.openSocket();
        client.writeString("spotInfo");

        String statement = (String) client.readObject();
        System.out.println("READ LINE: " + statement);
      //  client.closeObjectSocket();
        return null;
    }

    public int getAvailCarSlots() {
        // get output from server
        return availCarSlots;
    }

    public int getAvailMotorSlots() {
        // get output from server
        return availMotorSlots;
    }

    public int getTotalBookings() {
        // get output from server
        return totalBookings;
    }

    // Motorcycles and cars are separated. This is to cater when the user clicks on
    // the parking spot. If the selected parking spot is for cars only, the user can only select from
    // their list of cars. Else, motorcycle list.
    public String[] getCars() {
        // get output from server
        return cars;
    }

    public String[] getMotorcycles() {
        // get output from server
        return motorcycles;
    }
}
