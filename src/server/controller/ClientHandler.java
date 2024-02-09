package server.controller;


import java.io.*;
import java.net.Socket;


public class ClientHandler implements Runnable {


    Server server;
    Socket client;
    BufferedReader reader;
    PrintWriter writer;


    boolean authenticateLogin = false;

    String username;


    boolean userLoggedIn = true;

    public ClientHandler(Server server, Socket client) {
        this.client = client;
        this.server = server;
    }


    @Override
    public void run() {

        //TODO: Change to while GUI open
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
            while (true) {
                String page = reader.readLine();
                if (page != null) {
                    switch (page) {
                        case "login":
                            username = login();
                            System.out.println("page: " + page);
                            break;
                        case "reservation":
                            System.out.println("reservation page starting");
                            reserve();
                            System.out.println("reservation page finished");
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String login() throws IOException {
        System.out.println("login attempt");

        String username = null;
        while (username == null) {
            username = reader.readLine();
        }
        System.out.println("read username attempt: " + username);
        String password = reader.readLine();
        System.out.println("read password attempt: " + password);


        authenticateLogin = server.validateAccount(username, password);

        if (authenticateLogin)
            writer.println("true");
        else
            writer.println("false");
        return username;
    }


    public void reserve() {
        System.out.println("-----------RESERVE-----------");
        String startReserve = null;
        try {
            while (startReserve == null) {
                try {
                    startReserve = reader.readLine();
                } catch (NullPointerException ignore) {
                }
            }
            System.out.println("out of loop");
            writer.println(username);
            System.out.println(username);
            reader.readLine();
        } catch (IOException j) {
            System.out.println("J");
        }
    }
}
