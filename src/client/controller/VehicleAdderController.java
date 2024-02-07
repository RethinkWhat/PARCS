package client.controller;

import client.model.VehicleAdderModel;
import client.view.VehicleAdderView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Template for VehicleAdderController.
 * The VehicleAdderController processes the user requests for adding a vehicle.
 * Based on the user request, the VehicleAdderController defines methods and invokes methods in the View
 * and Model to accomplish the requested action.
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

    /**
     * Constructs a VehicleAdderController with a specified view and model.
     * @param view The specified VehicleAdderView.
     * @param model The specified VehicleAdderModel.
     */
    public VehicleAdderController(VehicleAdderView view, VehicleAdderModel model) {
        this.view = view;
        this.model = model;

        // model map
        view.getCmbType().setModel(new DefaultComboBoxModel(model.getVehicleTypes()));

        // action listeners
        view.setAddVehicleListener(new AddVehicleListener());
        view.setCancelListener(e -> view.dispose());

        // mouse listeners

        // focus listeners
    }

    /**
     * TODO: Documentation
     */
    class AddVehicleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO
        }
    }

    // Temporary main method
    public static void main(String[] args) {
        new VehicleAdderController(new VehicleAdderView(), new VehicleAdderModel());
    }
}
