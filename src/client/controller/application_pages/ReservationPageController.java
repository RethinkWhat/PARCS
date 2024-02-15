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

    private String btnDuration;

    private String date;

    private String[] timeAvailable;

    private String dateChosen;

    private String[] dateList;

    /**
     * Constructs a ReservationPageController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public ReservationPageController(ReservationPageView view, ReservationPageModel model) {
        this.view = view;
        this.model = model;
        date =model.getDate();
        dateList = model.getDateList();
        view.setUserFullName(model.getFullName());

        // constants/variables
        view.getLblDate().setText(model.getTime());
        // updates the time every second.
        timer = new Timer(1000,e -> updateTime());
        timer.start();

        view.getMainBottomPanel().getParkingSlotsPanel().setCarButtonsListener(new CarMotorListener());
        view.getParkingSlotButtonsView().setBtnCloseListener(new exitListener());
        view.getParkingSlotButtonsView().setReserveSlotListener(new reserveSlotListener());
        view.getParkingSlotButtonsView().setDateListener(new DateListener());
        view.getParkingSlotButtonsView().setDurationListener(new durationListener());
        view.getMainTopPanel().setTxtSearchBarListener(new searchListener());

        view.getMainTopPanel().setPnlAvailMotor(model.getAvailMotorSlots());
        view.getMainTopPanel().setPnlAvailCar(model.getAvailCarSlots());
        view.getMainTopPanel().setPnlTotalBookings(model.getTotalBookings());

        view.getParkingSlotButtonsView().setDateList(dateList);




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

    class DateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tempDate = view.getParkingSlotButtonsView().getDateChosen();
            if (!tempDate.equals("Select Date:")) {
                date = tempDate;
                view.getParkingSlotButtonsView().setLblDate(date);
            }
        }
    }

    class CarMotorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.getTopCardLayout().show(view.getPnlCards(), "buttons");

            CarMotorButton buttonClicked = (CarMotorButton) e.getSource();
            btnID = buttonClicked.getIdentifier();

            view.getParkingSlotButtonsView().setLblDate(date);


            if (btnID.contains("C")) {
                view.getParkingSlotButtonsView().setVehiclesList(model.getCars());
                view.getParkingSlotButtonsView().setLblType("Car");
            } else {
                view.getParkingSlotButtonsView().setVehiclesList(model.getMotorcycles());
                view.getParkingSlotButtonsView().setLblType("Motor");
            }

            view.getParkingSlotButtonsView().setLblSlotNumber(btnID);
            view.getParkingSlotButtonsView().setLblDate(date);
        }
    }

    class durationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getParkingSlotButtonsView().setLblDate(date);
            String duration = view.getParkingSlotButtonsView().getDurationChosen();
            if (!duration.equals("Duration:")) {
                timeAvailable = model.getAvailableTime(btnID, duration, date);
                view.getParkingSlotButtonsView().setTimeList(timeAvailable);
                if (timeAvailable != null)
                    view.getParkingSlotButtonsView().setLblStatus("Available");
            }

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
            String duration = view.getParkingSlotButtonsView().getDurationChosen();
                if (startTime != null && duration != null) {
                    model.attemptBooking(btnID, date, startTime, duration);
                    view.getParkingSlotButtonsView().resetDuration();
                    timeAvailable = model.getAvailableTime(btnID, duration, date);
                    System.out.println(timeAvailable);
                    view.getParkingSlotButtonsView().setTimeList(timeAvailable);
                    if (timeAvailable != null)
                        view.getParkingSlotButtonsView().setLblStatus("Available");
                    view.getReserveSlotConfirmationView();
                }
        }
    }

    class searchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String search = ((JTextField) e.getSource()).getText();
            String identifier = model.findAvailableSlotOnDay(search);
            view.getTopCardLayout().show(view.getPnlCards(), "buttons");

            if (identifier.contains("C")) {
                view.getParkingSlotButtonsView().setVehiclesList(model.getCars());
                view.getParkingSlotButtonsView().setLblType("Car");
            } else {
                view.getParkingSlotButtonsView().setVehiclesList(model.getMotorcycles());
                view.getParkingSlotButtonsView().setLblType("Motor");
            }

            view.getParkingSlotButtonsView().setLblSlotNumber(identifier);

        }
    }
}

