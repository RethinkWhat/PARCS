package client.controller.application_pages;

import client.model.application_pages.UserProfileModel;
import client.view.application_pages.HistoryPageView;
import client.view.application_pages.UserProfileView;
import utilities.Resources;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO: Documentation
 */
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

    /**
     * Constructs a controller of the UserProfile page with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public UserProfileController(UserProfileView view, UserProfileModel model) {
        this.view = view;
        this.model = model;

        populateFields();

        // action listeners
        view.setNavEditProfileListener(e -> view.getCardLayout().show(view.getPnlCards(), "profile"));
        view.setNavEditCarsListener(e -> view.getCardLayout().show(view.getPnlCards(), "vehicles"));

        // editProfileListener
        view.getPnlEditProfile().setContinueListener(new editListener());
        view.getPnlEditProfile().setCancelListener(new cancelListener());

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

    public void populateFields() {
        // constants / variable
        model.getCredentials();

        // Name
        view.getPnlEditProfile().getTxtFirstName().setText(model.getFirstName());
        view.getPnlEditProfile().getTxtLastName().setText(model.getLastName());

        // Username
        view.getPnlEditProfile().getTxtUsername().setText(model.getUsername());
        view.getPnlEditProfile().getTxtUsername().setEditable(false);

        // Contact Number
        view.getPnlEditProfile().getTxtContact().setText(model.getContactNo());

        // action listeners

        // navigation
        view.setNavEditProfileListener(e -> view.getCardLayout().show(view.getPnlCards(), "profile"));
        view.setNavEditCarsListener(e -> view.getCardLayout().show(view.getPnlCards(), "vehicles"));
        view.setNavHistoryListener(e -> new HistoryPageView());
        // view.setNavExitListener();

        // edit profile page
        view.getPnlEditProfile().setContinueListener(new ContinueListener());
        view.getPnlEditProfile().setCancelListener(new CancelListener());

        // edit cars page
        // TODO: action listeners for edit cars page

        // history page
        // TODO: action listeners for history page

        // security page
        // TODO: action listeners for security page

        // mouse listeners

        // edit profile page
        view.getBtnNavEditProfile().addMouseListener(new Resources.CursorChanger(view.getBtnNavEditProfile()));
        view.getBtnNavEditCars().addMouseListener(new Resources.CursorChanger(view.getBtnNavEditCars()));
        view.getBtnNavHistory().addMouseListener(new Resources.CursorChanger(view.getBtnNavHistory()));
        view.getBtnNavSecurity().addMouseListener(new Resources.CursorChanger(view.getBtnNavSecurity()));
        view.getBtnNavExit().addMouseListener(new Resources.CursorChanger(view.getBtnNavExit()));

        // edit cars page
        // TODO: mouse listeners for edit cars page
        // TODO: functionality when the mouse hovers on the vehicle, an edit button appears

        // history page
        // TODO: mouse listeners for history page

        // security page
        // TODO: mouse listeners for security page

        // focus listeners

        // edit profile page
        view.getPnlEditProfile().getTxtLastName().addFocusListener(
                new Resources.TextFieldFocus(view.getPnlEditProfile().getTxtLastName(), model.getLastName()));
        view.getPnlEditProfile().getTxtFirstName().addFocusListener(
                new Resources.TextFieldFocus(view.getPnlEditProfile().getTxtFirstName(), model.getFirstName()));
        view.getPnlEditProfile().getTxtLastName().addFocusListener(
                new Resources.TextFieldFocus(view.getPnlEditProfile().getTxtLastName(), model.getLastName()));
        view.getPnlEditProfile().getTxtContact().addFocusListener(
                new Resources.TextFieldFocus(view.getPnlEditProfile().getTxtContact(), model.getContactNo()));

        // edit cars page

        view.repaint();
        view.revalidate();
    }

    /**
     * TODO: Documentation
     */
    class CancelListener implements ActionListener {
        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getPnlEditProfile().getTxtFirstName().setText(model.getFirstName());
            view.getPnlEditProfile().getTxtLastName().setText(model.getLastName());
            view.getPnlEditProfile().getTxtContact().setText(model.getContactNo());
        }
    }

    /**
     * TODO: Documentation
     */
    class ContinueListener implements ActionListener {
        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: implementation
        }
    }
}
