package client.model.application_pages;

import client.model.Client;
import client.model.LiveDateTime;

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

    private Client client;
    /**
     * The formatter for the current date and time.
     */
    private LiveDateTime liveDateTime;
    /**
     * The date, day, and time now.
     */
    private String time;
    /**
     * The vehicles of the user.
     */
    private String[] vehicles;

    public ReservationPageModel(Client client) {
        this.client = client;
    }

    // This method causes the client to be unresponsive and crash. The suspected cause is similar to the
    // server crashing when turned on / off in the admin side.
    /*
    public void setTime() {
        while (true) {
            liveDateTime = new LiveDateTime();
            time = liveDateTime.getDateFormat().format(Calendar.getInstance().getTime()) + " | " +
                    liveDateTime.getDayFormat().format(Calendar.getInstance().getTime()) + " " +
                    liveDateTime.getTimeFormat().format(Calendar.getInstance().getTime());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
     */


    public String getTime() {
        return time;
    }

    public String getFullName() {
        client.openSocket();
        client.writeString("reservation");
        System.out.println("attempting socket connection");
        String name = client.readString();
        System.out.println(name);
        client.closeSocket();

        return name;
    }
}
