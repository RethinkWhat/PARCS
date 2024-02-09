package server.controller;


import java.io.*;
import java.net.Socket;


public class ClientHandler implements Runnable {


    Server server;
    Socket client;
    BufferedReader reader;
    PrintWriter writer;


    boolean authenticateLogin = false;

    String page = "login";


    boolean userLoggedIn = true;
    public ClientHandler(Server server, Socket client) {
        this.client = client;
        this.server = server;
    }


    @Override
    public void run() {
        try {
            switch (page) {
                case "login": {
                    login();
                    System.out.println("login completed");
                }//page = read(); };
                case "reservation": {
                    register();
                }//page = read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login() throws IOException{
        System.out.println("login attempt");

        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String username = read();
        System.out.println("read username attempt: " + username);
        String password = read();
        System.out.println("read password attempt: " + password);


        if (username != null && password != null) {
            authenticateLogin = server.validateAccount(username, password);
        }

        if (authenticateLogin)
            write("true");
        else
            write("false");
    }


    public void register() {}




    public void write(String message ) {
        try {
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            writer.println(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public String read() {
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return  line;
    }
}

