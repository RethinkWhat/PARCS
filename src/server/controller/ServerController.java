package server.controller;

import server.view.ServerStatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Controller for server
 * Assigned to @Ramon Jasmin
 * */
public class ServerController {
    Server server;
    ServerStatusView serverStatusView;

    public ServerController(Server server, ServerStatusView serverStatusView){
        this.server = server;
        this.serverStatusView = serverStatusView;

        serverStatusView.setServerListener(new serverListener());
    }

    class serverListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
