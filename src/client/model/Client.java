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

    // Object for writing to server
    private PrintWriter writer;

    // Object for reading from server
    private BufferedReader reader;

    private String username;

    private SocketAddress socketAddress;

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
            client = new Socket(host, port);
            client.close();

            // if no exception occurs in connecting to server
            socketAddress = new InetSocketAddress(host, port);
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    public void writeString(String line) {
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            writer.println(line);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String readString() {
        String toReturn = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            toReturn =  reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return toReturn;
    }

    public void openSocket() {
        try {
            client = new Socket();
            client.connect(socketAddress);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void closeSocket() {
        try {
            client.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void startGUI() {
        LoginRegisterModel model = new LoginRegisterModel(this);
        LoginRegisterView view = new LoginRegisterView();
        new LoginRegisterController(this,view, model);
    }

    public static void main(String[] args) {
        Thread clientsThread = new Thread(() ->{
            Client client = new Client(2020);

            client.startGUI();
        });
        clientsThread.start();
    }


}
