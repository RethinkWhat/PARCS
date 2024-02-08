package server.controller;

import server.view.ServerStatusView;

/** Controller for server
 * Assigned to @Ramon Jasmin
 * */
public class ServerController {
    Server server;
    ServerStatusView serverStatusView;

    public ServerController(Server server, ServerStatusView serverStatusView){
        this.server = server;
        this.serverStatusView = serverStatusView;
    }
}
