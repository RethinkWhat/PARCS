package client.model;

import client.controller.LoginRegisterController;
import client.view.account_view.LoginRegisterView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Socket;

public class Client implements Runnable {

    private Socket client;
    public Client(Socket server) {
        this.client = server;
    }

    @Override
    public void run() {
        LoginRegisterModel model = new LoginRegisterModel();
        LoginRegisterView view = new LoginRegisterView();
        new LoginRegisterController(view, model);
    }

    public static void main(String[] args) {

        try {
            Socket clientSocket = new Socket("localhost", 2020);

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            Client client = new Client(clientSocket);
            new Thread(client).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
