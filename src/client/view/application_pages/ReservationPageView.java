package client.view.application_pages;

import utilities.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Template for ReservationPageView object.
 * The ReservationPage is the home page of the client application.
 */
public class ReservationPageView extends JPanel {
    /**
     * The panel that holds different component panels.
     */
    private JPanel pnlCards;
    /**
     * The button that shows the current number of available car slots.
     */
    private JButton btnAvailCar;
    /**
     * The button that shows the current number of available motorcycle slots.
     */
    private JButton btnAvailMotor;
    /**
     * The button that shows the current number of total bookings.
     */
    private JButton btnTotalBookings;
    /**
     * The search bar.,
     */
    private JTextField txtSearchbar;
    /**
     * The first name of the current user.
     */
    private JLabel lblName;
    /**
     * The current date.
     */
    private JLabel lblDate;
    /**
     * The stylesheet.
     */
    private Resources res = new Resources();
    /**
     * Instance variable of GridBagConstraints used for JPanels using GridBagLayout.
     */
    private GridBagConstraints gbc;

    /**
     * Variable to hold full name of user
     */
    private JLabel userFullName = new JLabel("Ramon");
    /**
     * The CardLayout that controls the components of the MainTopPanel.
     */
    private CardLayout topCardLayout = new CardLayout();

    /**
     * Constructs a panel of ReservationPageView.
     */
    public ReservationPageView() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(25,25,25,25));

        pnlCards = new JPanel(topCardLayout);
        add(pnlCards, BorderLayout.NORTH);

        // Top Panel
        pnlCards.add(new MainTopPanel(), "dashboard");
        pnlCards.add(new ParkingSlotButtonsView(), "buttons");
        topCardLayout.show(pnlCards, "dashboard");

        // Bottom Panel
        add(new MainBottomPanel(), BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(1100,700));
    }

    /**
     * The panel that contains the parking slots.
     */
    class MainBottomPanel extends JPanel {
        /**
         * The rounded panel.
         */
        private JPanel container;

        /**
         * Constructs a panel of MainBottomPanel.
         */
        public MainBottomPanel() {
            setLayout(new BorderLayout());

            container = res.createPnlRounded(1300, 510, res.white, res.lightGray);
            container.setLayout(new BorderLayout());

            JLabel lblParkingTitle = res.createLblH1("SLU Maryheights Parking", res.eerieBlack);
            lblParkingTitle.setBorder(new EmptyBorder(20, 40, 0, 0));
            container.add(lblParkingTitle, BorderLayout.NORTH);

            ParkingSlotsPanel parkingSlotsPanel = new ParkingSlotsPanel();
            parkingSlotsPanel.setPreferredSize(new Dimension(1100, 350));
            parkingSlotsPanel.setOpaque(false);
            container.add(parkingSlotsPanel, BorderLayout.CENTER);

            add(container, BorderLayout.CENTER);

            setPreferredSize(new Dimension(1300, 510));
        }

        /**
         * The panel that contains the parking slots.
         */
        class ParkingSlotsPanel extends JPanel {
            private static final int NUM_CAR_SLOTS = 5;
            private static final int NUM_MOTOR_SLOTS = 2;

            private JButton[] carButtons;
            private JButton[] motorButtons;

            public ParkingSlotsPanel() {
                initializeButtons();
            }

            private void initializeButtons() {
                carButtons = new JButton[NUM_CAR_SLOTS * 2];
                motorButtons = new JButton[NUM_MOTOR_SLOTS * 2];

                // Initialize buttons for the top row
                for (int i = 0; i < NUM_CAR_SLOTS; i++) {
                    String carLabel = "C" + String.format("%2d", i + 1);
                    carButtons[i] = res.createBtnRounded(carLabel, res.celadon, res.white, 10);
                }

                for (int i = 0; i < NUM_MOTOR_SLOTS; i++) {
                    String motorLabel = "M" + String.format("%2d", i + 1);
                    motorButtons[i] = res.createBtnRounded(motorLabel, res.celadon, Color.WHITE, 10);
                }

                // Initialize buttons for the bottom row
                for (int i = 0; i < NUM_CAR_SLOTS; i++) {
                    String carLabel = "C" + String.format("%2d", i + 6);
                    carButtons[i + NUM_CAR_SLOTS] = res.createBtnRounded(carLabel, res.celadon, Color.WHITE, 10);
                }

                for (int i = 0; i < NUM_MOTOR_SLOTS; i++) {
                    String motorLabel = "M" + String.format("%2d", i + 3);
                    motorButtons[i + NUM_MOTOR_SLOTS] = res.createBtnRounded(motorLabel, res.celadon, Color.WHITE, 10);
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int panelWidth = getWidth();
                int panelHeight = getHeight();

                int slotWidth = 1000 / (NUM_CAR_SLOTS + NUM_MOTOR_SLOTS);
                int slotHeight = 300 / 2;

                int motorSlotWidth = 90; // int motorSlotWidth = slotWidth / 2;

                int xOffset = (panelWidth - 1000) / 2;
                int yOffset = (panelHeight - 300) / 2;

                g.setColor(Color.black);

                // Draw Car Slots (Top Row)
                for (int i = 0; i < NUM_CAR_SLOTS; i++) {
                    int x = xOffset + i * slotWidth;
                    int y = yOffset;

                    // Draw rectangles to represent car slots
                    g.drawRect(x, y, slotWidth, slotHeight);

                    // Buttons for the car slots (added bounds for positioning)
                    carButtons[i].setBounds(x + slotWidth / 8, y + slotHeight / 8, slotWidth * 3 / 4, slotHeight * 3 / 4);
                    add(carButtons[i]);
                }

                // Draw Motor Slots (Top Row)
                for (int i = 0; i < NUM_MOTOR_SLOTS; i++) {
                    int x = xOffset + (NUM_CAR_SLOTS + i) * slotWidth;
                    int y = yOffset;

                    // Draw rectangles to represent motor slots
                    g.drawRect(x, y, motorSlotWidth, slotHeight);

                    // Buttons for the motor slot
                    motorButtons[i].setBounds(x + motorSlotWidth / 8, y + slotHeight / 8, motorSlotWidth * 3 / 4, slotHeight * 3 / 4);
                    add(motorButtons[i]);
                }

                // Draw Car Slots (Bottom Row)
                for (int i = 0; i < NUM_CAR_SLOTS; i++) {
                    int x = xOffset + i * slotWidth;
                    int y = yOffset + slotHeight;

                    // Draw rectangles to represent car slots
                    g.drawRect(x, y, slotWidth, slotHeight);

                    // Buttons for the car slots (added bounds for positioning)
                    carButtons[i + NUM_CAR_SLOTS].setBounds(x + slotWidth / 8, y + slotHeight / 8, slotWidth * 3 / 4, slotHeight * 3 / 4);
                    add(carButtons[i + NUM_CAR_SLOTS]);
                }

                // Draw Motor Slots (Bottom Row)
                for (int i = 0; i < NUM_MOTOR_SLOTS; i++) {
                    int x = xOffset + (NUM_CAR_SLOTS + i) * slotWidth;
                    int y = yOffset + slotHeight;

                    // Draw rectangles to represent motor slots
                    g.drawRect(x, y, motorSlotWidth, slotHeight);

                    // Buttons for the motor slot
                    motorButtons[i + NUM_MOTOR_SLOTS].setBounds(x + motorSlotWidth / 8, y + slotHeight / 8, motorSlotWidth * 3 / 4, slotHeight * 3 / 4);
                    add(motorButtons[i + NUM_MOTOR_SLOTS]);
                }
            }
        }
    }

    /**
     * The panel that contains multiple objects of the ButtonsPanel.
     */
    class MainTopPanel extends JPanel {
        /**
         * Constructs a panel of MainTopPanel.
         */
        public MainTopPanel() {
            setBackground(res.lightGray);
            setLayout(new BorderLayout());

            JPanel pnlInformation = new JPanel(new GridBagLayout());
            pnlInformation.setPreferredSize(new Dimension(1300,50));
            add(pnlInformation, BorderLayout.NORTH);
            pnlInformation.setBackground(res.lightGray);

            gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.ipadx = 475;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            lblName = res.createLblH2("Hello, " + "Ramon Jasmin"+ "!", res.eerieBlack);
            pnlInformation.add(lblName, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 3;
            String date = "";
            lblDate = res.createLblH4(date, res.eerieBlack);
            pnlInformation.add(lblDate, gbc);

            gbc.gridy = 0;
            gbc.gridx = 1;
            gbc.gridwidth = 5;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.fill = GridBagConstraints.BOTH;
            txtSearchbar = res.createTxtRounded("Search date",res.white,res.gray, 30);
            pnlInformation.add(txtSearchbar, gbc);

            GridLayout gridLayout = new GridLayout(0,3);
            gridLayout.setHgap(10);

            JPanel pnlButtons = new JPanel(gridLayout);
            pnlButtons.setPreferredSize(new Dimension(1300,100));
            pnlButtons.setBorder(new EmptyBorder(10,0,10,0));
            pnlButtons.setBackground(res.lightGray);
            add(pnlButtons, BorderLayout.SOUTH);


            // Update parameters as models from controller when backend has commenced.
            ButtonPanel pnlAvailCar = new ButtonPanel(
                    btnAvailCar = res.createBtnIconOnly(res.iconSolidCar, 50, 50),
                    res.createLblH1("13", res.eerieBlack),
                    res.createLblP("<html>Available<br> Car Slots</html>", res.eerieBlack)
            );
            pnlButtons.add(pnlAvailCar);

            ButtonPanel pnlAvailMotor = new ButtonPanel(
                    btnAvailMotor = res.createBtnIconOnly(res.iconSolidMotor, 50, 50),
                    res.createLblH1("10", res.eerieBlack),
                    res.createLblP("<html>Available<br> Motor Slots</html>", res.eerieBlack)
            );
            pnlButtons.add(pnlAvailMotor);

            ButtonPanel pnlTotalBookings = new ButtonPanel(
                    btnTotalBookings = res.createBtnIconOnly(res.iconSolidTicket, 50,50),
                    res.createLblH1("3", res.eerieBlack),
                    res.createLblP("<html>Your Total<br> Bookings</html>", res.eerieBlack)
            );
            pnlButtons.add(pnlTotalBookings);

            this.setPreferredSize(new Dimension(1300,150));
        }
    }

    /**
     * The panel that creates the useful buttons of the home page.
     */
    class ButtonPanel extends JPanel {
        /**
         * The rounded panel.
         */
        private JPanel container;

        /**
         * Constructs a panel with a specified button, number label, and title label.
         */
        public ButtonPanel(JButton button, JLabel number, JLabel title) {
            setLayout(new BorderLayout());

            container = res.createPnlRounded(100, 80, res.white, res.lightGray);
            container.setLayout(new GridLayout(0, 3));
            add(container, BorderLayout.NORTH);

            container.add(button);
            container.add(number);
            container.add(title);

            this.setPreferredSize(new Dimension(100, 100));
        }
    }

    /**
     * TODO: Documentation
     */
    class ParkingSlotButtonsView extends JPanel {
        /**
         * TODO: Documentation
         */
        private JButton btnClose;
        /**
         * TODO: Documentation
         */
        private JPanel pnlContainer;
        /**
         * TODO: Documentation
         */
        private JLabel lblSlotNumber;
        /**
         * TODO: Documentation
         */
        private JLabel lblType;
        /**
         * TODO: Documentation
         */
        private JLabel lblStatus;
        /**
         * TODO: Documentation
         */
        private JLabel lblDate;
        /**
         * TODO: Documentation
         */
        private JButton btnReserve;
        /**
         * TODO: Documentation
         */
        private JComboBox<String> cmbVehicle;
        /**
         * TODO: Documentation
         */
        private JComboBox<String> cmbTime;
        /**
         * TODO: Documentation
         */
        private JComboBox<String> cmbDuration;

        /**
         * The stylesheet.
         */
        private Resources res = new Resources();

        /**
         * Constructs a panel of ParkingSlotButtonsView
         */
        public ParkingSlotButtonsView() { // TODO: Replace constructors
            this.setLayout(new BorderLayout());

            pnlContainer = res.createPnlRounded(1300,130,res.white, res.lightGray);
            pnlContainer.setLayout(new GridLayout(3, 1)); // Divided into 3 rows, 1 column
            add(pnlContainer, BorderLayout.CENTER);

            // pnlSlotNumber
            JPanel pnlSlotNumber = new JPanel(new BorderLayout());
            pnlSlotNumber.setPreferredSize(new Dimension(1300, 50));
            pnlSlotNumber.setBackground(res.white);

            // Button for closing the panel
            btnClose = res.createBtnIconOnly(res.iconClose, 20,20);
            pnlSlotNumber.add(btnClose, BorderLayout.WEST);

            // Label for the slot number
            lblSlotNumber = res.createLblH1("SLOT A 01", res.eerieBlack);
            pnlSlotNumber.add(lblSlotNumber, BorderLayout.CENTER);
            pnlContainer.add(pnlSlotNumber);

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

            pnlContainer.add(pnlSlotInformation);

            // pnlReserve
            JPanel pnlReserve = new JPanel(new GridBagLayout());
            pnlReserve.setPreferredSize(new Dimension(1300, 100));
            pnlReserve.setBackground(Color.white);

            gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 10, 5, 10); // Add space around components
            gbc.anchor = GridBagConstraints.CENTER; // Center components horizontally

            // Dropdown for Select Vehicle
            cmbVehicle = new JComboBox<>(new String[]{"Select Vehicle:", "Honda Civic", "Toyota Raize", "Ford Everest"});
            cmbVehicle.setPreferredSize(new Dimension(200, 40));
            cmbVehicle.setFont(new Font("Arial", Font.BOLD, 16));
            pnlReserve.add(cmbVehicle, gbc);

            // Dropdown for Select Time
            JComboBox<String> cmbTime = new JComboBox<>(new String[]{"Select Time:", "6:00", "10:00", "15:00", "20:00"});
            cmbTime.setPreferredSize(new Dimension(200, 40));
            cmbTime.setFont(new Font("Arial", Font.BOLD, 16));
            gbc.gridx = 1;
            pnlReserve.add(cmbTime, gbc);

            // Dropdown for Duration
            cmbDuration = new JComboBox<>(new String[]{"Duration:", "1hr", "5hr", "10hr", "15hr", "19hr"});
            cmbDuration.setPreferredSize(new Dimension(200, 40));
            cmbDuration.setFont(new Font("Arial", Font.BOLD, 16));
            gbc.gridx = 2;
            pnlReserve.add(cmbDuration, gbc);

            // Reserve Slot Button (using createBtnRounded from Resources)
            JButton btnReserve = res.createBtnRounded("Reserve Slot", res.celadon, res.eerieBlack, 15);
            gbc.gridx = 3;
            gbc.gridwidth = 2; // Set the button to span 2 columns
            btnReserve.setPreferredSize(new Dimension(140, 40));
            btnReserve.setFont(new Font("Arial", Font.BOLD, 16));
            pnlReserve.add(btnReserve, gbc);

            pnlContainer.add(pnlReserve);

            this.setPreferredSize(new Dimension(1300, 130));
        }
    }

    public JPanel getPnlCards() {
        return pnlCards;
    }

    public JButton getBtnAvailCar() {
        return btnAvailCar;
    }

    public JButton getBtnAvailMotor() {
        return btnAvailMotor;
    }

    public JButton getBtnTotalBookings() {
        return btnTotalBookings;
    }

    public JTextField getTxtSearchbar() {
        return txtSearchbar;
    }

    public JLabel getLblName() {
        return lblName;
    }

    public void setUserFullName(String fullName) {
        lblName.setText("Hello, " + fullName + "!");
    }

    public JLabel getLblDate() {
        return lblDate;
    }

    public CardLayout getTopCardLayout() {
        return topCardLayout;
    }
}
