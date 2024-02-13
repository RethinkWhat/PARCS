package client.model.application_pages;

import client.model.Client;
import client.view.application_pages.HistoryPageView;

public class HistoryPageModel {
    /**
     * The connection of the client to the server. (necessary?)
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
    //what is the class for hour?

    /**
     * The duration of parking in hours.
     */
    private int duration;

    /**
     * Constructs a model of HistoryPage with a specified client.
     * @param client The specified client.
     */
    public HistoryPageModel(Client client) {
        this.client = client;
    }
}