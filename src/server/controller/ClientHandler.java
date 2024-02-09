package server.controller;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    Server server;
    Socket client;
    BufferedReader reader;
    PrintWriter writer;

    boolean authenticateLogin = false;

    boolean userLoggedIn = true;
    public ClientHandler(Server server, Socket client) {
        this.client = client;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            String username = reader.readLine();
            String password = reader.readLine();

            if (username != null && password != null)
                authenticateLogin = server.validateAccount(username, password);

            if (authenticateLogin)
                writer.println("true");
            else
                writer.println("false");
            writer.flush();
            reader.close();
            writer.close();


        } catch (IOException ex) {

        }
    }
}
