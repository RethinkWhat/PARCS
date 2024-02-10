package client.model;

import client.controller.ApplicationController;
import client.controller.VehicleAdderController;
import client.view.ApplicationView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Template for LoginRegisterModel.
 * TODO: Documentation (describe this class)
 */
public class LoginRegisterModel {
    /**
     * The status of the user.
     * True if logged in, false if otherwise.
     */
    private Client client;

    /**
     * Constructs a LoginRegisterModel with null values.
     */
    public LoginRegisterModel(Client client) {
        this.client = client;
    }

    /**
     * Verifies if the user input of password matches the password confirmation in the signup page.
     * @param password Specified user input of password.
     * @param passwordConfirmation Specified user input of password confirmation.
     * @return True if the password matches. False if otherwise.
     */
    public boolean verifySignupPassword(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }

    /**
     * Encrypts a specified plain text password using the SHA256 algorithm.
     * @param rawPassword The specified password in plain text.
     * @return The encrypted password.
     */
    public String encryptPassword(String rawPassword) {
        String encrypted = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            encrypted = Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;
    }

    /**
     * Verifies the given user credentials by sending the username and password to the server.
     * Returns true if the given credentials are mapped to an existing account. Returns false if the account
     * does not exist or the given credentials are incorrect.
     * @param username The specified username.
     * @param password The specified encrypted password.
     * @return The success state.
     */
    public boolean validateAccount(String username, String password) {
        client.openSocket();
        client.writeString("login");
        client.writeString(username);
        client.writeString(password);
        boolean validated =  client.readString().equals("true");

        if (validated) {
            client.writeString("disconnect");
            client.closeSocket();
            client.setUsername(username);
            new ApplicationController(new ApplicationView(), new ApplicationModel(client));
        }
        return validated;
    }

    public boolean verifyUsername(String firstName, String lastName,
                                 String username, String phoneNumber, String password,
                                 String vehicleType, String vehicleModel, String licensePlate) {

        client.openSocket();

        // Tell clientHandler client on sign up
        client.writeString("signUp");

        // Write username and validate if username already in use
        client.writeString(username);

        // Read if username already in user
        boolean uniqueUsername = client.readString().equals("true");

        if (uniqueUsername) {
            client.writeString("user");
            client.writeString(password);
            client.writeString(lastName);
            client.writeString(firstName);
            client.writeString(phoneNumber);
            client.writeString(vehicleType);
            client.writeString(vehicleModel);
            client.writeString(licensePlate);
        }

        boolean signUpSuccess =  client.readString().equals("true");

        if (signUpSuccess) {
            client.writeString("disconnect");
            client.closeSocket();
            client.setUsername(username);
            new ApplicationController(new ApplicationView(), new ApplicationModel(client));
        }
        return signUpSuccess;
    }

    /*
    public boolean isAdmin(){
        client.readString();
        boolean validated =  client.readString().equals("admin");
        client.closeSocket();
        return validated;
    }

     */

}
