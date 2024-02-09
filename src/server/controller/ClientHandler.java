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
        login();
    }

    public void login() {
        String username = read();
        String password = read();

        if (username != null && password != null)
            authenticateLogin = server.validateAccount(username, password);

        if (authenticateLogin)
            write("true");
        else
            write("false");

    }


    public void write(String message ) {
        try {
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            writer.println(message);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String read() {
        String line = null;
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            line = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return  line;
    }
}
