package client.controller.application_pages;

import client.model.application_pages.ReservationPageModel;
import client.view.application_pages.ReservationPageView;

import javax.swing.*;
import java.sql.Time;

public class ReservationPageController {
    private ReservationPageView view;
    private ReservationPageModel model;
    private Timer timer;
    public ReservationPageController(ReservationPageView view, ReservationPageModel model) {
        this.view = view;
        this.model = model;

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
