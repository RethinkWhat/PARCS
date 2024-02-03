package client.controller;

import client.view.account_view.LoginRegisterView;

import javax.swing.*;

public class LoginRegisterController implements Runnable{

    // Temporary main method to view and debug fra
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginRegisterView();
            }
        });
    }

    @Override
    public void run() {
        new LoginRegisterView();
    }
}
