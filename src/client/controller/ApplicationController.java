package client.controller;

import client.controller.application_pages.ReservationPageController;
import client.model.ApplicationModel;
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
     * Constructs an ApplicationController with a specified view and model.
     * @param view The specified ApplicationView.
     * @param model The specified ApplicationModel.
     */
    public ApplicationController(ApplicationView view, ApplicationModel model) {
        this.view = view;
        this.model = model;

        // constants / variables
        ReservationPageController reservationPageController =
                new ReservationPageController(view.getReservationPageView(), model.getReservationPageModel());

        // action listeners

        // mouse listeners

        // focus listeners
    }
}
