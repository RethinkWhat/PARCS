package client.model;

import client.LoginRegisterApp;

import java.net.ServerSocket;
import java.net.Socket;

public class Client implements Runnable {

    private Socket client;
    public Client(ServerSocket server) throws Exception{
        this.client = server.accept();
    }

    @Override
    public void run() {
        new LoginRegisterApp().run();
    }
}
