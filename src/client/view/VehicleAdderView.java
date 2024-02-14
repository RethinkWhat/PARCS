package client.view;

import utilities.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Template for object VehicleAdderView.
 * The VehicleAdderView is the page to add the user's vehicles of the client application.
 */
public class VehicleAdderView extends JFrame {
    /**
     * The text field for the vehicle type.
     */
    private JComboBox<?> cmbType;
    /**
     * The text field for the vehicle model.
     */
    private JTextField txtModel;
    /**
     * The text field for the vehicle plate number.
     */
    private JTextField txtPlateNumber;
    /**
     * The button to add and confirm the vehicle being added.
     */
    private JButton btnAddVehicle;
    /**
     * The button to cancel and close the frame.
     */
    private JButton btnCancel;
    /**
     * The stylesheet.
     */
    private Resources res = new Resources();

    /**
     * Constructs the VehicleAdderView frame.
     */
    public VehicleAdderView() {
        super("Add Vehicle");

        // Body panel acting as a container to hold all UI components
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        // GridBagConstraints to position components using the GB layout
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 40;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);
        JLabel lblTitle = res.createLblH1("Add Vehicle", res.eerieBlack);
        contentPane.add(lblTitle);

        gbc.gridy = 1;
        gbc.ipady = 3;
        cmbType = res.createCmbRounded(res.lightGray,res.eerieBlack,0);
        contentPane.add(cmbType, gbc);

        gbc.gridy = 2;
        gbc.ipady = 3;
        txtModel = res.createTxtRounded("Vehicle Model (e.g., Honda Civic)", res.white, res.gray, 30);
        contentPane.add(txtModel, gbc);

        gbc.gridy = 3;
        gbc.ipady = 3;
        txtPlateNumber = res.createTxtRounded("License Plate Number (e.g., NWA 991)", res.white,
                res.gray, 30);
        contentPane.add(txtPlateNumber, gbc);

        gbc.insets = new Insets(10,10,10,10);

        gbc.gridy = 4;
        JPanel pnlButtons = new JPanel(new FlowLayout());
        contentPane.add(pnlButtons, gbc);

        btnAddVehicle = res.createBtnRounded("Add Vehicle",res.celadon, res.eerieBlack,10);
        pnlButtons.add(btnAddVehicle);

        btnCancel = res.createBtnRounded("Cancel",res.gray, res.eerieBlack,10);
        pnlButtons.add(btnCancel);

        this.setContentPane(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(600,400);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void setAddVehicleListener(ActionListener actionListener) {
        btnAddVehicle.addActionListener(actionListener);
    }

    public void setCancelListener(ActionListener actionListener) {
        btnCancel.addActionListener(actionListener);
    }

    public JComboBox<?> getCmbType() {
        return cmbType;
    }

    public JTextField getTxtModel() {
        return txtModel;
    }

    public JTextField getTxtPlateNumber() {
        return txtPlateNumber;
    }

    public String getVehicleType() {
        return cmbType.getSelectedItem().toString();
    }


    public String getModel() {
        return txtModel.getText();
    }
    public String getPlateNumber() {
        return txtPlateNumber.getText();
    }


    public JButton getBtnAddVehicle() {
        return btnAddVehicle;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    // Temporary main method to view and debug frame
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VehicleAdderView();
            }
        });
    }
}
