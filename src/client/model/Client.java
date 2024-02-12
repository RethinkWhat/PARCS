package client.model;

import client.controller.LoginRegisterController;
import client.view.LoginRegisterView;

import java.io.*;

import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    // Object to hold client socket
    private Socket client;

    private String username;

    private SocketAddress socketAddress;

    BufferedReader reader;

    PrintWriter writer;

    public Client(Socket client) {
        this.client = client;
    }

    public Client(int port ) {
        try {
            Scanner fileReader = new Scanner(new File("src/client/host"));
            String host = "";


            // read host IP address
            host = fileReader.nextLine();

            // attempt to connect to server
            try {
                client = new Socket(host, port);
                client.close();
            } catch (ConnectException ex) {
                System.out.println();
                System.out.println("-----------------");
                System.out.println("Server is closed.");
                System.out.println("-----------------");
                System.exit(0);
            }

            // if no exception occurs in connecting to server
            socketAddress = new InetSocketAddress(host, port);
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public void writeString(String line) {
            writer.println(line);
    }

    public String readString() {
        String toReturn = null;
        try {
            toReturn =  reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return toReturn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void openSocket() {
        try {
            client = new Socket();
            client.connect(socketAddress);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
        } catch (IOException ex) {
            System.out.println();
            System.out.println("-----------------");
            System.out.println("Server is closed.");
            System.out.println("-----------------");
            System.exit(0);
        }
    }

    public void newSocket(int port) {
        try {
            Scanner fileReader = new Scanner(new File("src/client/host"));
            String host = "";


            // read host IP address
            host = fileReader.nextLine();

            // attempt to connect to server
            client = new Socket(host, port);
            client.close();

            // if no exception occurs in connecting to server
            socketAddress = new InetSocketAddress(host, port);
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void closeSocket() {
        try {
            client.close();
            reader.close();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void startGUI() {
        LoginRegisterModel model = new LoginRegisterModel(this);
        LoginRegisterView view = new LoginRegisterView();
        new LoginRegisterController(view, model);
    }

    public static void main(String[] args) {
        Thread clientsThread = new Thread(() ->{
            Client client = new Client(2020);

            client.startGUI();
        });
        clientsThread.start();
    }


}
