package client.view.application_pages;

import utilities.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ParkingSlotButtonsView extends JPanel {
    private JLabel lblSlotNumber;
    private JLabel lblType;
    private JLabel lblStatus;
    private JLabel lblDate;

    /**
     * The stylesheet.
     */
    private Resources res = new Resources();

    /**
     * Constructs a panel of ParkingSlotButtonsView
     */
    public ParkingSlotButtonsView() { // TODO: Replace constructors
        setBackground(res.white);
        setLayout(new GridLayout(3, 1)); // Divided into 3 rows, 1 column

        // pnlSlot
        JPanel pnlSlotNumber = new JPanel();
        pnlSlotNumber.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlSlotNumber.setPreferredSize(new Dimension(1300, 50));
        pnlSlotNumber.setBackground(Color.white);

        // Label for the slot number
        lblSlotNumber = res.createLblH1("SLOT A 01", res.eerieBlack);
        pnlSlotNumber.add(lblSlotNumber);

        add(pnlSlotNumber);

        // pnlSlotInformation
        JPanel pnlSlotInformation = new JPanel(new GridBagLayout());
        pnlSlotInformation.setPreferredSize(new Dimension(1300, 50));
        pnlSlotInformation.setBackground(Color.white);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Labels for Type
        JLabel lblTypeName = res.createLblH3("Type:", res.eerieBlack); // Used createLblH3 for distinction
        gbc.gridx = 0;
        pnlSlotInformation.add(lblTypeName, gbc);

        lblType = res.createLblP("Car", res.eerieBlack);
        gbc.gridx = 1;
        pnlSlotInformation.add(lblType, gbc);

        // Labels for Status
        JLabel lblStatName = res.createLblH3("Status:", res.eerieBlack); // Used createLblH3 for distinction
        gbc.gridx = 2;
        pnlSlotInformation.add(lblStatName, gbc);

        lblStatus = res.createLblP("Unavailable", res.eerieBlack);
        gbc.gridx = 3;
        pnlSlotInformation.add(lblStatus, gbc);

        // Labels for Date
        JLabel lblDateName = res.createLblH3("Date:", res.eerieBlack); // Used createLblH3 distinction
        gbc.gridx = 4;
        pnlSlotInformation.add(lblDateName, gbc);

        lblDate = res.createLblP("January 24, 2024", res.eerieBlack);
        gbc.gridx = 5;
        pnlSlotInformation.add(lblDate, gbc);

        add(pnlSlotInformation);

        // pnlReserve
        JPanel pnlReserve = new JPanel(new GridBagLayout());
        pnlReserve.setPreferredSize(new Dimension(1300, 100));
        pnlReserve.setBackground(Color.white);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Add space around components
        gbc.anchor = GridBagConstraints.CENTER; // Center components horizontally

        // Dropdown for Select Vehicle
        JComboBox<String> vehicleDropdown = new JComboBox<>(new String[]{"Select Vehicle:", "Honda Civic", "Toyota Raize", "Ford Everest"});
        vehicleDropdown.setPreferredSize(new Dimension(200, 40));
        vehicleDropdown.setFont(new Font("Arial", Font.BOLD, 16));
        pnlReserve.add(vehicleDropdown, gbc);

        // Dropdown for Select Time
        JComboBox<String> timeDropdown = new JComboBox<>(new String[]{"Select Time:", "6:00", "10:00", "15:00", "20:00"});
        timeDropdown.setPreferredSize(new Dimension(200, 40));
        timeDropdown.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        pnlReserve.add(timeDropdown, gbc);

        // Dropdown for Duration
        JComboBox<String> durationDropdown = new JComboBox<>(new String[]{"Duration:", "1hr", "5hr", "10hr", "15hr", "19hr"});
        durationDropdown.setPreferredSize(new Dimension(200, 40));
        durationDropdown.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 2;
        pnlReserve.add(durationDropdown, gbc);

        // Reserve Slot Button (using createBtnRounded from Resources)
        JButton reserveButton = res.createBtnRounded("Reserve Slot", res.celadon, res.eerieBlack, 15);
        gbc.gridx = 3;
        gbc.gridwidth = 2; // Set the button to span 2 columns
        reserveButton.setPreferredSize(new Dimension(140, 40));
        reserveButton.setFont(new Font("Arial", Font.BOLD, 16));
        pnlReserve.add(reserveButton, gbc);

        add(pnlReserve);

        this.setPreferredSize(new Dimension(1300, 160));
    }
}


