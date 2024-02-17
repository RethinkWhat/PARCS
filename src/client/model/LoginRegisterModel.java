package client.model;

import client.controller.ApplicationController;
import client.controller.VehicleAdderController;
import client.view.ApplicationView;
import client.view.VehicleAdderView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Template for LoginRegisterModel.
 * TODO: Documentation (describe this class)
 */
public class LoginRegisterModel {

    private Client client;

    private String errorMessage;

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
    public static boolean verifySignupPassword(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }

    /**
     * Encrypts a specified plain text password using the SHA256 algorithm.
     * @param rawPassword The specified password in plain text.
     * @return The encrypted password.
     */
    public static String encryptPassword(String rawPassword) {
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
        System.out.println("login register model validation attempting");
        client.openSocket();
        System.out.println("socket open");
        client.writeString("login");
        client.writeString(username);
        client.writeString(password);

        String validationMessage = client.readString();
        System.out.println(validationMessage);
        boolean validated =  validationMessage.equals("true");

        if (!validated) {
            if (validationMessage.equals("userExists"))
                errorMessage = "Account already logged in.";
            else
                errorMessage = "Wrong credentials or the account does not exist. Try again.";
        }

        if (validated) {
           // client.writeString("disconnect");
            client.setUsername(username);
            new ApplicationController(new ApplicationView(), new ApplicationModel(client));
        }
        client.closeSocket();

        return validated;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean createAccount(String firstName, String lastName,
                                 String username, String phoneNumber, String password) {

        client.openSocket();

        // Tell clientHandler client on sign up
        client.writeString("signUp");

        // Write username and validate if username already in use
        client.writeString(username);

        // Read if username already taken
        boolean uniqueUsername = client.readString().equals("true");

        if (!uniqueUsername) {
            client.writeString(encryptPassword(password));
            client.writeString(lastName);
            client.writeString(firstName);
            client.writeString(phoneNumber);
        }

        boolean signUpSuccess =  client.readString().equals("true");

        if (signUpSuccess) {
            //client.writeString("disconnect");
            client.setUsername(username);
            System.out.println("sign up success");
            new VehicleAdderController(new VehicleAdderView(), new VehicleAdderModel(client));
        }
        client.closeSocket();
        return signUpSuccess;

    }


}
