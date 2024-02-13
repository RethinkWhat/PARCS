package client.model.application_pages;

import client.model.Client;

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getContactNo() {
        return contactNo;
    }

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

    // TODO: Create method to edit password
    public boolean editPassword(String password) {
        client.openSocket();
        client.writeString("editPassword");

        client.writeString(client.getUsername());
        client.writeString(password);

        client.closeSocket();
        return true;
    }

    public Client getClient() {
        return client;
    }
}
