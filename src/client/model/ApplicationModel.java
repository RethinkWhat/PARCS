package client.model;

public class ApplicationModel {
    /**
     * The first name of the user displayed in the home page.
     */
    private String userFirstName;

    /**
     * Constructs an ApplicationModel with a specified first name of the user.
     * @param userFirstName The specified first name of the user.
     */
    public ApplicationModel(String userFirstName) {
        this.userFirstName = userFirstName;
    }
}
