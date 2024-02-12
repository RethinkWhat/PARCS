package client.controller;

import client.controller.application_pages.ReservationPageController;
import client.controller.application_pages.UserProfileController;
import client.model.ApplicationModel;
import client.model.Client;
import client.model.application_pages.ReservationPageModel;
import client.model.application_pages.UserProfileModel;
import client.view.ApplicationView;
import client.view.application_pages.ReservationPageView;
import client.view.application_pages.UserProfileView;

/**
 * Template for ApplicationController object.
 * The ApplicationController processes the user requests. Based on the user request, the ApplicationController
 * defines methods and invokes methods in the View and Model to accomplish the requested action.
 */
public class ApplicationController {
    /**
     * The view ApplicationView object.
     */
    private ApplicationView view;
    /**
     * The model ApplicationModel model.
     */
    private ApplicationModel model;

    /**
     * Constructs an ApplicationController with a specified view and model.
     * @param view The specified ApplicationView.
     * @param model The specified ApplicationModel.
     */
    public ApplicationController(ApplicationView view, ApplicationModel model) {
        this.view = view;
        this.model = model;

        // constants / variables
        view.getLblLocation().setText("Home");
        new ReservationPageController(view.getReservationPageView(), model.getReservationPageModel());
        new UserProfileController(view.getUserProfileView(), model.getUserProfileModel());

        // action listeners
        view.setNavHomeListener(e -> {
            view.getLblLocation().setText("Home");
            view.getMainCardLayout().show(view.getPnlCards(), "home");
        });
        view.setNavTicketListener(e -> {
            view.getLblLocation().setText("Ticket");
            // TODO: change panel
        });
        view.setNavAccountListener(e -> {
            view.getLblLocation().setText("Account");
            view.getMainCardLayout().show(view.getPnlCards(), "account");
        });
        view.setNavLogoutListener(e -> {
            view.dispose();
            model.getClient().logout();
        });

        // mouse listeners

        // focus listeners
    }
}
