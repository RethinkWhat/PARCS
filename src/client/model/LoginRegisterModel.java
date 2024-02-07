package client.model;

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
     *
     */
    public void createAccount(String lastName, String firstName) {
        // creates a new user
        // XML processing here
    }

    public boolean verifySignupPassword(String password, String passwordConfirmation) {
        return password == passwordConfirmation;
    }

    public static String encryptPassword(String rawPassword) {
        String encrypted = "";
        return encrypted;
    }

    public static String decryptPassword(String encryptedPassword) {
        String decrypted = "";
        return decrypted;
    }

    /**
     * Verifies the given user credentials by sending the username and password to the server.
     * Returns true if the given credentials are mapped to an existing account. Returns false if the account
     * does not exist or the given credentials are incorrect.
     * @param username The specified username.
     * @param password The specified password.
     * @return The success state.
     */
    public boolean validateAccount(String username, String password) {
        client.writeString(username);
        client.writeString(password);
        return client.readString().equals("true");
        // TODO: if account exists but wrong credentials, if account does not exist.
    }
}
