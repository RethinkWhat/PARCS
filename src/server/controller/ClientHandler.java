package server.controller;


import client.model.Client;
import server.model.Vehicle;
import server.view.ServerStatusView;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;


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
                            case "login":
                                login();
                                System.out.println("page: " + page);
                                break;
                            case "reservation":
                                System.out.println("reservation page starting");
                                reserve();
                                System.out.println("reservation page finished");
                                break;
                            case "signUp" :
                                System.out.println("Sign Up");
                                signUp();
                            case "disconnect":
                                handleDisconnect();
                                break;
                            case "account":
                                System.out.println("account page starting");
                                account();
                                System.out.println("account page finished");
                                break;
                            case "editInfo":
                                System.out.println("editing");
                                editInfo();
                                System.out.println("edit finished");
                                break;
                            case "addVehicle":
                                System.out.println("Add Vehicle");
                                addVehicle();
                                break;
                            case "logout":
                                System.out.println("LOGOUT");
                                handleDisconnect();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleDisconnect() {
        System.out.println("---------------");
        System.out.println("disconnecting");
        disconnect = true;
        System.out.println("disconnected");
        System.out.println("---------------");
        closeResources();
        openResources();
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

        String username = null;
        while (username == null) {
            username = reader.readLine();
        }
        System.out.println("read username attempt: " + username);
        String password = reader.readLine();
        System.out.println("read password attempt: " + password);

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
            System.out.println("SIGN UP METHOD");
            String username = reader.readLine();

            System.out.println(username);
            writer.println(server.checkUsernameExists(username));

            String password = reader.readLine();
            String lastName = reader.readLine();
            String firstName = reader.readLine();
            String phoneNumber = reader.readLine();

            System.out.println(username + ", " + password + ", " + lastName + ", " + firstName + ", " + phoneNumber);

            boolean createAccount = server.createAccount(username,"user",password,lastName,firstName,phoneNumber,null);
            writer.println(createAccount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editInfo() {
        try {
            System.out.println("---EDIT INFO METHOD---");
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


    public void reserve() {
        System.out.println("-----------RESERVE-----------");
        try {
            String username = reader.readLine();
            System.out.println("Printing: " + username);
            writer.println(server.getUserFullName(username));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void account() {
        System.out.println("---------ACCOUNT---------");
        try {
            String username = reader.readLine();
            System.out.println("Printing: " + username);
            writer.println(server.getUserCredentials(username));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addVehicle() {
        System.out.println("----- ADD VEHICLE ----");
        try {
            String username = reader.readLine();
            System.out.println("ADD VEHICLE: " + username);
            String type = reader.readLine();
            String model = reader.readLine();
            String plateNumber = reader.readLine();
            Vehicle newVehicle = new Vehicle(type,model,plateNumber);

            boolean addConfirmed = server.addVehicle(username, newVehicle);
            System.out.println("Writing addConfirmed: " + addConfirmed);
            writer.println(addConfirmed);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
