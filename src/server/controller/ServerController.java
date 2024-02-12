package server.controller;

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
    Server server;
    ServerStatusView serverStatusView;

    boolean serverStatus = false;

    final int address = 2020;


    public ServerController(ServerStatusView serverStatusView) throws Exception{
        this.server = new Server(address);
        server.startServer();

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

    public static void main(String[] args) throws Exception {
        ServerStatusView view = new ServerStatusView();
        ServerController controller = new ServerController(view);
    }
}
