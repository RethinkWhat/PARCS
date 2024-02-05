package client.view.application;

import utilities.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ParkingStatus extends JFrame {

    private JButton btnNavMenu;
    private JButton btnNavHome;
    private JButton btnNavTicket;
    private JButton btnNavAccount;
    private JButton btnNavLogout;

    private Resources res = new Resources();

    public ParkingStatus() {
        super("PARCS");

        // Body panel acting as a container to hold all UI components
        Container contentArea = new JPanel(new BorderLayout());

        ParkingStatus.HeaderPanel pnlHeader = new ParkingStatus.HeaderPanel();
        contentArea.add(pnlHeader, BorderLayout.NORTH);

        ParkingStatus.NavbarPanel pnlNavbar = new ParkingStatus.NavbarPanel();
        contentArea.add(pnlNavbar, BorderLayout.WEST);

        JPanel pnlMain = new JPanel(new BorderLayout());
        pnlMain.setPreferredSize(new Dimension(1100,700));
        pnlMain.setBackground(res.lightGray);
        pnlMain.setBorder(new EmptyBorder(0,25,25,25));
        contentArea.add(pnlMain, BorderLayout.CENTER);

        MainTopPanel pnlMainTop = new MainTopPanel();
        pnlMain.add(pnlMainTop, BorderLayout.NORTH);

        MainBottomPanel pnlMainBottom = new MainBottomPanel();
        pnlMain.add(pnlMainBottom, BorderLayout.SOUTH);


        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(1300,800);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    class HeaderPanel extends JPanel {
        public HeaderPanel() {
            setBackground(res.celadon);
            setLayout(new FlowLayout(FlowLayout.LEFT));

            JButton btnNavMenu = res.createBtnIconOnly(res.logoParcs, 40, 30);
            add(btnNavMenu);

            JLabel lblLocation = res.createLblH3("Home", res.white);
            add(lblLocation);

            this.setPreferredSize(new Dimension(1300, 50));
        }
    }


    class NavbarPanel extends JPanel {
        public NavbarPanel() {
            setBackground(res.feldgrau);

            btnNavHome = res.createBtnIconOnly(res.iconHome, 30,30);
            add(btnNavHome);

            btnNavTicket = res.createBtnIconOnly(res.iconTicket,30,30);
            add(btnNavTicket);

            btnNavAccount = res.createBtnIconOnly(res.iconAccount,30,30);
            add(btnNavAccount);

            btnNavLogout = res.createBtnIconOnly(res.iconLogout,30,30);
            add(btnNavLogout);

            this.setPreferredSize(new Dimension(60, 800));
        }
    }

    class MainTopPanel extends JPanel {
        public MainTopPanel() {
            setBackground(res.white);
            setLayout(new GridLayout(3, 1)); // Divided into 3 rows, 1 column

            // pnlSlot
            JPanel pnlSlotNumber = new JPanel();
            pnlSlotNumber.setLayout(new FlowLayout(FlowLayout.CENTER));
            pnlSlotNumber.setPreferredSize(new Dimension(1300, 50));
            pnlSlotNumber.setBackground(Color.white);

            // Label for the slot number
            JLabel lblSlotNumber = res.createLblH1("SLOT A 01", res.eerieBlack);
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

            JLabel lblType = res.createLblP("Car", res.eerieBlack);
            gbc.gridx = 1;
            pnlSlotInformation.add(lblType, gbc);

            // Labels for Status
            JLabel lblStatName = res.createLblH3("Status:", res.eerieBlack); // Used createLblH3 for distinction
            gbc.gridx = 2;
            pnlSlotInformation.add(lblStatName, gbc);

            JLabel lblStatus = res.createLblP("Unavailable", res.eerieBlack);
            gbc.gridx = 3;
            pnlSlotInformation.add(lblStatus, gbc);

            // Labels for Date
            JLabel lblDateName = res.createLblH3("Date:", res.eerieBlack); // Used createLblH3 distinction
            gbc.gridx = 4;
            pnlSlotInformation.add(lblDateName, gbc);

            JLabel lblDate = res.createLblP("January 24, 2024", res.eerieBlack);
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


            this.setPreferredSize(new Dimension(1300,160));
        }
    }

    class MainBottomPanel extends JPanel {
        public MainBottomPanel() {
            setBackground(res.white);
            setForeground(res.white);
            setBorder(new Resources.RoundedBorder(20));

            setPreferredSize(new Dimension(1300,515));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ParkingStatus();
            }
        });
    }
}


