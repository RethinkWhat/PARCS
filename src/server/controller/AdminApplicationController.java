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
    private ReservationParser reservationParser = new ReservationParser();

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

        DashboardView.MainBottomPanel.RecordPanel[] carRecords = new DashboardView.MainBottomPanel.RecordPanel[carBookings.size()];
        DashboardView.MainBottomPanel.RecordPanel[] motorRecords = new DashboardView.MainBottomPanel.RecordPanel[motorBookings.size()];

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
        // TODO

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

    /**
     * Main entry point of the program.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new AdminApplicationController(new AdminApplicationView());
    }
}
