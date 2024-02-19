package client.model;

import client.controller.LoginRegisterController;
import client.view.LoginRegisterView;
import utilities.Resources;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import java.net.*;
import java.util.Scanner;


/**
 * The Client class represents the client-side of the PARCS (Parking Reservation and Counting System) application.
 * It manages the connection to the server, socket operations, and GUI initialization.
 */
public class Client {

    // Object to hold client socket
    private Socket client;

    /**
     * The username associated with the client.
     */
    private String username;

    /**
     * The socket address used for connecting to the server.
     */
    private SocketAddress socketAddress;

    /**
     * The BufferedReader used for reading from the server.
     */
    BufferedReader reader;

    /**
     * The PrintWriter used for writing to the server.
     */
    PrintWriter writer;

    /**
     * Constructs a Client with a specified client socket.
     *
     * @param client The client socket.
     */
    public Client(Socket client) {
        this.client = client;
    }

    /**
     * Constructs a Client with a specified port.
     *
     * @param port The port to connect to.
     */
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
                displayErrorMessage();
            }

            // if no exception occurs in connecting to server
            socketAddress = new InetSocketAddress(host, port);
            fileReader.close();
        } catch (IOException ex) {

        }
    }

    /**
     * Retrieves the client socket.
     *
     * @return The client socket.
     */
    public Socket getClient() {
        return client;
    }

    /**
     * Sets the client socket.
     *
     * @param client The new client socket.
     */
    public void setClient(Socket client) {
        this.client = client;
    }

    /**
     * Writes a string to the server.
     *
     * @param line The string to be written.
     */
    public void writeString(String line) {
            writer.println(line);
    }

    /**
     * Reads a string from the server.
     *
     * @return The string read from the server.
     */
    public String readString() {
        String toReturn = null;
        try {
            toReturn =  reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return toReturn;
    }

    /**
     * Retrieves the current date using the LiveDateTime class.
     *
     * @return The current date.
     */
    public String getDate() {
        return LiveDateTime.getDate();
    }

    /**
     * Retrieves the current time using the LiveDateTime class.
     *
     * @return The current time.
     */
    public String getTime() {
        return LiveDateTime.getTimeForTimerComparisons();
    }

    /**
     * Retrieves the username associated with the client.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the client.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Opens a socket connection to the server.
     *
     * @return True if the connection is successful, false otherwise.
     */
    public boolean openSocket() {
        try {
            client = new Socket();
            client.connect(socketAddress);
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"), true);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            displayErrorMessage();
            return false;
        }
    }

    /**
     * Flushes the writer.
     */
    public void flushWriter() {
        writer.flush();
    }

    /**
     * Initializes a new socket connection using a specified port.
     *
     * @param port The port to connect to.
     */
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

    /**
     * Logs out the user from the server and starts the GUI.
     */
    public void logout() {
        openSocket();
        writeString("logout");
        this.writeString(this.getUsername());

        username = null;
        startGUI();
    }

    /**
     * Logs out the user from the server and exits the application.
     *
     * @return 1 indicating a successful exit.
     */
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

    /**
     * Closes the client socket, reader, and writer.
     */
    public void closeSocket() {
        try {
            client.close();
            reader.close();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Displays an error message dialog when unable to connect to the server.
     */
    private void displayErrorMessage() {
        JFrame mainFrame = new JFrame();
        JDialog dialog = new JDialog(mainFrame, "PARCS", true);
        dialog.setTitle("PARCS");
        dialog.setLayout(new GridLayout(3, 1));
        dialog.setSize(500, 300);

        // Create pnlIcon panel
        JPanel pnlIcon = new JPanel();
        pnlIcon.setLayout(new BorderLayout());
        pnlIcon.setPreferredSize(new Dimension(600, 200));

        // Create and set ImageIcon for pnlIcon
        Resources res = new Resources();
        ImageIcon iconAvailableCar = res.iconOffline;
        pnlIcon.add(new JLabel(iconAvailableCar), BorderLayout.CENTER);

        // Create pnlServerClosed panel
        JPanel pnlServerClosed = new JPanel(new GridBagLayout());
        pnlServerClosed.setPreferredSize(new Dimension(600, 170));

        // Labels for the pnlServerClosed
        JLabel lblServerMsg = res.createLblH1("SERVER IS CLOSED", res.red);
        JLabel lblClosedMsg = res.createLblP("Unable to connect to server. Please try again later.", res.eerieBlack);

        // Add labels to pnlServerClosed panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlServerClosed.add(lblServerMsg, gbc);

        gbc.gridy = 1;
        pnlServerClosed.add(lblClosedMsg, gbc);

        // Create pnlExit panel
        JPanel pnlExit = new JPanel(new FlowLayout());
        pnlExit.setPreferredSize(new Dimension(600, 30));

        // Create the exit button
        JButton btnExit = res.createBtnRounded("EXIT", res.red, res.eerieBlack, 10);

        btnExit.addActionListener(e -> {
            System.exit(0);
        });

        pnlExit.add(btnExit);

        // Add panels to the dialog
        dialog.add(pnlIcon);
        dialog.add(pnlServerClosed);
        dialog.add(pnlExit);

        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    /**
     * Checks if the server is open by attempting to create a test socket.
     *
     * @return True if the server is open, false otherwise.
     */
    private boolean isServerOpen() {
        try {
            Socket testSocket = new Socket();
            testSocket.connect(socketAddress);
            testSocket.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Initiates the login and registration process.
     */
    private void loginRegister() {
        LoginRegisterModel model = new LoginRegisterModel(this);
        LoginRegisterView view = new LoginRegisterView();
        //view.setDefaultCloseOperation(logoutAndExit());
        new LoginRegisterController(view, model);
    }

    /**
     * Checks if the server is open and initializes the GUI accordingly.
     */
    public void startGUI() {
        if (isServerOpen() && openSocket()) {
            loginRegister();
        } else {
            displayErrorMessage();
        }
    }

    /**
     * The main method to start the client application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Thread clientsThread = new Thread(() ->{
            Client client = new Client(2040);

            client.startGUI();
        });
        clientsThread.start();
    }
}