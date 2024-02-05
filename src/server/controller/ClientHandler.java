package server.controller;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    Server server;
    Socket client;

    boolean authenticateLogin = false;
    public ClientHandler(Server server, Socket client) {
        this.client = client;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            while (!authenticateLogin) {
                String username = reader.readLine();
                String password = reader.readLine();

                authenticateLogin = server.validateAccount(username, password);
                if (authenticateLogin)
                    writer.println("true");
                else
                    writer.println("false");
                writer.flush();
            }



        } catch (IOException ex) {

        }
    }
}
