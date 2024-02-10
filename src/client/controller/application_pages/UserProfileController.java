package client.controller.application_pages;

import client.view.application_pages.HistoryPageView;
import client.view.application_pages.UserProfileView;

import javax.swing.*;

public class UserProfileController {

    UserProfileView gui;
    Object userAccount;
    JPanel pnlMain;

    /*
    public UserProfileController(UserProfileView view, Object userLoggedIn) {
        this.gui = view;
        this.userAccount = userLoggedIn;

        pnlMain = gui.pnlMain;

        view.btnEditProfile.addActionListener(e -> editProfile());
        view.btnEditCars.addActionListener(e -> showEditCars());
        view.historyButton.addActionListener(e -> showHistory());
//        gui.btnExit.addActionListener(new exit());

    }

    private void editProfile() {
        gui.cardLayout.show(gui.pnlMain, "editProfile");
    }


    private void showEditCars() {
        gui.cardLayout.show(gui.pnlMain, "editCars");
    }


    private void showHistory() {
        // Dispose the current window and show the history page
        gui.dispose();
        HistoryPageView historyPageView = new HistoryPageView();
        historyPageView.setVisible(true);
    }

    /*
    public class exit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginRegisterController loginObj = new LoginRegisterController();
            loginObj.run();
        }
    }
    */

    public static void main(String[] args) {
                UserProfileView userProfileView = new UserProfileView();
    }
}
