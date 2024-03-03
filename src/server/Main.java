package server;

import server.controller.AdminApplicationController;
import server.view.AdminApplicationView;

public class Main {
    /**
     * Main entry point of the program.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new AdminApplicationController(new AdminApplicationView());
    }
}
