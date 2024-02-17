package server.controller;

import server.model.ReservationParser;
import server.model.Server;
import server.view.AdminApplicationView;
import server.view.DashboardView;
import utilities.Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Proc
 *
 */
public class AdminApplicationController {
    /**
     * The object of server.
     */
    private Server server;
    /**
     * The view
     */
    private AdminApplicationView view;
    /**
     * The status of the server.
     * True if online, false if offline.
     */
    boolean serverStatus = false;
    /**
     * The default port of the server.
     */
    final int address = 2040;
    /**
     * Instance variable of reservation parser.
     */
    private static ReservationParser reservationParser = new ReservationParser();
    /**
     * List of lists of car bookings.
     */
    private volatile List<List<String>> carBookings;
    /**
     * List of lists of motor bookings.
     */
    private volatile List<List<String>> motorBookings;

    /**
     * Constructs a ServerController with a specified AdminApplicationView.
     * @param view The specified AdminApplicationView.
     */
    public AdminApplicationController(AdminApplicationView view){
        try {
            this.server = new Server(address);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Thread thread = new Thread(server);
        thread.start();

        this.view = view;

        // constants / variables
        List<List<String>> carBookings = reservationParser.getAllCarBookings();
        List<List<String>> motorBookings = reservationParser.getAllMotorBookings();

        // create record panels of car bookings
        DashboardView.MainBottomPanel.RecordPanel[] carRecords = new DashboardView.MainBottomPanel.RecordPanel[carBookings.size()];
        for (DashboardView.MainBottomPanel.RecordPanel recordPanel : carRecords) {
            for (List<String> booking : carBookings) {
                String[] tokens = booking.toString().replace("[","").replace("]","").split(",");
                String username = tokens[0];
                String spot = tokens[1];
                String date = tokens[2];
                String timeIn = tokens[3];
                String timeOut = tokens[4];
                String duration = tokens[5];

                DashboardView.MainBottomPanel.RecordPanel record = new DashboardView.MainBottomPanel.RecordPanel();
                record.getLblUsername().setText(username);
                record.getLblSlot().setText(spot);
                record.getLblDate().setText(date);
                record.getLblCheckIn().setText(timeIn);
                record.getLblCheckOut().setText(timeOut);
                record.getLblDuration().setText(duration);
                record.getLblVehicle().setText("Car");

                view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().
                        getPnlCards().add(record, String.valueOf(recordPanel));
                view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().
                        getCardLayout().show(view.getDashboardView().
                                getPnlMainBottom().getPnlCompletedCar().getPnlCards(), String.valueOf(recordPanel));
            }
        }

        // create record panels of motor bookings
        DashboardView.MainBottomPanel.RecordPanel[] motorRecords = new DashboardView.MainBottomPanel.RecordPanel[motorBookings.size()];
        for (DashboardView.MainBottomPanel.RecordPanel recordPanel : motorRecords) {
            for (List<String> booking : motorBookings) {
                String[] tokens = booking.toString().replace("[","").replace("]","").split(",");
                String username = tokens[0];
                String spot = tokens[1];
                String date = tokens[2];
                String timeIn = tokens[3];
                String timeOut = tokens[4];
                String duration = tokens[5];

                DashboardView.MainBottomPanel.RecordPanel record = new DashboardView.MainBottomPanel.RecordPanel();
                record.getLblUsername().setText(username);
                record.getLblSlot().setText(spot);
                record.getLblDate().setText(date);
                record.getLblCheckIn().setText(timeIn);
                record.getLblCheckOut().setText(timeOut);
                record.getLblDuration().setText(duration);
                record.getLblVehicle().setText("Motor");

                view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().
                        getPnlCards().add(record, String.valueOf(recordPanel));
                view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().
                        getCardLayout().show(view.getDashboardView().
                                getPnlMainBottom().getPnlCompletedMotor().getPnlCards(), String.valueOf(recordPanel));
            }
        }

        // action listeners

        // server status page
        view.getServerStatusView().setServerListener(new ServerListener());
        view.setNavStatusListener(e -> {
            view.getMainCardLayout().show(view.getPnlCards(), "status");
            view.getLblLocation().setText("Server Status");
        });
        view.setNavDashboardListener(e -> {
            view.getMainCardLayout().show(view.getPnlCards(), "dashboard");
            view.getLblLocation().setText("Dashboard");
        });

        // dashboard page
        view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().setNextListener(e -> {
            view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().getCardLayout().next(
                    view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().getPnlCards());
        });
        view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().setPrevListener(e -> {
            view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().getCardLayout().previous(
                    view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().getPnlCards());
        });

        view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().setNextListener(e -> {
            view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().getCardLayout().next(
                    view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().getPnlCards());
        });
        view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().setPrevListener(e -> {
            view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().getCardLayout().previous(
                    view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().getPnlCards());
        });

        // mouse listeners

        // server status page
        view.getServerStatusView().getServerSwitch().addMouseListener(
                new Resources.CursorChanger(view.getServerStatusView().getServerSwitch()));

        // dashboard page

        view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().getBtnNext().addMouseListener(
                new Resources.CursorChanger(view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().getBtnNext()));
        view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().getBtnPrev().addMouseListener(
                new Resources.CursorChanger(view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().getBtnPrev()));
        view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().getBtnNext().addMouseListener(
                new Resources.CursorChanger(view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().getBtnNext()));
        view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().getBtnPrev().addMouseListener(
                new Resources.CursorChanger(view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().getBtnPrev()));
    }

    /**
     * TODO: Documentation
     */
    class ServerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!serverStatus) {
                view.getServerStatusView().setOnline();
                serverStatus = true;

                // Create and start a new thread for the server
                Thread thread = new Thread(server);
                thread.start();
            } else {
                view.getServerStatusView().setOffline();
                serverStatus = false;

                // Stop the server logic
                server.stopAccepting();
            }
        }
    }

    private void refreshBookings() {
        carBookings = reservationParser.getAllCarBookings();
        motorBookings = reservationParser.getAllMotorBookings();
    }

    /**
     * Main entry point of the program.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new AdminApplicationController(new AdminApplicationView());

        List<List<String>> carBookings = reservationParser.getAllCarBookings();
        for (List<String> car : carBookings) {
            System.out.println(car);
        }
        List<List<String>> motorBookings = reservationParser.getAllMotorBookings();
    }
}
