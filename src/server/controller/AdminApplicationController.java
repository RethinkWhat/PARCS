package server.controller;

import server.model.Server;
import server.view.AdminApplicationView;
import server.view.DashboardView;
import utilities.Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Processes the server initialization and viewing of pertinent activities in the server.
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
    /**
     * List of lists of car bookings.
     */
    private volatile List<List<String>> carBookings = new ArrayList<>();
    /**
     * List of lists of motor bookings.
     */
    private volatile List<List<String>> motorBookings = new ArrayList<>();
    /**
     * Array of RecordPanel to hold the car records.
     */
    private volatile DashboardView.MainBottomPanel.RecordPanel[] carRecords;
    /**
     * Array of RecordPanel to hold the motor records.
     */
    private volatile DashboardView.MainBottomPanel.RecordPanel[] motorRecords;

    private Thread threadServer = new Thread(server);;

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
        refreshBookings();

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
        view.getDashboardView().setRefreshListener(e -> refreshBookings());

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
        view.getBtnNavDashboard().addMouseListener(new Resources.CursorChanger(view.getBtnNavDashboard()));
        view.getBtnNavStatus().addMouseListener(new Resources.CursorChanger(view.getBtnNavStatus()));

        // server status page
        view.getServerStatusView().getServerSwitch().addMouseListener(
                new Resources.CursorChanger(view.getServerStatusView().getServerSwitch()));

        // dashboard page
        view.getDashboardView().getBtnRefresh().addMouseListener(new Resources.CursorChanger(view.getDashboardView().getBtnRefresh()));

        view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().getBtnNext().addMouseListener(
                new Resources.CursorChanger(view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().getBtnNext()));
        view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().getBtnPrev().addMouseListener(
                new Resources.CursorChanger(view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().getBtnPrev()));
        view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().getBtnNext().addMouseListener(
                new Resources.CursorChanger(view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().getBtnNext()));
        view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().getBtnPrev().addMouseListener(
                new Resources.CursorChanger(view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().getBtnPrev()));

        view.repaint();
        view.revalidate();
    }

    /**
     * Processes the initializing and disabling the server.
     */
    class ServerListener implements ActionListener {
        /**
         * Turns the server on and/or off.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!serverStatus) {
                view.getServerStatusView().setOnline();
                serverStatus = true;

                // Create and start a new thread for the server
                threadServer.start();
            } else {
                view.getServerStatusView().setOffline();
                serverStatus = false;
                threadServer.interrupt();

                // Stop the server logic
                server.stopAccepting();
            }
        }
    }

    /**
     * Populates the list of car bookings and motor bookings.
     */
    private void refreshBookings() {
        // clears the lists
        carBookings.clear();
        motorBookings.clear();

        // populate / repopulate lists
        carBookings = server.getAllCarBookings();
        motorBookings = server.getAllMotorBookings();

        view.getDashboardView().getPnlMainTop().getLblCarCount().setText(String.valueOf(carBookings.size()));
        view.getDashboardView().getPnlMainTop().getLblMotorCount().setText(String.valueOf(motorBookings.size()));
        view.getDashboardView().getPnlMainTop().getLblTotalCount().
                setText(String.valueOf(carBookings.size() + motorBookings.size()));


        // create / recreate record panels of car bookings
        carRecords = new DashboardView.MainBottomPanel.RecordPanel[carBookings.size()];
        Arrays.fill(carRecords, null);
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
                record.getLblSlot().setText("SLOT " + spot);
                record.getLblDate().setText(date);
                record.getLblCheckIn().setText(timeIn);
                record.getLblCheckOut().setText(timeOut);
                record.getLblDuration().setText(duration + " hour/s");
                record.getLblVehicle().setText("Car");

                
                view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().
                        getPnlCards().add(record, String.valueOf(recordPanel));
                view.getDashboardView().getPnlMainBottom().getPnlCompletedCar().
                        getCardLayout().show(view.getDashboardView().
                                getPnlMainBottom().getPnlCompletedCar().getPnlCards(), String.valueOf(recordPanel));
            }
        }

        // create record panels of motor bookings
        motorRecords = new DashboardView.MainBottomPanel.RecordPanel[motorBookings.size()];
        Arrays.fill(motorRecords, null);
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
                record.getLblSlot().setText("SLOT " + spot);
                record.getLblDate().setText(date);
                record.getLblCheckIn().setText(timeIn);
                record.getLblCheckOut().setText(timeOut);
                record.getLblDuration().setText(duration + " hour/s");
                record.getLblVehicle().setText("Motor");

                view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().
                        getPnlCards().add(record, String.valueOf(recordPanel));
                view.getDashboardView().getPnlMainBottom().getPnlCompletedMotor().
                        getCardLayout().show(view.getDashboardView().
                                getPnlMainBottom().getPnlCompletedMotor().getPnlCards(), String.valueOf(recordPanel));
            }
        }
    }
}
