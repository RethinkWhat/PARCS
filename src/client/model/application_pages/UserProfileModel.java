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
}
