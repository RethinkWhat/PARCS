package client.model;

import client.model.application_pages.ReservationPageModel;

/**
 * Template for object ApplicationModel.
 * The application model contains the different data to be displayed in the client application domain.
 */
public class ApplicationModel {
    /**
     * The location of the user in the application domain
     * 1. Home
     * 2. Ticket
     * 3. Account
     */
    private String pageLocation;
    /**
     * The model for the reservation page.
     */
    private ReservationPageModel reservationPageModel = new ReservationPageModel();

    /**
     * Retrieves the current model of the reservation page.
     * @return The current model of the reservation page.
     */
    public ReservationPageModel getReservationPageModel() {
        return reservationPageModel;
    }
}
