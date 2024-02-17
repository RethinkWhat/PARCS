package server.utilities;


import client.model.DateTime;
import server.model.Server;
import server.model.Vehicle;

import java.io.*;
import java.net.*;
import java.util.*;


public class ClientHandler implements Runnable {


    Server server;
    Socket client;
    BufferedReader reader;
    PrintWriter writer;


    boolean authenticateLogin = false;

    boolean disconnect = false;
    InetAddress address;


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

    @Override
    public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream(),true);
                while (!disconnect) {
                        String page = reader.readLine();
                        if (page != null) {
                            System.out.println("page : " + page);
                            switch (page) {
                                case "logout":
                                    handleLogout();
                                    break;
                                case "login":
                                    System.out.println("login switch case reached");
                                    login();
                                    break;
                                case "reservation":
                                    System.out.println("reservation switch case reached");
                                    reserve();
                                    break;
                                case "signUp":
                                    System.out.println("reached sign up");
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
                            }
                        }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void handleDisconnect() {
        disconnect = true;
        closeResources();
        openResources();
    }

    public void handleLogout() {
        try {
            String username = reader.readLine();
            server.accountLogout(username);
            handleDisconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

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




    public void login() throws IOException {
        System.out.println("login attempt");

        String username = reader.readLine();
        System.out.println(username);

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

    public void editPassword(){
        try {
            String username = reader.readLine();
            String newPassword = reader.readLine();

            writer.println(server.editInfo(username, "password", newPassword));
        }catch (IOException iox){
            iox.printStackTrace();
        }
    }


    public void reserve() {
        DateTime dateTime = new DateTime();
        try {
            String username = reader.readLine();
            System.out.println("received username: " + username);

            writer.println(server.getUserFullName(username));
            System.out.println("wrote full name: " + server.getUserFullName(username));

            String totalBookings = String.valueOf(server.countBookings(username,dateTime.getDateTime()));
            writer.println(totalBookings);
            System.out.println("wrote total bookings: " + totalBookings);

            Map<String, List<String>> vehicles = server.getUserVehicles(username);
            System.out.println("vehicles: " + vehicles);

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
                System.out.println("2. vehicles: " + vehicles);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * TODO: Documentation
     */
    public void getVehicles() {
        try {
            String username = reader.readLine();
            Map<String, List<String>> vehicles = server.getUserVehicles(username);
            //ObjectOutputStream outputStreamWriter = new ObjectOutputStream(client.getOutputStream());
           // outputStreamWriter.writeObject(vehicles);
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
                System.out.println("2. vehicles: " + vehicles);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void account() {
        try {
            String username = reader.readLine();
            writer.println(server.getUserCredentials(username));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void spotInfo() {
        DateTime dateTime = new DateTime();
        try {
            String identifier = reader.readLine();
            String duration = reader.readLine();
            String date = reader.readLine();
            List<String> availableTime = server.getParkingAvailability(identifier, duration, date);

            for (String time : availableTime) {
                if (Integer.valueOf(dateTime.getTime().split(":")[0]) <=
                        Integer.valueOf(time.split(":")[0])) {
                    writer.println(time);
                }
            }
            writer.println("complete");
          //  outputStreamWriter.writeObject(availableTime);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

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

    public void ticket() {
        DateTime dateTime = new DateTime();
        try {
            String username = reader.readLine();

            List<String> userReservation = server.getClosestReservation(username, dateTime.getTime());

            for (String reservation : userReservation) {
                writer.println(reservation);//outputStreamWriter.writeObject(userReservation);
                System.out.println("printing: " + reservation);
            }
            writer.println("complete");

            String duration = server.getDuration(userReservation.get(1), userReservation.get(2));
            writer.println(duration);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
