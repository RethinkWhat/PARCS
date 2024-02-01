package client.view;

import javax.swing.*;
import java.awt.*;

/**
 * TODO: Documentation
 */
public class VehicleAdderView extends JFrame {
    /**
     * The text field for the vehicle type.
     */
    private JComboBox cmbType;
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
     * Constructs the VehicleAdderView frame.
     */
    public VehicleAdderView() {
        super("Add Vehicle");

        // Body panel acting as a container to hold all UI components
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        // GridBagConstraints to position components using the GB layout
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitle = new JLabel("Add Vehicle");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(0,25,50,50);
        contentPane.add(lblTitle);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(20,0,0,0);
        cmbType = new JComboBox();
        contentPane.add(cmbType, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0,0,0,0);
        txtModel = new JTextField(30);
        txtModel.setText("Vehicle Model (e.g., Honda Civic)");
        txtModel.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(txtModel, gbc);

        gbc.gridy = 3;
        txtPlateNumber = new JTextField(30);
        txtPlateNumber.setText("License Plate Number (e.g., NWA 991)");
        txtPlateNumber.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(txtPlateNumber, gbc);

        gbc.insets = new Insets(10,10,10,10);

        gbc.gridy = 4;
        JPanel pnlButtons = new JPanel(new FlowLayout());
        contentPane.add(pnlButtons, gbc);

        btnAddVehicle = new JButton("Add Vehicle");
        pnlButtons.add(btnAddVehicle);

        btnCancel = new JButton("Cancel");
        pnlButtons.add(btnCancel);

        this.setContentPane(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(600,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
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
