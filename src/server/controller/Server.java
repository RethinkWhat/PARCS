package server.controller;


import server.model.UserParser;
import server.model.Vehicle;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
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

    public boolean checkAdminStatus(String username) {
        return userParser.isAdmin(username);
    }

    public String getUserFullName(String username) {
        return userParser.getUserFullName(username);
    }

    public boolean checkUsernameExists(String username) {
        return userParser.doesUsernameExist(username);
    }

    public boolean createAccount(String username, String type, String password, String lastName, String firstName,
                           String phoneNumber, ArrayList<Vehicle> vehicles) {
        userParser.createUser(username,type,password,lastName,firstName,phoneNumber,vehicles);
        return true;
    }

    public boolean addVehicle(String username, Vehicle vehicle) {
        userParser.addVehicle(username, vehicle);
        return true;
    }

    public String getUserCredentials(String username) {
        return userParser.getFullUserInfo(username);
    }

    public ServerSocket getServer() {
        return server;
    }

    public boolean editInfo(String username, String tag, String newInfo) {
        try {
            userParser.editUserInfo(username, tag, newInfo);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void runServer() {
        SocketAddress address = new InetSocketAddress(2020);
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


    public static void main(String[] args) {
        SocketAddress address = new InetSocketAddress(2020);
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


