package client.model;

import client.controller.LoginRegisterController;
import client.view.LoginRegisterView;
import utilities.Resources;

import javax.swing.*;
import java.awt.*;
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

    ObjectInputStream readerObject;

    ObjectOutputStream writerObject;

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

    public void writeObject(Object object) {
        try {
            writerObject.writeObject(object);
        } catch (IOException ex ){
            ex.printStackTrace();
        }
    }

    public Object readObject() {
        Object obj = null;
        try {
            readerObject = new ObjectInputStream(client.getInputStream());
            obj = readerObject.readObject();
        }catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    public String getDate() {
        return LiveDateTime.getDate();
    }

    public String getTime() {
        return LiveDateTime.getTimeForTimerComparisons();
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
            ex.printStackTrace();
            System.out.println();
            System.out.println("-----------------");
            System.out.println("Server is closed.");
            System.out.println("-----------------");
            System.exit(0);
        }
    }

    public void openObjectSocket() {
        try {
            client = new Socket();
            client.connect(socketAddress);
            //readerObject = new ObjectInputStream(client.getInputStream());
            //writerObject = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
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

    public void logout() {
        openSocket();
        try {
            writeString("logout");
            this.writeString(this.getUsername());
            client.close();
            startGUI();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        closeSocket();
    }

    public int logoutAndExit() {
        openSocket();
        try {
            writeString("logout");
            this.writeString(this.getUsername());
            client.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        closeSocket();
        return 1;
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

    public void closeObjectSocket() {
        try{
            readerObject.close();
            writerObject.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void displayErrorMessage() {
        JDialog dialog = new JDialog();
        dialog.setTitle("PARCS");
        dialog.setLayout(new GridLayout(3, 1));
        dialog.setSize(500, 300);

        // Create pnlIcon panel
        JPanel pnlIcon = new JPanel();
        pnlIcon.setLayout(new BorderLayout());
        pnlIcon.setPreferredSize(new Dimension(600, 200));

        // Create and set ImageIcon for pnlIcon
        Resources res = new Resources();
        ImageIcon iconAvailableCar = res.iconTakenCar; // CHANGE ICON
        pnlIcon.add(new JLabel(iconAvailableCar), BorderLayout.CENTER);


        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    public void startGUI() {
        LoginRegisterModel model = new LoginRegisterModel(this);
        LoginRegisterView view = new LoginRegisterView();
        view.setDefaultCloseOperation(logoutAndExit());
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
