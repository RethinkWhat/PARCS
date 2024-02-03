package client.model;

import server.model.User;

/**
 * Template for LoginRegisterModel.
 * TODO: Documentation (describe this class)
 */
public class LoginRegisterModel {
    /**
     * The status of the user.
     * True if logged in, false if otherwise.
     */
    private boolean status;
    /**
     * The user of the program -- new or existing.
     */
    private User user;

    /**
     * Constructs a LoginRegisterModel with null values.
     */
    public LoginRegisterModel() {
        status = false;
        user = null;
    }

    protected void createAccount() {
        // creates a new user
        // XML processing here
    }

    protected void validateAccount() {
        // checks if the user exists
        // updates status accordingly
    }
}
