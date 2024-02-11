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

    /**
     * Constructs a controller of the UserProfile page with a specified view and model.
     *
     * @param view  The specified view.
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
        view.getPnlEditProfile().setContinueListener(new EditListener());
        view.getPnlEditProfile().setCancelListener(new CancelListener());

        // mouse listeners
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
        view.getPnlEditProfile().setContinueListener(new EditListener());
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
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            populateFields();
        }
    }

    public class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.editUserInformation(
                    view.getPnlEditProfile().getFirstName(),
                    view.getPnlEditProfile().getLastName(),
                    view.getPnlEditProfile().getContact()
            );
            populateFields();
            //TODO: Display message indicating edit was successful
        }
    }
}

