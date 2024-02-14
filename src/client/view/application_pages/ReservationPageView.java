package client.view.application_pages;

import client.model.application_pages.CarMotorButton;
import client.model.application_pages.CarMotorButton;
import utilities.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

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

    private MainBottomPanel mainBottomPanel;

    private ParkingSlotButtonsView parkingSlotButtonsView;

    private Icon takenCar = res.iconTakenCar;

    private Icon availCar = res.iconAvailableCar;

    public Icon getTakenCar() {
        return takenCar;
    }


    public Icon getAvailCar() {
        return availCar;
    }

    /**
     * Constructs a panel of ReservationPageView.
     */


    public ReservationPageView() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(25,25,25,25));

        pnlCards = new JPanel(topCardLayout);
        add(pnlCards, BorderLayout.NORTH);

        parkingSlotButtonsView = new ParkingSlotButtonsView();
        // Top Panel
        pnlCards.add(new MainTopPanel(), "dashboard");
        pnlCards.add(parkingSlotButtonsView, "buttons");
        topCardLayout.show(pnlCards, "dashboard");

        mainBottomPanel = new MainBottomPanel();

        // Bottom Panel
        add(mainBottomPanel, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(1100,700));
    }

    /**
     * The panel that contains the parking slots.
     */
    public class MainBottomPanel extends JPanel {
        /**
         * The rounded panel.
         */
        private JPanel container;

        private ParkingSlotsPanel parkingSlotsPanel;

        /**
         * Constructs a panel of MainBottomPanel.
         *
         */

        public MainBottomPanel() {



            setLayout(new BorderLayout());

            container = res.createPnlRounded(1300, 510, res.white, res.lightGray);
            container.setLayout(new BorderLayout());

            JLabel lblParkingTitle = res.createLblH1("SLU Maryheights Parking", res.eerieBlack);
            lblParkingTitle.setBorder(new EmptyBorder(20, 40, 0, 0));
            container.add(lblParkingTitle, BorderLayout.NORTH);

            parkingSlotsPanel = new ParkingSlotsPanel();
            parkingSlotsPanel.setPreferredSize(new Dimension(1100, 350));
            parkingSlotsPanel.setOpaque(false);
            container.add(parkingSlotsPanel, BorderLayout.CENTER);

            add(container, BorderLayout.CENTER);

            setPreferredSize(new Dimension(1300, 510));

        }

        public ParkingSlotsPanel getParkingSlotsPanel() {
            return parkingSlotsPanel;
        }

        /**
         * The panel that contains the parking slots.
         */
        public class ParkingSlotsPanel extends JPanel {
            private static final int NUM_CAR_SLOTS = 5;
            private static final int NUM_MOTOR_SLOTS = 2;

            private CarMotorButton[] carButtons;
            private CarMotorButton[] motorButtons;

            public ParkingSlotsPanel() {
                initializeButtons();
            }

            private void initializeButtons() {
                carButtons = new CarMotorButton[NUM_CAR_SLOTS * 2];
                motorButtons = new CarMotorButton[NUM_MOTOR_SLOTS * 2];

                // Initialize buttons for the top row
                for (int i = 0; i < NUM_CAR_SLOTS; i++) {
                    String carLabel = "C" + (i + 1);
                    carButtons[i] = new CarMotorButton(carLabel);
                    carButtons[i].setIdentifier(carLabel);

                    //TODO: Validate if it is taken
                    //for buttons with image
                    carButtons[i] = new CarMotorButton(carLabel, res.iconTakenCar);
                    carButtons[i].setOpaque(false);
                    carButtons[i].setContentAreaFilled(false);
                    carButtons[i].setBorderPainted(false);
                }

                for (int i = 0; i < NUM_MOTOR_SLOTS; i++) {
                    String motorLabel = "M" + (i + 1);
                    motorButtons[i] = new CarMotorButton(motorLabel);
                    motorButtons[i].setIdentifier(motorLabel);

                    //for buttons with image
                    motorButtons[i] = new CarMotorButton(motorLabel, res.iconAvailableMotor);
                    motorButtons[i].setOpaque(false);
                    motorButtons[i].setContentAreaFilled(false);
                    motorButtons[i].setBorderPainted(false);
                }

                // Initialize buttons for the bottom row
                for (int i = 0; i < NUM_CAR_SLOTS; i++) {
                    String carLabel = "C" + (i + 6);
                    carButtons[i + NUM_CAR_SLOTS] = new CarMotorButton(carLabel);

                    //for buttons with image
                    carButtons[i+ NUM_CAR_SLOTS] = new CarMotorButton(carLabel, res.iconAvailableCar);
                    carButtons[i+ NUM_CAR_SLOTS].setOpaque(false);
                    carButtons[i+ NUM_CAR_SLOTS].setContentAreaFilled(false);
                    carButtons[i+ NUM_CAR_SLOTS].setBorderPainted(false);
                    carButtons[i+ NUM_CAR_SLOTS].setFocusPainted(false);
                }

                for (int i = 0; i < NUM_MOTOR_SLOTS; i++) {
                    String motorLabel = "M" + (i + 3);
                    motorButtons[i + NUM_MOTOR_SLOTS] = new CarMotorButton(motorLabel);

                    //for buttons with image
                    motorButtons[i+ NUM_MOTOR_SLOTS] = new CarMotorButton(motorLabel, res.iconTakenMotor);
                    motorButtons[i+ NUM_MOTOR_SLOTS].setOpaque(false);
                    motorButtons[i+ NUM_MOTOR_SLOTS].setContentAreaFilled(false);
                    motorButtons[i+ NUM_MOTOR_SLOTS].setBorderPainted(false);
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
            public void setCarButtonsListener(ActionListener listener) {
                for (JButton button : carButtons) {
                    button.addActionListener(listener);
                }
                for (JButton button : motorButtons) {
                    button.addActionListener(listener);
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
    public class ParkingSlotButtonsView extends JPanel {
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
         * TODO: Documentation
         */
        private JComboBox<String> cmbDate;

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
            JPanel pnlSlotNumber = new JPanel(new GridBagLayout());
            pnlSlotNumber.setPreferredSize(new Dimension(1300, 50));
            pnlSlotNumber.setBackground(res.white);

            GridBagConstraints gbcSlotNumber = new GridBagConstraints();
            gbcSlotNumber.anchor = GridBagConstraints.WEST;
            gbcSlotNumber.insets = new Insets(0, 10, 0, 0);

            // Button for closing the panel
            btnClose = res.createBtnIconOnly(res.iconClose, 20, 20);
            pnlSlotNumber.add(btnClose, gbcSlotNumber);

            // Label for the slot number
            lblSlotNumber = res.createLblH1("SLOT A 01", res.eerieBlack);
            gbcSlotNumber.gridx = 1;
            gbcSlotNumber.weightx = 1.0;
            gbcSlotNumber.anchor = GridBagConstraints.CENTER;
            gbcSlotNumber.insets = new Insets(0, 10, 0, 0);
            pnlSlotNumber.add(lblSlotNumber, gbcSlotNumber);

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
            gbc.insets = new Insets(5, 10, 5, 10);
            gbc.anchor = GridBagConstraints.CENTER;

            // Dropdown for Select Vehicle
            cmbVehicle = new JComboBox<>(new String[]{"Select Vehicle:", "Honda Civic", "Toyota Raize", "Ford Everest"});
            cmbVehicle.setPreferredSize(new Dimension(200, 40));
            cmbVehicle.setFont(new Font("Arial", Font.BOLD, 16));
            pnlReserve.add(cmbVehicle, gbc);

            // Dropdown for Select Date
            cmbDate = new JComboBox<>(new String[]{"Select Date:", "02-15-24", "02-16-24", "02-17-24"});
            cmbDate.setPreferredSize(new Dimension(200, 40));
            cmbDate.setFont(new Font("Arial", Font.BOLD, 16));
            gbc.gridx = 1;
            pnlReserve.add(cmbDate, gbc);

            // Dropdown for Duration
            cmbDuration = new JComboBox<>(new String[]{"Duration:", "1hr", "2hr","3hr","4hr"});
            cmbDuration.setPreferredSize(new Dimension(200, 40));
            cmbDuration.setFont(new Font("Arial", Font.BOLD, 16));
            gbc.gridx = 2;
            pnlReserve.add(cmbDuration, gbc);

            // Dropdown for Select Time
            cmbTime = new JComboBox<>(new String[]{"Select Time:", "6:00", "7:00", "8:00", "9:00",
                    "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"});
            cmbTime.setPreferredSize(new Dimension(200, 40));
            cmbTime.setFont(new Font("Arial", Font.BOLD, 16));
            gbc.gridx = 3;
            pnlReserve.add(cmbTime, gbc);

            // Reserve Slot Button
            btnReserve = res.createBtnRounded("Reserve Slot", res.celadon, res.eerieBlack, 15);
            gbc.gridx = 4;
            gbc.gridwidth = 2;
            btnReserve.setPreferredSize(new Dimension(140, 40));
            btnReserve.setFont(new Font("Arial", Font.BOLD, 16));
            pnlReserve.add(btnReserve, gbc);

            pnlContainer.add(pnlReserve);

            this.setPreferredSize(new Dimension(1300, 130));
        }

        public void setLblSlotNumber(String label) {
                lblSlotNumber.setText("SPOT " + label);
        }
        public void setBtnCloseListener(ActionListener listener) {
            btnClose.addActionListener(listener);
        }

        public void setLblDate(String date) {
            lblDate.setText(date);
        }

        public void setReserveSlotListener(ActionListener listener) {
            btnReserve.addActionListener(listener);
        }

        public void setLblType(String type) {
            lblType.setText(type);
        }

        public void setLblStatus(String status) {
            lblStatus.setText(status);
        }


        public void setVehiclesList(String[] vehicles) {
            cmbVehicle.setModel(new javax.swing.DefaultComboBoxModel<>(vehicles));
        }

        public void setTimeList(String[] timeList) {
            cmbTime.setModel(new javax.swing.DefaultComboBoxModel<>(timeList));
        }

        public String getStartTime() {
            return cmbTime.getItemAt(cmbTime.getSelectedIndex());
        }

        public String getDateChosen() {
            return cmbDate.getItemAt(cmbDate.getSelectedIndex());
        }

        public String getDurationChosen() {
            return String.valueOf(cmbDuration.getSelectedIndex());
        }

        public void setDurationListener(ActionListener listener) {
            cmbDuration.addActionListener(listener);
        }

        public void setDurationList(String[] durationList) {
        cmbDuration = new JComboBox<>(durationList);
        }
    }

    public ParkingSlotButtonsView getParkingSlotButtonsView() {
        return parkingSlotButtonsView;
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

    public MainBottomPanel getMainBottomPanel() {
        return mainBottomPanel;
    }



    public static void main(String[] args) {
        ReservationPageView view = new ReservationPageView();
    }
}
