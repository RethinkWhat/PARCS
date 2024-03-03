package client;

import client.model.Client;

public class Main {

    /**
     * The main method to start the client application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Thread clientsThread = new Thread(() ->{
            Client client = new Client();

            client.startGUI();
        });
        clientsThread.start();
    }
}
