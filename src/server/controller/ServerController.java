package server.controller;

import server.model.ServerStatusModel;
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

    /** The View associated with the ServerStatusController */
    ServerStatusView serverStatusView;

    /** The model of the ServerStatusController */
    ServerStatusModel model;

    boolean serverStatus = false;


    /** The server object */
    Server server;


    public ServerController(ServerStatusView serverStatusView, ServerStatusModel model){
            this.model = model;
            this.server = model.getServer();

        Thread thread = new Thread(server);
        thread.start();

        this.serverStatusView = serverStatusView;
        serverStatusView.setServerListener(new serverListener());
    }



    class serverListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!serverStatus){
                serverStatusView.setOnline();
                serverStatus = true;
                server.startAccepting();

            }else {
                serverStatusView.setOffline();
                serverStatus= false;
                server.stopAccepting();
            }

        }
    }

    public static void main(String[] args) {
        ServerStatusView view = new ServerStatusView();
        ServerStatusModel model = new ServerStatusModel();
        ServerController controller = new ServerController(view, model);
    }
}
