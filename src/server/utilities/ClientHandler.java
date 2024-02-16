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

        while (!disconnect) {
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
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
                            case "signUp" :
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
        String username = null;
        while (username == null) {
            username = reader.readLine();
        }
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
            writer.println(server.getUserFullName(username));

            String totalBookings = String.valueOf(server.countBookings(username,dateTime.getDateTime()));
            writer.println(totalBookings);

            Map<String, List<String>> vehicles = server.getUserVehicles(username);
            ObjectOutputStream outputStreamWriter = new ObjectOutputStream(client.getOutputStream());
            outputStreamWriter.writeObject(vehicles);
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
            ObjectOutputStream outputStreamWriter = new ObjectOutputStream(client.getOutputStream());
            outputStreamWriter.writeObject(vehicles);
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
        try {
            String identifier = reader.readLine();
            String duration = reader.readLine();
            String date = reader.readLine();
            List<String> availableTime = server.getParkingAvailability(identifier, duration, date);
            ObjectOutputStream outputStreamWriter = new ObjectOutputStream(client.getOutputStream());
            outputStreamWriter.writeObject(availableTime);
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


            if (server.checkScheduleConflicts(identifier,startTime,duration)) {
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
            ObjectOutputStream outputStreamWriter = new ObjectOutputStream(client.getOutputStream());

            List<String> userReservation = server.getClosestReservation(username, dateTime.getTime());
            outputStreamWriter.writeObject(userReservation);

            String duration = server.getDuration(userReservation.get(1), userReservation.get(2));
            writer.println(duration);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
