package client.controller.application_pages;

import client.controller.LoginRegisterController;
import client.model.LoginRegisterModel;
import client.model.application_pages.UserProfileModel;
import client.view.ApplicationView;
import client.view.application_pages.HistoryPageView;
import client.view.application_pages.UserProfileView;
import utilities.Resources;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The UserProfileController processes the user requests for editing user account information, vehicle information,
 * editing user's password, viewing the booking history of the history, and logging out.
 * Based on the user request, the UserProfileController defines methods and invokes methods in the View and Model
 * to accomplish the requested action.
 */
public class UserProfileController {

    /**
     * The view LoginRegisterView object.
     */
    private final UserProfileView view;
    /**
     * The model LoginRegisterModel object.
     */
    private final UserProfileModel model;

    /*
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
     *
     * @param view  The specified view.
     * @param model The specified model.
     */
    public UserProfileController(UserProfileView view, UserProfileModel model, ApplicationView parent) {
        this.view = view;
        this.model = model;

        // constants / variables
        populateFields();

        // action listeners

        // navigation
        view.setNavEditProfileListener(e -> view.getCardLayout().show(view.getPnlCards(), "profile"));
        view.setNavEditCarsListener(e -> view.getCardLayout().show(view.getPnlCards(), "vehicles"));
        view.setNavHistoryListener(e -> view.getCardLayout().show(view.getPnlCards(), "history"));
        view.setNavSecurityListener(e -> view.getCardLayout().show(view.getPnlCards(), "security"));
        view.setNavExitListener(e -> {
            parent.dispose();
            model.getClient().logout();
        });
        // view.setNavExitListener(new LoginRegisterController());

        // edit profile page
        view.getPnlEditProfile().setContinueListener(new EditListener());
        view.getPnlEditProfile().setCancelListener(new CancelListener());

        // edit cars page
        // TODO: action listeners for edit cars page

        // history page
        // TODO: action listeners for history page

        // security page
        // TODO: action listeners for security page

        // mouse listeners

        // navigation
        view.getBtnNavEditProfile().addMouseListener(new Resources.CursorChanger(view.getBtnNavEditProfile()));
        view.getBtnNavEditCars().addMouseListener(new Resources.CursorChanger(view.getBtnNavEditCars()));
        view.getBtnNavHistory().addMouseListener(new Resources.CursorChanger(view.getBtnNavHistory()));
        view.getBtnNavSecurity().addMouseListener(new Resources.CursorChanger(view.getBtnNavSecurity()));
        view.getBtnNavExit().addMouseListener(new Resources.CursorChanger(view.getBtnNavExit()));

        // edit profile page
        view.getPnlEditProfile().getBtnContinue().
                addMouseListener(new Resources.CursorChanger(view.getPnlEditProfile().getBtnContinue()));
        view.getPnlEditProfile().getBtnCancel().
                addMouseListener(new Resources.CursorChanger(view.getPnlEditProfile().getBtnCancel()));

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

        // mouse listeners

        view.revalidate();
        view.repaint();
    }



    /**
     * Populates the respective fields of the user.
     */
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
    }

    /**
     * TODO: Documentation
     */
    class CancelListener implements ActionListener {
        /**
         * TODO: Documentation
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getPnlEditProfile().getTxtFirstName().setText(model.getFirstName());
            view.getPnlEditProfile().getTxtLastName().setText(model.getLastName());
            view.getPnlEditProfile().getTxtContact().setText(model.getContactNo());
            populateFields(); // updates the information
        }
    }

    /**
     * TODO: Documentation
     */
    public class EditListener implements ActionListener {
        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            model.editUserInformation(
                    view.getPnlEditProfile().getFirstName(),
                    view.getPnlEditProfile().getLastName(),
                    view.getPnlEditProfile().getContact()
            );
            populateFields(); // updates the information
            //TODO: Display message indicating edit was successful
        }
    }
}

