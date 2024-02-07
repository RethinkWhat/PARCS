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

    public Server(SocketAddress socketAddress) throws IOException {
        server = new ServerSocket();
        server.bind(socketAddress);
        userParser = new UserParser();
    }

    public boolean validateAccount(String username, String password) {
        Map<String, String> accounts = userParser.getUserLoginCredentials();
        String associatedPass = accounts.getOrDefault(username, "");
        return password.equals(associatedPass);
    }

    public ServerSocket getServer() {
        return server;
    }


    public static void main(String[] args) {
        SocketAddress address = new InetSocketAddress(2020);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        try {
            Server server = new Server(address);

            while (true) {
                Socket clientSocket = server.getServer().accept();
                new Thread(new ClientHandler(server, clientSocket)).start();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
