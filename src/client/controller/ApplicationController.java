package client.controller;

import client.controller.application_pages.ReservationPageController;
import client.model.ApplicationModel;
import client.model.Client;
import client.view.ApplicationView;

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
     * A reference to the client logged in
     */


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
        ReservationPageController reservationPageController =
                new ReservationPageController(view.getReservationPageView(), model.getReservationPageModel());

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
            // TODO: change panel
        });
        view.setNavLogoutListener(e -> {
            // TODO: add functions here to save user actions and send to server.
            view.dispose();
        });

        // mouse listeners

        // focus listeners
    }
}
