package client.model;

import client.controller.LoginRegisterController;
import client.view.LoginRegisterView;

import java.io.*;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable {

    private Socket client;

    private PrintWriter writer;

    private BufferedReader reader;

    private boolean loggedIn;
    public Client(Socket server) {
        this.client = server;
    }

    @Override
    public void run() {
        LoginRegisterModel model = new LoginRegisterModel();
        LoginRegisterView view = new LoginRegisterView();
        new LoginRegisterController(this,view, model);
    }

    public void writeString(String line) {
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            writer.println(line);
            writer.flush();
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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public static void main(String[] args) {

        try {
            Scanner fileReader = new Scanner(new File("src/client/host"));
            String host = "";
            Socket clientSocket = null;
            while (fileReader.hasNext()) {
                try {
                    host = fileReader.nextLine();
                    clientSocket = new Socket(host, 2020);
                } catch (UnknownHostException ignored) {

                }
            }

            fileReader.close();


            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            Client client = new Client(clientSocket);
            new Thread(client).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
