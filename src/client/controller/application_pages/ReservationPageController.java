package client.controller.application_pages;

import client.model.application_pages.ReservationPageModel;
import client.view.application_pages.ReservationPageView;

import javax.swing.*;

/**
 * The ReservationPageController processes the user requests for reserving a parking slot.
 * Based on the user request, the ReservationPageController defines methods and invokes methods in the View and Model
 * to accomplish the requested action.
 */
public class ReservationPageController {
    /**
     * The view ReservationPageView.
     */
    private ReservationPageView view;
    /**
     * The model ReservationPageModel.
     */
    private ReservationPageModel model;
    /**
     * The timer to delay UI components.
     */
    private Timer timer;

    /**
     * Constructs a ReservationPageController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public ReservationPageController(ReservationPageView view, ReservationPageModel model) {
        this.view = view;
        this.model = model;

        view.setUserFullName(model.getFullName());

        // constants/variables
        view.getLblDate().setText(model.getTime());
        // updates the time every second.
        timer = new Timer(1000,e -> updateTime());
        timer.start();

        // action listeners


        // mouse listeners

        // focus listeners
    }

    /**
     * Updates the time by refreshing the components of the UI.
     */
    private void updateTime() {
        SwingUtilities.invokeLater(() -> {
            String time = model.getTime();
            view.getLblDate().setText(time);
        });
    }
}

