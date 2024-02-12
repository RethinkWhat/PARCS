package server.controller;

import server.model.ServerStatusModel;
import server.view.ServerStatusView;

public class ServerStatusController {

    /** The View associated with the ServerStatusController */
    ServerStatusView view;

    /** The model of the ServerStatusController */
    ServerStatusModel model;

    /**
     * Constructor for serverStatus
     * @param view : ServerStatusView
     * @param model : ServerStatusModel
     */
    public ServerStatusController(ServerStatusView view, ServerStatusModel model) {
        this.view = view;
        this.model = model;
    }
}
