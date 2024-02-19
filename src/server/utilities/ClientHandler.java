package server.utilities;


import client.model.DateTime;
import server.model.Reservations;
import server.model.Server;
import server.model.Vehicle;

import java.io.*;
import java.net.*;
import java.util.*;


public class ClientHandler implements Runnable {


    /** Reference to the server instance */
    Server server;

    /** Socket representing the client connection */
    Socket client;

    /** BufferedReader for reading client input */
    BufferedReader reader;

    /** PrintWriter for writing output to the client */
    PrintWriter writer;

    /** Flag indicating whether the client is authenticated */
    boolean authenticateLogin = false;

    /** Flag indicating whether the client is disconnected */
    boolean disconnect = false;

    /** The server's IP address */
    InetAddress address;


    /**
     * Constructs a new ClientHandler with the given server and client socket.
     * @param server The server instance.
     * @param client The client socket.
     */
    public ClientHandler(Server server, Socket client) {
        this.client = client;
        this.server = server;
        this.address = server.getServer().getInetAddress();
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    /**
     * Runs the client handler thread, processing client requests.
     */
    @Override
    public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream(),true);
                while (!disconnect) {
                        String page = reader.readLine();
                        if (page != null) {
                            switch (page) {
                                case "logout":
                                    handleLogout();
                                    break;
                                case "login":
                                    login();
                                    break;
                                case "reservation":
                                    reserve();
                                    break;
                                case "signUp":
                                    signUp();
                                    break;
                                case "disconnect":
                                    handleDisconnect();
                                    break;
                                case "account":
                                    account();
                                    break;
                                case "editInfo":
                                    editInfo();
                                    break;
                                case "editPassword":
                                    editPassword();
                                    break;
                                case "addVehicle":
                                    addVehicle();
                                    break;
                                case "spotInfo":
                                    spotInfo();
                                    break;
                                case "book":
                                    bookReservation();
                                    break;
                                case "ticket":
                                    ticket();
                                    break;
                                case "getVehicles":
                                    getVehicles();
                                    break;
                                case "editVehicleInfo":
                                    editVehicle();
                                    break;
                                case "history":
                                    history();
                                    break;
                                case "delete":
                                    deleteUser();
                                    break;
                            }
                        }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    /**
     * Method to handle disconnect the client from the server
     */
    public void handleDisconnect() {
        disconnect = true;
        closeResources();
        openResources();
    }

    /**
     * Method to handle user logout
     */
    public synchronized void handleLogout() {
        try {
            String username = reader.readLine();
            server.accountLogout(username);
            handleDisconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method to handle closing the client port, reader, and writer
     */
    private void closeResources() {
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (client != null && !client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to handle accepting a new client connection and establishing a connection, reader, and writer
     */
    private void openResources() {
        try {
            client = new Socket();  // Create a new socket for a potential reconnection
            client.connect(new InetSocketAddress(address, server.getServer().getLocalPort()));
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to handle login, sending and receiving information between client and server
     * @throws IOException
     */
    public synchronized void login() throws IOException {

        String username = reader.readLine();

        String password = reader.readLine();

        authenticateLogin = server.validateAccount(username, password);

        if (authenticateLogin) {
            if (server.getUserLog().contains(username)) {
                writer.println("userExists");
            }else {
                server.accountLogin(username);
                writer.println("true");
            }
        }
        else
            writer.println("false");


    }


    /**
     * Method to handle sign up, creating account and validating credentials
     */
    public void signUp() {
        try {
            String username = reader.readLine();

            writer.println(server.checkUsernameExists(username));

            String password = reader.readLine();
            String lastName = reader.readLine();
            String firstName = reader.readLine();
            String phoneNumber = reader.readLine();

            boolean createAccount = server.createAccount(username,"user",password,lastName,firstName,phoneNumber,null);
            writer.println(createAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to handle editing user information, called when user clicks edit profile
     */
    public void editInfo() {
        try {
            String lineRead = "";
            while (true) {
                lineRead = reader.readLine();
                if (lineRead.equals("complete"))
                    break;
                String[] tagAndUpdate = lineRead.split(",");
                server.editInfo(tagAndUpdate[0], tagAndUpdate[1], tagAndUpdate[2]);
            }
            writer.println("true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method to handle editing information of a vehicle registered to a user
     */
    public void editVehicle() {
        try {
            String username = reader.readLine();
            String plateNo = reader.readLine();
            String newVehicle = reader.readLine();
            writer.println(server.editVehicle(username,plateNo, newVehicle));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Method to handle editing the password associated to a user account
     */
    public void editPassword(){
        try {
            String username = reader.readLine();
            String newPassword = reader.readLine();

            writer.println(server.editInfo(username, "password", newPassword));
        }catch (IOException iox){
            iox.printStackTrace();
        }
    }


    /**
     * Method to handle populating the information present on the reservation page of user
     */
    public void reserve() {
        DateTime dateTime = new DateTime();
        try {
            String username = reader.readLine();

            writer.println(server.getUserFullName(username));

            String totalBookings = String.valueOf(server.countBookings(username,dateTime.getDateTime()));
            writer.println(totalBookings);

            Map<String, List<String>> vehicles = server.getUserVehicles(username);

            if (vehicles.isEmpty()) {
                writer.println("empty");
            }
            else {
                for (String key : vehicles.keySet()) {
                    writer.println(key);
                    for (String value : vehicles.get(key)) {
                        writer.println(value);
                    }
                    writer.println("nextKey");
                }
                writer.println("complete");
                //outputStreamWriter.writeObject(vehicles);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method to get the vehicles associated with a user account
     */
    public void getVehicles() {
        try {
            String username = reader.readLine();
            Map<String, List<String>> vehicles = server.getUserVehicles(username);
            if (vehicles.isEmpty()) {
                writer.println("empty");
            }
            else {
                for (String key : vehicles.keySet()) {
                    writer.println(key);
                    for (String value : vehicles.get(key)) {
                        writer.println(value);
                    }
                    writer.println("nextKey");
                }
                writer.println("complete");
                //outputStreamWriter.writeObject(vehicles);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get the account credentials of a user account
     */
    public void account() {
        try {
            String username = reader.readLine();
            writer.println(server.getUserCredentials(username));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to handle registering a vehicle for a user
     */
    public void addVehicle() {
        try {
            String username = reader.readLine();
            String type = reader.readLine();
            String model = reader.readLine();
            String plateNumber = reader.readLine();
            Vehicle newVehicle = new Vehicle(type,model,plateNumber);

            boolean addConfirmed = server.addVehicle(username, newVehicle);
            writer.println(addConfirmed);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method to handle getting the information of a parking spot
     */
    public void spotInfo() {
        try {
            String identifier = reader.readLine();
            String duration = reader.readLine();
            String date = reader.readLine();

            List<String> availableTime = server.getParkingAvailability(identifier, duration, date);

            for (String time : availableTime) {
                    writer.println(time);
            }
            writer.println("complete");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method to handle make a reservation and validating if reservation can be made
     */
    public void bookReservation() {
        try {
            String identifier = reader.readLine();
            String date = reader.readLine();
            String startTime = reader.readLine();
            String duration = reader.readLine();
            String username = reader.readLine();


            if (server.checkScheduleConflicts(username,startTime,duration, date)) {
                boolean confirmed = server.makeReservation(identifier, date, startTime, duration, username);
                writer.println(confirmed);
            } else {
                writer.println("false");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method to handle displaying the information necessary for a booking under the ticket view
     */
    public void ticket() {
        DateTime dateTime = new DateTime();
        try {
            String username = reader.readLine();

            List<String> userReservation = server.getClosestReservation(username, dateTime.getTime());

            for (String reservation : userReservation) {
                writer.println(reservation);//outputStreamWriter.writeObject(userReservation);
            }
            writer.println("complete");

            String duration = server.getDuration(userReservation.get(1), userReservation.get(2));
            writer.println(duration);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Method to get all the history of bookings associated with a user account
     */
    public void history() {
        try {
            String username = reader.readLine();
            List<List<String>> userBookings = server.getUserReservations(username);

            for (List<String> bookingList : userBookings) {

                for (String bookingInfo : bookingList) {
                    writer.println(bookingInfo);
                }
                writer.println("nextBooking");
            }
            writer.println("complete");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /** Method to delete a user account */
    public void deleteUser() {
        try {
            String username = reader.readLine();
            server.deleteUser(username);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

}
