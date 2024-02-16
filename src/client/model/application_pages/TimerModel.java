package client.model.application_pages;

import client.model.Client;
import client.view.application_pages.Timer;

import java.util.List;

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

    private List<String> reservationInfo;

    private boolean startTimer;

    private boolean isTimeStarted = false;

    /**
     * Constructs a model of timer with a specified client.
     * @param client The specified client.
     */
    public TimerModel(Client client) {
        this.client = client;
        getReservationInfo();
    }

    public void getReservationInfo() {
        client.openSocket();

        client.writeString("ticket");

        client.writeString(client.getUsername());

        reservationInfo = (List<String>) client.readObject();

        duration = Integer.valueOf(client.readString());

        parkingSlot = reservationInfo.get(0);
        timeIn = reservationInfo.get(1);
        timeOut = reservationInfo.get(2);
        date = reservationInfo.get(3);

        if (parkingSlot.contains("C"))
            parkingType = "Car";
        else
            parkingType = "Motorcycle";
        client.closeSocket();

        if (date.equals(client.getDate())) {
            startTimer = true;
        }
    }

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public String getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(String parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isStartTimer() {
        return startTimer;
    }

    public void setStartTimer(boolean startTimer) {
        this.startTimer = startTimer;
    }

    public boolean isTimeStarted() {
        return isTimeStarted;
    }

    public void setTimeStarted(boolean timeStarted) {
        isTimeStarted = timeStarted;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
