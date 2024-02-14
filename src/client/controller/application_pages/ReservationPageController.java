package client.controller.application_pages;

import client.model.application_pages.CarMotorButton;
import client.model.application_pages.ReservationPageModel;
import client.view.application_pages.ReservationPageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private String btnID;

    private String date ="02/14/24";

    private String[] timeAvailable;

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

        view.getMainBottomPanel().getParkingSlotsPanel().setCarButtonsListener(new CarMotorListener());
        view.getParkingSlotButtonsView().setBtnCloseListener(new exitListener());
        view.getParkingSlotButtonsView().setReserveSlotListener(new reserveSlotListener());



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

    class CarMotorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.getTopCardLayout().show(view.getPnlCards(), "buttons");

            CarMotorButton buttonClicked = (CarMotorButton) e.getSource();
            btnID = buttonClicked.getIdentifier();
            //TODO: Fix date implementation

            timeAvailable = model.getAvailableTime(btnID, date);

            if (timeAvailable != null) {
                view.getParkingSlotButtonsView().setLblStatus("Available");
            }

            view.getParkingSlotButtonsView().setLblDate(date);


            if (btnID.contains("C")) {
                view.getParkingSlotButtonsView().setVehiclesList(model.getCars());
                view.getParkingSlotButtonsView().setLblType("Car");
            } else {
                view.getParkingSlotButtonsView().setVehiclesList(model.getMotorcycles());
                view.getParkingSlotButtonsView().setLblType("Motor");
            }

            view.getParkingSlotButtonsView().setLblSlotNumber(btnID);
            view.getParkingSlotButtonsView().setTimeList(timeAvailable);
        }
    }

    class exitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getTopCardLayout().show(view.getPnlCards(), "dashboard");
        }
    }

    class reserveSlotListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String startTime = view.getParkingSlotButtonsView().getStartTime();
            String duration = view.getParkingSlotButtonsView().getDuration();
            if (startTime != null && duration != null) {
                model.attemptBooking(btnID, date, startTime, duration);
            }
        }
    }
}

