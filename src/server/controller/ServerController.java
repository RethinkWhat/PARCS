package server.controller;

import server.view.AdminApplicationView;
import server.view.ServerStatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Controller for server
 * Assigned to @Ramon Jasmin
 * */
public class ServerController {
    private Server server;
    private AdminApplicationView view;
    boolean serverStatus = false;
    final int address = 2020;

    public ServerController(AdminApplicationView view){
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
        view.getServerStatusView().setServerListener(new serverListener());
        view.setNavStatusListener(e -> view.getMainCardLayout().show(view.getPnlCards(), "status"));
        view.setNavDashboardListener(e -> view.getMainCardLayout().show(view.getPnlCards(), "dashboard"));

        // dashboard page

        // mouse listeners

        // server status page

        // dashboard page

        // focus listeners
    }


    class serverListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!serverStatus){
                view.getServerStatusView().setOnline();
                serverStatus = true;
                server.startAccepting();

            }else {
                view.getServerStatusView().setOffline();
                serverStatus= false;
                server.stopAccepting();
            }

        }
    }

    public static void main(String[] args) {
        ServerController controller = new ServerController(new AdminApplicationView());
    }
}
