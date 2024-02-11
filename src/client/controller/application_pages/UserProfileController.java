package client.controller.application_pages;

import client.model.application_pages.UserProfileModel;
import client.view.application_pages.HistoryPageView;
import client.view.application_pages.UserProfileView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfileController {
    /**
     * TODO: Documentation
     */
    UserProfileView view;
    /**
     * TODO: Documentation
     */
    UserProfileModel model;
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

    public UserProfileController(UserProfileView view, UserProfileModel model) {
        this.view = view;
        this.model = model;

        // constants / variable
        model.getCredentials();

        // Name
        view.getPnlEditProfile().getTxtFirstName().setText(model.getFirstName());
        view.getPnlEditProfile().getTxtLastName().setText(model.getLastName());

        // Username
        view.getPnlEditProfile().getTxtUsername().setText(model.getUsername());
        view.getPnlEditProfile().getTxtUsername().setEditable(false);

        view.getPnlEditProfile().getTxtContact().setText(model.getContactNo());

        // action listeners
        view.setNavEditProfileListener(e -> view.getCardLayout().show(view.getPnlCards(), "profile"));
        view.setNavEditCarsListener(e -> view.getCardLayout().show(view.getPnlCards(), "vehicles"));

        // editProfileListener
        view.getPnlEditProfile().setContinueListener(new editListener());

        // mouse listeners
    }

    public static void main(String[] args) {
                UserProfileView userProfileView = new UserProfileView();
    }

    public class editListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.editUserInformation(
                    view.getPnlEditProfile().getFirstName(),
                    view.getPnlEditProfile().getLastName(),
                    view.getPnlEditProfile().getContact()
            );
            //TODO: Display message indicating edit was successful
        }
    }
}
