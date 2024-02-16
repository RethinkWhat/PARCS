package client.model.application_pages;

import client.model.Client;
import client.model.LiveDateTime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


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
    private String availCarSlots;
    /**
     * The number of currently available motorcycle parking slots.
     */
    private String availMotorSlots;
    /**
     * The number of current bookings the user has.
     */

    private String totalBookings;
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

    private String fullName;

    private Map<String, List<String>> vehicles;

    /**
     * Constructs a ReservationPageModel with a specified client.
     * @param client The specified client.
     */
    public ReservationPageModel(Client client) {
        this.client = client;

        client.openSocket();
        client.writeString("reservation");
        client.writeString(client.getUsername());
        this.fullName = client.readString();

        totalBookings = client.readString();
        System.out.println("total bookings: " + totalBookings);

        vehicles = (Map<String, List<String>>) client.readObject();
        System.out.println(vehicles);

        client.closeSocket();

        ArrayList<String> carsArrayList = new ArrayList();
        ArrayList<String> motorArrayList = new ArrayList();

        for (String vehicle : vehicles.keySet()) {
            if (vehicles.get(vehicle).get(0).equals("car")) {
                carsArrayList.add(vehicles.get(vehicle).get(1));
            }
            else {
                motorArrayList.add(vehicles.get(vehicle).get(1));
            }
        }

        cars = new String[carsArrayList.size()];
        motorcycles = new String[motorArrayList.size()];

        for (int x =0; x < carsArrayList.size(); x++) {
            cars[x] = carsArrayList.get(x);
        }

        for (int x =0; x < motorArrayList.size(); x++) {
            motorcycles[x] = motorArrayList.get(x);
        }
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

        return fullName;
    }

    public String[] getReservationTime() {
        return reservationTime;
    }

    public String[] getAvailableTime(String parkingIdentifier, String duration, String date) {
        client.openSocket();
        client.writeString("spotInfo");

        client.writeString(parkingIdentifier);
        client.writeString(duration);
        client.writeString(date);
        ArrayList<String> listOfTime = (ArrayList<String>) client.readObject();

        String[] arrayString = new String[1];
        if (!listOfTime.isEmpty()) {
            arrayString = new String[listOfTime.size()];
        }
        arrayString[0] = "Select Time:";
        for (int x =1 ; x< listOfTime.size(); x++) {
            arrayString[x] = listOfTime.get(x);
        }
        client.closeSocket();
        return arrayString;
    }

    public boolean attemptBooking(String identifier, String date, String startTime, String duration) {
        client.openSocket();
        client.writeString("book");
        client.writeString(identifier);
        client.writeString(date);
        client.writeString(startTime);
        client.writeString(duration);
        client.writeString(client.getUsername());

        boolean confirmed = client.readString().equals("true");
        return confirmed;
    }

    public String findAvailableSlotOnDay(String date, int carSize, int motorSize) {
        String dates[] = getDateList();
        if (Arrays.stream(dates).toList().contains(date)) {

            for (int x =0; x< carSize; x++) {
                String time[] = getAvailableTime(("C" + (x+1)),"1",date);

                if (time.length > 1)
                    return ("C"+(x+1));
            }
            for (int x =0; x< motorSize; x++) {
                String time[] = getAvailableTime(("M" + (x+1)),"1",date);
                if (time.length >1)
                    return ("M"+(x+1));
            }
        }
        return null;
    }


    public String getAvailCarSlots() {
        return availCarSlots;
    }

    public String getAvailMotorSlots() {
        return availMotorSlots;
    }

    public String getTotalBookings() {
        return totalBookings;
    }

    // Motorcycles and cars are separated. This is to cater when the user clicks on
    // the parking spot. If the selected parking spot is for cars only, the user can only select from
    // their list of cars. Else, motorcycle list.
    public String[] getCars() {

        return cars;
    }

    public String[] getMotorcycles() {
        // get output from server
        return motorcycles;
    }

    public String getDate() {
        return client.getDate();
    }

    public String[] getDateList() {
        String[] dates = new String[4];

        LocalDate dateToday = LocalDate.now();
        LocalDate tomorrow = dateToday.plusDays(1);
        LocalDate dayAfter = tomorrow.plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");

        dates[0] = "Date:";
        dates[1] = dateToday.format(formatter);
        dates[2] = tomorrow.format(formatter);
        dates[3] = dayAfter.format(formatter);
        return dates;
    }

}

