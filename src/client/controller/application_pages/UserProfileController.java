package client.controller.application_pages;

import client.controller.LoginRegisterController;
import client.model.LoginRegisterModel;
import client.model.application_pages.UserProfileModel;
import client.view.ApplicationView;
import client.view.application_pages.HistoryPageView;
import client.view.application_pages.UserProfileView;
import utilities.Resources;

import javax.swing.*;
import java.awt.*;
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
    private UserProfileView view;
    /**
     * The model LoginRegisterModel object.
     */
    private UserProfileModel model;
    /**
     * The array for Cars Panel holding the user's cars.
     */
    private UserProfileView.EditCars.CarsPanel[] pnlsCars;

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
        populateFields(); // populate fields of the edit profile text fields.
        model.getVehiclesInfo();

        pnlsCars = new UserProfileView.EditCars.CarsPanel[model.getVehicleList().size()];

        // populate cars panel inside edit cars page.
        for (int i = 0; i < model.getVehicleList().size(); i++) {
            String token = model.getVehicleList().get(i);
            String[] tokens = token.split(",");
            String vPlateNumber = tokens[0];
            String vType = tokens[1];
            String vModel = tokens[2];

            UserProfileView.EditCars.CarsPanel carPanel = new UserProfileView.EditCars.CarsPanel(vPlateNumber, vType, vModel);
            pnlsCars[i] = carPanel;

            view.getPnlEditCars().getPnlCards().add(carPanel, String.valueOf(i));
            view.getPnlEditCars().getCardLayout().show(view.getPnlEditCars().getPnlCards(), String.valueOf(i));
        }
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
        view.getPnlEditProfile().setContinueListener(new ProfileEditListener());
        view.getPnlEditProfile().setCancelListener(new ProfileCancelListener());

        // edit cars page
        view.getPnlEditCars().setContinueListener(new CarContinueListener());
        view.getPnlEditCars().setCancelListener(new CarCancelListener());
        view.getPnlEditCars().setNextListener(e ->
                view.getPnlEditCars().getCardLayout().next(view.getPnlEditCars().getPnlCards()));
        view.getPnlEditCars().setPrevListener(e ->
                view.getPnlEditCars().getCardLayout().previous(view.getPnlEditCars().getPnlCards()));

        for (UserProfileView.EditCars.CarsPanel panel : pnlsCars) {
            panel.setEditListener(e -> {
                panel.getTxtPlateNumber().setEditable(true);
                panel.getTxtPlateNumber().setFocusable(true);
                panel.getTxtModel().setEditable(true);
                panel.getTxtModel().setFocusable(true);
                view.getPnlEditCars().getBtnContinue().setVisible(true);
                view.getPnlEditCars().getBtnCancel().setVisible(true);
            });
        }

        // history page
        // TODO: action listeners for history page

        // security page
        view.getPnlSecurityPage().setConfirmListener(new SecurityConfirmListener());

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
        view.getPnlEditCars().getBtnPrev().addMouseListener(new Resources.CursorChanger(view.getPnlEditCars().getBtnPrev()));
        view.getPnlEditCars().getBtnNext().addMouseListener(new Resources.CursorChanger(view.getPnlEditCars().getBtnNext()));
        view.getPnlEditCars().getBtnContinue().addMouseListener(new Resources.CursorChanger(view.getPnlEditCars().getBtnContinue()));
        view.getPnlEditCars().getBtnCancel().addMouseListener(new Resources.CursorChanger(view.getPnlEditCars().getBtnCancel()));
        for (UserProfileView.EditCars.CarsPanel panel : pnlsCars) {
            panel.getBtnEdit().addMouseListener(new Resources.CursorChanger(panel.getBtnEdit()));
        }

        // history page
        // TODO: mouse listeners for history page

        // security page
        view.getPnlSecurityPage().getBtnConfirm().addMouseListener(
                new Resources.CursorChanger(view.getPnlSecurityPage().getBtnConfirm()));

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
        for (UserProfileView.EditCars.CarsPanel panel : pnlsCars) {
            String initialPlateNumber = panel.getTxtPlateNumber().getText();
            String initialModel = panel.getTxtPlateNumber().getText();

            panel.getTxtPlateNumber().addFocusListener(new Resources.TextFieldFocus(
                    panel.getTxtPlateNumber(), initialPlateNumber));
            panel.getTxtModel().addFocusListener(new Resources.TextFieldFocus(
                    panel.getTxtModel(), initialModel));
        }

        // security page
        view.getPnlSecurityPage().getTxtCurrentPassword().
                addFocusListener(new Resources.PasswordFocus(
                        view.getPnlSecurityPage().getTxtCurrentPassword(), "Current Password"
                ));
        view.getPnlSecurityPage().getTxtNewPassword().
                addFocusListener(new Resources.PasswordFocus(
                        view.getPnlSecurityPage().getTxtNewPassword(), "New Password"
                ));
        view.getPnlSecurityPage().getTxtConfirmNewPassword().
                addFocusListener(new Resources.PasswordFocus(
                        view.getPnlSecurityPage().getTxtConfirmNewPassword(), "Confirm New Password"
                ));

        // history page

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
    class ProfileCancelListener implements ActionListener {
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
    public class ProfileEditListener implements ActionListener {
        /**
         * TODO: Documentation
         *
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

    /**
     * Cancels the editing of the car information.
     */
    class CarCancelListener implements ActionListener {
        /**
         * Processes the user request.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getPnlEditCars().getBtnCancel().setVisible(false);
            view.getPnlEditCars().getBtnContinue().setVisible(false);
            for (UserProfileView.EditCars.CarsPanel panel : pnlsCars) {
                panel.getTxtPlateNumber().setEditable(false);
                panel.getTxtPlateNumber().setFocusable(false);
                panel.getTxtModel().setEditable(false);
                panel.getTxtModel().setFocusable(false);
            }
        }
    }

    /**
     * Sends the information of the edited car information.
     */
    class CarContinueListener implements ActionListener {
        /**
         * Processes the user request.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String vType = null;
            String vPlateNumber = null;
            String vModel = null;

            for (UserProfileView.EditCars.CarsPanel panel : pnlsCars) {
                vType = panel.getTxtVehicleType().getText();
                vPlateNumber = panel.getTxtPlateNumber().getText();
                vModel = panel.getTxtModel().getText();
            }
            model.editVehicleInfo(vType, vModel, vPlateNumber);
        }
    }

    /**
     * Updates the user's password.
     */
    class SecurityConfirmListener implements ActionListener {
        /**
         * 1. Verifies if the input equates to the original password.
         * 2. Verifies if the new password and the confirmed new password matches.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!LoginRegisterModel.encryptPassword(view.getPnlSecurityPage().getCurrentPassword()).equals(model.getPassword())) {
                view.getPnlSecurityPage().getLblMessage().setText("Current password does not match. Try again.");
                view.getPnlSecurityPage().getLblMessage().setForeground(Color.RED);
            } else {
                if (!LoginRegisterModel.verifySignupPassword(
                        view.getPnlSecurityPage().getNewPassword(), view.getPnlSecurityPage().getConfirmNewPassword())) {
                    view.getPnlSecurityPage().getLblMessage().setText("New passwords do not match. Try again.");
                    view.getPnlSecurityPage().getLblMessage().setForeground(Color.RED);
                } else {
                    model.editPassword(LoginRegisterModel.encryptPassword(view.getPnlSecurityPage().getNewPassword()));
                    view.getPnlSecurityPage().getLblMessage().setText("Your password has been successful changed.");
                    view.getPnlSecurityPage().getLblMessage().setForeground(Color.green);
                    model.editPassword(view.getPnlSecurityPage().getNewPassword());
                }
            }
        }
    }
}

