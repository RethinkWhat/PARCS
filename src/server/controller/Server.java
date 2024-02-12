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

public class Server implements Runnable{
    private ServerSocket server;

    private UserParser userParser;

    private int socketAddress;

    private volatile boolean serverRunning;

    // List of users logged in to the system
    List<String> userLog;

    public Server(int socketAddress)throws IOException {
        this.socketAddress = socketAddress;
        userParser = new UserParser();
        userLog = new ArrayList<>();
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

    public boolean serverRunning() {
        return serverRunning;
    }

    public void startServer() {
        try {
            server = new ServerSocket(socketAddress);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setServerRunning(boolean serverRunning) {
        this.serverRunning = serverRunning;
    }

    public void run() {
        while (true) {
            try {
                boolean flag = this.serverRunning();
                while (flag) {
                        Socket clientSocket = server.accept();
                        new Thread(new ClientHandler(this, clientSocket)).start();

                }
            } catch (IOException ignore) {
            }
        }
    }

    public void stopAccepting() {
        try {
            this.setServerRunning(false);
            server.close();
        } catch (IOException ex) {
            ex.printStackTrace( );
        }
    }

    public void startAccepting() {
        try {
            server = new ServerSocket(socketAddress);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setServerRunning(true);
    }

    public List<String> getUserLog() {
        return userLog;
    }

    public void accountLogin(String username) {
        userLog.add(username);
    }

    public void accountLogout(String username) {
        userLog.remove(username);
    }
}


