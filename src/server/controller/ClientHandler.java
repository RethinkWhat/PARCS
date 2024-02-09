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
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            switch (page) {
                case "login": {
                    login();
                    System.out.println("login completed");
                }//page = read(); };
                case "reservation": {
                    reserve();
                }//page = read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login() throws IOException{
        System.out.println("login attempt");

        String username = reader.readLine();
        System.out.println("read username attempt: " + username);
        String password = reader.readLine();
        System.out.println("read password attempt: " + password);


        if (username != null && password != null) {
            authenticateLogin = server.validateAccount(username, password);
        }

        if (authenticateLogin)
            write("true");
        else
            write("false");
    }


    public void reserve() {
        System.out.println("-----RESERVE-----");
        write("");
    }



    public void write(String message ) {
        writer.println(message);
    }



}

