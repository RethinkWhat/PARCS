package client.model.application_pages;

import client.model.Client;

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
     * The cars of the user.
     */
    private String[] cars;
    /**
     * The motors of the user.
     */
    private String[] motors;
    /**
     * The mapping of the user to their vehicles.
     */
    private HashMap<String, List<String>> vehicles;

    /**
     * Constructs a model of UserProfile with a specified client.
     * @param client The specified client.
     */
    public UserProfileModel(Client client) {
        this.client = client;
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
     * Retrieves the current first name of the user.
     * @return The current first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the current last name of the user.
     * @return The current last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieves the current username of the user.
     * @return The current username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the current password of the user.
     * @return The current password (encrypted).
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the current contact number of the user.
     * @return The current contact number.
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * TODO: Documentation
     * @param firstName
     * @param lastName
     * @param contactNo
     * @return
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
     * @param type The specified type.
     * @param model The specified model.
     * @param plateNumber The specified plate number.
     * @return True if the edit was successful. False if otherwise.
     */
    public boolean editVehicleInfo(String type, String model, String plateNumber) {
        client.openSocket();;
        client.writeString("editInfo");

        // TODO: Handle equal values
        client.writeString(username + ",vehicle," +
                type + "," + model + "," + plateNumber);

        client.writeString("complete");

        boolean editConfirmed = client.readString().equals("true");
        client.closeSocket();

        return editConfirmed;
    }

    /**
     * Changes the password of the user with a specified new password.
     * The client sends the information to the server and parses it.
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
     * Retrieves the current client.
     * @return The current client.
     */
    public Client getClient() {
        return client;
    }
}
