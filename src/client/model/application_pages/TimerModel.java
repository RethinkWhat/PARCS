package client.model.application_pages;

import client.model.Client;
import client.view.application_pages.Timer;

public class TimerModel {
    /**
     * The connection of the client to the server.
     */
    private Client client;
    /**
     * The location of the parking area.
     */
    private String parkingArea = "SLU Maryheights Campus";
    /**
     * The type of parking (motorcycle or car).
     */
    private String parkingType;
    /**
     * The specified parked vehicle of the user.
     */
    private String vehicle;
    /**
     * The chosen parking slot of the user.
     */
    private String parkingSlot;
    /**
     * The date of parking.
     */
    private String date;
    /**
     * The duration of parking in hours.
     */
    private int duration;
    /**
     * The check-in time of the user in the parking slot.
     */
    private String timeIn;
    /**
     * The check-out time of the user in the parking slot.
     */
    private String timeOut;

    /**
     * Constructs a model of timer with a specified client.
     * @param client The specified client.
     */
    public TimerModel(Client client) {
        this.client = client;
    }


}
