package server.model;

import server.controller.Server;

import java.io.IOException;

public class ServerStatusModel {

    /**
     * Variable to hold the numberOfBookings
     */
    private int numberOfBookings;

    private Server server;


    /** The port address for the application */

    final int address = 2020;

    public ServerStatusModel() {
        try {
            this.server = new Server(address);
        } catch (IOException io) {
            io.printStackTrace();
        }
        numberOfBookings = server.getNumberOfBookings();
    }

    public int getNumberOfBookings() {
        return numberOfBookings;
    }

    public void setNumberOfBookings(int numberOfBookings) {
        this.numberOfBookings = numberOfBookings;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
