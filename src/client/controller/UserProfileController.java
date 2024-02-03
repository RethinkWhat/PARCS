package client.controller;

import client.view.account_view.UserProfileView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfileController {

    UserProfileView gui;
    Object userAccount;

    public UserProfileController(UserProfileView view, Object userLoggedIn) {
        this.gui = view;
        this.userAccount = userLoggedIn;

        gui.btnEditProfile.addActionListener(new editProfile());
        gui.btnEditCars.addActionListener(new editCars());
        gui.btnExit.addActionListener(new exit());

    }

    public class editProfile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.cardLayout.show(gui.pnlMain, "editProfile");
        }
    }

    public class editCars implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.cardLayout.show(gui.pnlMain, "editCars");
        }
    }

    public class exit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginRegisterController loginObj = new LoginRegisterController();
            loginObj.run();
        }
    }

    public static void main(String[] args) {
        UserProfileController obj = new UserProfileController(new UserProfileView(),
                new Object()
        );

    }


}
