package server.controller;

import server.view.ServerStatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/** Controller for server
 * Assigned to @Ramon Jasmin
 * */
public class ServerController {
    Server server;
    ServerStatusView serverStatusView;

    boolean serverStatus = false;

    public ServerController(Server server, ServerStatusView serverStatusView){
        this.server = server;
        this.serverStatusView = serverStatusView;

        serverStatusView.setServerListener(new serverListener());
    }

    class serverListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {



            if (!serverStatus){
                serverStatusView.setOnline();
                serverStatus = true;
            }else {

                serverStatusView.setOffline();
                serverStatus= false;
            }

        }
    }

    public static void main(String[] args) {
        ServerStatusView view = new ServerStatusView();
    //    ServerController controller = new ServerController(server, view);

    }
}
