package server.view;

import utilities.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The panel of DashboardView.
 * TODO: Documentation
 */
public class DashboardView extends JPanel {
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
     * The full name of the current admin.
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
     * The CardLayout that controls the components of the MainTopPanel.
     */
    private CardLayout topCardLayout = new CardLayout();

    /**
     * Constructs a panel of DashboardView.
     */
    public DashboardView() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(25,25,25,25));

        // Main Panel
        add(new MainTopPanel(), BorderLayout.NORTH);
        add(new MainBottomPanel(), BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(1100,700));
    }

    /**
     * The panel that contains the parking slots.
     */
    public class MainBottomPanel extends JPanel {
        /**
         * The panel that holds the different components.
         */
        private JPanel container;
        /**
         * The rounded panel for completed car bookings.
         */
        private CarPanel pnlCompletedCar;
        /**
         * The rounded panel for completed motorcycle bookings.
         */
        private MotorPanel pnlCompletedMotor;

        /**
         * Constructs a panel of MainBottomPanel.
         */
        public MainBottomPanel() {
            setLayout(new BorderLayout());
            setBackground(res.lightGray);

            GridLayout gridLayout = new GridLayout(0,2);
            gridLayout.setHgap(25);

            container = new JPanel(gridLayout);
            container.setPreferredSize(new Dimension(1300,510));
            container.setBackground(res.lightGray);
            add(container, BorderLayout.CENTER);

            pnlCompletedCar = new CarPanel();
            container.add(pnlCompletedCar);

            pnlCompletedMotor = new MotorPanel();
            container.add(pnlCompletedMotor);

            setPreferredSize(new Dimension(1300, 510));
            setVisible(true);
        }

        /**
         * The CarPanel displays the completed car bookings.
         */
        public class CarPanel extends JPanel {
            /**
             * Holds the components.
             */
            private JPanel container;
            /**
             * The button for previous record.
             */
            private JButton btnPrev;
            /**
             * The button for next record.
             */
            private JButton btnNext;
            /**
             * The panel that holds different components panels of booking histories.
             */
            private JPanel pnlCards;
            /**
             * The CardLayout that controls the components of the bookings.
             */
            private CardLayout cardLayout = new CardLayout();

            /**
             * Constructs a panel of CarPanel.
             */
            public CarPanel() {
                setLayout(new BorderLayout());

                container = res.createPnlRounded(400, 500,res.white, res.lightGray);
                container.setBorder(new EmptyBorder(20,10,20,10));
                container.setLayout(new BorderLayout());
                add(container,BorderLayout.CENTER);

                JLabel lblTitle = res.createLblH1("Completed Car Bookings", res.eerieBlack);
                lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
                container.add(lblTitle, BorderLayout.NORTH);

                btnPrev = res.createBtnIconOnly(res.iconLeftArrow, 25,25);
                container.add(btnPrev, BorderLayout.WEST);

                btnNext = res.createBtnIconOnly(res.iconRightArrow, 25,25);
                container.add(btnNext, BorderLayout.EAST);

                pnlCards = new JPanel(cardLayout);
                pnlCards.setPreferredSize(new Dimension(400,300));
                container.add(pnlCards, BorderLayout.CENTER);

                pnlCards.add(new RecordPanel());

                this.setPreferredSize(new Dimension(400,500));
            }
        }

        /**
         * The MotorPanel displays the completed motorcycle bookings.
         */
        public class MotorPanel extends JPanel {
            /**
             * Holds the components.
             */
            private JPanel container;
            /**
             * The button for previous record.
             */
            private JButton btnPrev;
            /**
             * The button for next record.
             */
            private JButton btnNext;
            /**
             * The panel that holds different components panels of booking histories.
             */
            private JPanel pnlCards;
            /**
             * The CardLayout that controls the components of the bookings.
             */
            private CardLayout cardLayout = new CardLayout();

            public MotorPanel() {
                setLayout(new BorderLayout());

                container = res.createPnlRounded(400, 500,res.white, res.lightGray);
                container.setBorder(new EmptyBorder(20,10,20,10));
                container.setLayout(new BorderLayout());
                add(container,BorderLayout.CENTER);

                JLabel lblTitle = res.createLblH1("Completed Motorcycle Bookings", res.eerieBlack);
                lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
                container.add(lblTitle, BorderLayout.NORTH);

                btnPrev = res.createBtnIconOnly(res.iconLeftArrow, 25,25);
                container.add(btnPrev, BorderLayout.WEST);

                btnNext = res.createBtnIconOnly(res.iconRightArrow, 25,25);
                container.add(btnNext, BorderLayout.EAST);

                pnlCards = new JPanel(cardLayout);
                pnlCards.setPreferredSize(new Dimension(400,300));
                container.add(pnlCards, BorderLayout.CENTER);

                pnlCards.add(new RecordPanel());

                this.setPreferredSize(new Dimension(400,500));
            }
        }

        /**
         * The record panel holds one record of a booking. It displays the pertinent information about the particular
         * completed booking.
         */
        public class RecordPanel extends JPanel {
            /**
             * The label for username.
             */
            private JLabel lblUsername;
            /**
             * The label for vehicle.
             */
            private JLabel lblVehicle;
            /**
             * The label for parking slot.
             */
            private JLabel lblSlot;
            /**
             * The label for date.
             */
            private JLabel lblDate;
            /**
             * The label for check-in time.
             */
            private JLabel lblCheckIn;
            /**
             * The label for check-out time.
             */
            private JLabel lblCheckOut;
            /**
             * The label for duration in hours.
             */
            private JLabel lblDuration;

            /**
             * Constructs a panel of RecordPanel.
             */
            public RecordPanel() {
                setLayout(new GridBagLayout());
                setBackground(res.white);
                GridBagConstraints gbc = new GridBagConstraints();

                gbc.ipadx = 5;
                gbc.ipady = 10;
                gbc.insets = new Insets(3,10,3,10);
                gbc.gridwidth = 5;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.WEST;

                gbc.gridx = 0;
                gbc.gridy = 0;

                // upper row
                lblUsername = res.createLblH2("Username",res.eerieBlack);
                add(lblUsername, gbc);

                gbc.gridy = 1;
                lblVehicle = res.createLblH3("Vehicle", res.eerieBlack);
                add(lblVehicle, gbc);

                gbc.gridy = 2;
                gbc.gridwidth = 5;
                JSeparator separator = new JSeparator();
                separator.setForeground(res.gray);
                add(separator, gbc);

                // lower row
                gbc.gridy = 3;
                lblSlot = res.createLblH3("SLOT C1", res.eerieBlack);
                add(lblSlot, gbc);

                // left column
                gbc.gridwidth = 2;
                gbc.gridx = 0;
                gbc.gridy = 4;
                JLabel lblDateLabel = res.createLblP("Date:", res.gray);
                add(lblDateLabel, gbc);

                gbc.gridy = 5;
                JLabel lblCheckInLabel = res.createLblP("Check-In Time:", res.gray);
                add(lblCheckInLabel, gbc);

                gbc.gridy = 6;
                JLabel lblCheckOutLabel = res.createLblP("Check-Out Time:", res.gray);
                add(lblCheckOutLabel, gbc);

                gbc.gridy = 7;
                JLabel lblDurationLabel = res.createLblP("Duration:", res.gray);
                add(lblDurationLabel, gbc);

                // right column
                gbc.gridwidth = 2;
                gbc.gridx = 4;
                gbc.gridy = 4;
                lblDate = res.createLblP("August 20, 1996", res.eerieBlack);
                add(lblDate, gbc);

                gbc.gridy = 5;
                lblCheckIn = res.createLblP("1:00 PM", res.eerieBlack);
                add(lblCheckIn, gbc);

                gbc.gridy = 6;
                lblCheckOut = res.createLblP("5:00 PM", res.eerieBlack);
                add(lblCheckOut, gbc);

                gbc.gridy = 7;
                lblDuration = res.createLblP("4 hours",res.eerieBlack);
                add(lblDuration, gbc);
            }

            /**
             * Retrieves the current JLabel of lblUsername.
             * @return The current lblUsername.
             */
            public JLabel getLblUsername() {
                return lblUsername;
            }

            /**
             * Retrieves the current JLabel of lblVehicle.
             * @return The current lblVehicle.
             */
            public JLabel getLblVehicle() {
                return lblVehicle;
            }

            /**
             * Retrieves the current JLabel of lblSlot.
             * @return The current lblSlot.
             */
            public JLabel getLblSlot() {
                return lblSlot;
            }

            /**
             * Retrieves the current JLabel of lblDate.
             * @return The current lblDate.
             */
            public JLabel getLblDate() {
                return lblDate;
            }

            /**
             * Retrieves the current JLabel of lblCheckIn.
             * @return The current lblCheckIn.
             */
            public JLabel getLblCheckIn() {
                return lblCheckIn;
            }

            /**
             * Retrieves the current JLabel of lblCheckOut.
             * @return The current lblCheckOut.
             */
            public JLabel getLblCheckOut() {
                return lblCheckOut;
            }

            /**
             * Retrieves the current JLabel of lblDuration.
             * @return The current lblDuration.
             */
            public JLabel getLblDuration() {
                return lblDuration;
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
            gbc.ipadx = 1100;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            lblName = res.createLblH2("Hello, " + "Admin"+ "!", res.eerieBlack);
            pnlInformation.add(lblName, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 3;
            String date = "";
            lblDate = res.createLblH4(date, res.eerieBlack);
            pnlInformation.add(lblDate, gbc);

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
                    res.createLblP("<html>Total<br> Bookings</html>", res.eerieBlack)
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
}
