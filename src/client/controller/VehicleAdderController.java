package client.controller;

import client.model.VehicleAdderModel;
import client.view.VehicleAdderView;

/**
 * Template for VehicleAdderController.
 * The VehicleAdderController processes the user requests for adding a vehicle.
 * Based on the user request, the VehicleAdderController defines methods and invokes methods in the View
 * and Model to accomplish the requestion action.
 */
public class VehicleAdderController {
    /**
     * The view VehicleAdderView object.
     */
    private VehicleAdderView view;
    /**
     * The model VehicleAdderModel object.
     */
    private VehicleAdderModel model;

    public VehicleAdderController(VehicleAdderView view, VehicleAdderModel model) {
        this.view = view;
        this.model = model;

        //

        // action listeners

        // mouse listeners

        // focus listeners
    }
}
