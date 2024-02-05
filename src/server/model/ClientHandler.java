package server.model;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    Socket client;
    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

        } catch (IOException ex) {

        }
    }
}
