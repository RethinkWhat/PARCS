package server.controller;

import server.model.Server;
import server.view.AdminApplicationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    final int address = 2020;

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

        // mouse listeners


        // server status page
        // TODO

        // dashboard page
        // TODO
    }

    /**
     * TODO: Documentation
     */
    class ServerListener implements ActionListener{
        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!serverStatus){
                view.getServerStatusView().setOnline();
                serverStatus = true;
                ExecutorService executor = Executors.newFixedThreadPool(10);
                server.startAccepting();
                executor.submit(server);
            }else {
                view.getServerStatusView().setOffline();
                serverStatus= false;
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
