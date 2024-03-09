package client.model.application_pages;

import client.model.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The UserProfileModel contains the data.
 */
public class UserProfileModel {
    /**
     * The connection of the client to the server
     */
    private Client client;
    /**
     * The first name of the user.
     */
    private String firstName;
    /**
     * The last name of the user.
     */
    private String lastName;
    /**
     * The username of the user.
     */
    private String username;
    /**
     * The encrypted password of the user.
     */
    private String password;
    /**
     * The contact number of the user.
     */
    private String contactNo;
    /**
     * The mapping of the user to their vehicles.
     */
    private HashMap<String, List<String>> vehicles;
    /**
     * The String array list of vehicles, populated by a HashMap.
     */
    private ArrayList<String> vehicleList;


    /** Histroy page variables */
    private int historyPageNo =0;

    private List<List<String>> bookings;

    /**
     * Constructs a model of UserProfile with a specified client.
     *
     * @param client The specified client.
     */
    public UserProfileModel(Client client) {
        this.client = client;
    }

    public List<List<String>> getBookings() {
        return bookings;
    }

    /**
     * Retrieves the user's vehicles.
     */
    public void getVehiclesInfo() {
        client.openSocket();
        client.writeString("getVehicles");
        client.writeString(client.getUsername());
        vehicles = new HashMap<>(); //null; //(HashMap<String, List<String>>) client.readObject();

        ArrayList<String> vehicleInfo;

        while (true) {
            String vehicleKey = client.readString();
            vehicleInfo = new ArrayList<>();

            if (vehicleKey.equals("empty") || vehicleKey.equals("complete"))
                break;

            while (true) {
                String vehicleValue = client.readString();
                if (vehicleValue.equals("nextKey")) {
                    break;
                }
                vehicleInfo.add(vehicleValue);
            }
            vehicles.put(vehicleKey, vehicleInfo);
        }

        client.closeSocket();

        vehicleList = new ArrayList();

        for (String vehicle : vehicles.keySet()) {
            vehicleList.add(vehicle + "," + vehicles.get(vehicle).get(0) + "," + vehicles.get(vehicle).get(1));
        }
    }

    /**
     * Retrieves the full credentials (excluding password) of the current user.
     */
    public void getCredentials() {
        client.openSocket();
        client.writeString("account");
        client.writeString(client.getUsername());
        String credentials = client.readString();
        client.closeSocket();

        String[] tokens = credentials.split(",");
        lastName = tokens[0];
        firstName = tokens[1];
        username = tokens[2];
        contactNo = tokens[3];
        password = tokens[4];
    }

    /**
     * Method to handle deleting a user account
     */
    public void deleteAccount() {
        client.openSocket();
        client.writeString("delete");
        client.writeString(client.getUsername());
        client.closeSocket();
    }


    /**
     * Retrieves the current first name of the user.
     *
     * @return The current first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the current last name of the user.
     *
     * @return The current last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieves the current username of the user.
     *
     * @return The current username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the current password of the user.
     *
     * @return The current password (encrypted).
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the current contact number of the user.
     *
     * @return The current contact number.
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * Retrieves the list of vehicles associated with the user.
     *
     * @return The list of vehicles in a formatted string.
     */
    public ArrayList<String> getVehicleList() {
        return vehicleList;
    }

    /**
     * Edits the user's information with the specified first name, last name, and contact number.
     *
     * @param firstName The new first name.
     * @param lastName  The new last name.
     * @param contactNo The new contact number.
     * @return True if the edit was successful. False if otherwise.
     */
    public boolean editUserInformation(String firstName, String lastName, String contactNo) {
        client.openSocket();
        client.writeString("editInfo");

        if (!getFirstName().equalsIgnoreCase(firstName))
            client.writeString(username + ",firstName," + firstName);

        if (!getLastName().equalsIgnoreCase(lastName))
            client.writeString(username + ",lastName," + lastName);

        if (!getContactNo().equalsIgnoreCase(contactNo))
            client.writeString(username + ",phoneNumber," + contactNo);

        client.writeString("complete");

        boolean editConfirmed = client.readString().equals("true");
        client.closeSocket();

        return editConfirmed;
    }

    /**
     * Edits the user's vehicle information with a specified vehicle type, model, and plateNumber.
     *
     * @param plateNumber        The specified plateNumber.
     * @param newInfo       The new vehicle information.
     * @param plateNumber The specified plate number.
     * @return True if the edit was successful. False if otherwise.
     */
    public boolean editVehicleInfo(String plateNumber, String newInfo) {
        client.openSocket();

        client.writeString("editVehicleInfo");

        client.writeString(client.getUsername());
        client.writeString(plateNumber);
        client.writeString(newInfo);

        boolean editConfirmed = client.readString().equals("true");
        client.closeSocket();

        return editConfirmed;
    }

    /**
     * Changes the password of the user with a specified new password.
     * The client sends the information to the server and parses it.
     *
     * @param password The specified new password.
     * @return True if successful. False if otherwise.
     */
    public boolean editPassword(String password) {
        client.openSocket();
        client.writeString("editPassword");

        client.writeString(client.getUsername());
        client.writeString(password);

        client.closeSocket();
        return true;
    }

    /**
     * Retrieves and displays the booking history of the user.
     */
    public void viewHistory() {
        client.openSocket();
        client.writeString("history");
        client.writeString(client.getUsername());

        bookings = new ArrayList<>();
        ArrayList parkingInfo;
        String lineRead;
        while (true) {
            lineRead = client.readString();
            if (lineRead.equals("complete"))
                break;
            parkingInfo = new ArrayList<>();
            parkingInfo.add(lineRead);
            while (true) {
                lineRead = client.readString();
                if (lineRead.equals("nextBooking"))
                    break;
                parkingInfo.add(lineRead);
            }
            bookings.add(parkingInfo);
        }
        client.closeSocket();
    }

    /**
     * Retrieves the current client.
     *
     * @return The current client.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Validates and updates the current index for viewing booking history.
     *
     * @param number The specified number to move forward or backward in history.
     * @return True if the index is valid and updated. False if otherwise.
     */
    public boolean validateEndOrStart(int number) {
        int indexToCheck = historyPageNo + number;
        boolean isValidIndex = indexToCheck >= 0 && indexToCheck < bookings.size();
        if (isValidIndex) {
            historyPageNo += number;
        }
        return isValidIndex;
    }

    /**
     * Retrieves the type of the current booking.
     *
     * @return The type of the current booking.
     */
    public String getType() {
        return bookings.get(historyPageNo).get(0);
    }

    /**
     * Retrieves the parking spot of the current booking.
     *
     * @return The parking spot of the current booking.
     */
    public String getSpot() {
        return bookings.get(historyPageNo).get(1);
    }

    /**
     * Retrieves the check-in time of the current booking.
     *
     * @return The check-in time of the current booking.
     */
    public String getCheckIn() {
        return bookings.get(historyPageNo).get(2);
    }

    /**
     * Retrieves the check-out time of the current booking.
     *
     * @return The check-out time of the current booking.
     */
    public String getCheckOut() {
        return bookings.get(historyPageNo).get(3);
    }

    /**
     * Retrieves the duration of the current booking.
     *
     * @return The duration of the current booking.
     */
    public String getDuration() {
        return bookings.get(historyPageNo).get(4);
    }

}
