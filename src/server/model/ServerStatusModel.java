package server.model;

import java.io.IOException;

/**
 * The model class for managing server status information.
 */
public class ServerStatusModel {

    /**
     * The number of bookings on the server.
     */
    private int numberOfBookings;

    /**
     * The server instance associated with this model.
     */
    private Server server;

    /** The port address for the application */
    final int address = 2020;

    /**
     * Constructs a ServerStatusModel object.
     * Initializes the server instance and retrieves the number of bookings from the server.
     */
    public ServerStatusModel() {
        try {
            this.server = new Server(address);
        } catch (IOException io) {
            io.printStackTrace();
        }
        numberOfBookings = server.getNumberOfBookings();
    }

    /**
     * It retrieves the number of bookings on the server.
     * @return The number of bookings.
     */
    public int getNumberOfBookings() {
        return numberOfBookings;
    }

    /**
     * It sets the number of bookings on the server.
     * @param numberOfBookings The number of bookings to set.
     */
    public void setNumberOfBookings(int numberOfBookings) {
        this.numberOfBookings = numberOfBookings;
    }

    /**
     * It retrieves the server instance associated with this model.
     * @return The server instance.
     */
    public Server getServer() {
        return server;
    }

    /**
     * It sets the server instance associated with this model.
     * @param server The server instance to set.
     */
    public void setServer(Server server) {
        this.server = server;
    }
}
