package server.controller;


import server.model.UserParser;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{
    private ServerSocket server;

    private UserParser userParser;

    public Server() {
        int port = 2020;
        try {
            this.server = new ServerSocket(port);
            userParser = new UserParser();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean validateAccount(String username, String password) {
        Map<String, String> accounts = userParser.getUserLoginCredentials();
        String associatedPass = accounts.getOrDefault(username, "");
        return password.equals(associatedPass);
    }

    public ServerSocket getServer() {
        return server;
    }

    public void startServer() {
        while (true) {
            try {
                Socket clientSocket = server.accept();
                new Thread(new ClientHandler(this, clientSocket)).start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void closeServer() {
        try {
            server.close();
        } catch (IOException ez) {
            ez.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Server server = new Server();
    }

}
