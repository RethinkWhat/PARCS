package server.model;


import server.model.*;
import server.utilities.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{
    private ServerSocket server;

    private UserParser userParser;

    private ReservationParser reservationParser;

    private int socketAddress;

    private volatile boolean serverRunning;

    // List of users logged in to the system
    List<String> userLog;

    public Server(int socketAddress)throws IOException {
        this.socketAddress = socketAddress;
        userParser = new UserParser();
        reservationParser = new ReservationParser();
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

    public List<String> getParkingAvailability(String identifier, String duration, String date) {
        return reservationParser.availableTime(identifier,duration, date);
    }

    public String getDuration(String startTime, String endTime) {
        return reservationParser.computeDuration(startTime, endTime);

    }

    public List<String> getClosestReservation(String username, String currentTime) {
        return reservationParser.getClosestReservation(username, currentTime);
    }

    public Map<String, List<String>> getUserVehicles(String username) {

        return userParser.getUserVehicles(username);
    }

    public boolean makeReservation(String identifier, String date, String startTime, String duration, String username) {
        try {
            reservationParser.createReservationNode(identifier, date, startTime, duration, username);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean checkScheduleConflicts(String username, String startTime, String duration, String date) {
        String endTime = reservationParser.computeEndTime(startTime,duration);
        return reservationParser.checkScheduleConflicts(username, startTime, endTime, date);
    }

    public boolean serverRunning() {
        return serverRunning;
    }


    public int getNumberOfBookings() {
        return reservationParser.countBookings();
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
                    ExecutorService executor = Executors.newFixedThreadPool(10);
                    executor.submit(new ClientHandler(this, clientSocket));
                    //new Thread(new ClientHandler(this, clientSocket)).start();

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

    public int countBookings(String username, String date) {
        return reservationParser.countTotalBookingsPerDay(username, date);
    }
}


