package client.model.account;

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

    private String username;

    /**
     * Constructs a LoginRegisterModel with null values.
     */
    public LoginRegisterModel() {
        status = false;
    }

    protected void createAccount() {
        // creates a new user
        // XML processing here
    }

    protected void validateAccount() {
        // sends login info to server
    }
}
