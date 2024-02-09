package client.model.application_pages;

import client.model.LiveDateTime;

import javax.swing.*;
import java.util.Calendar;

/**
 * Template for ReservationPageModel object.
 * The ReservationPageModel contains the different models presented in the reservation page.
 */
public class ReservationPageModel {
    /**
     * The first name of the user
     */
    private String userFirstName;
    /**
     * The formatter for the current date and time.
     */
    private LiveDateTime liveDateTime = new LiveDateTime();
    /**
     * The current date.
     */
    private String date;
    /**
     * The date, day, and time now.
     */
    private String time;
    /**
     * The vehicles of the user.
     */
    private String[] vehicles;

    public String getTime() {
        return liveDateTime.getTime();
    }
}
