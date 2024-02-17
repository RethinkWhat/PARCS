package client.view.application_pages;

import utilities.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HistoryView extends JPanel {
    /**
     * The button for next.
     */
    private JButton btnNext;
    /**
     * The button for previous.
     */
    private JButton btnPrev;
    /**
     * The panel that holds multiple components.
     */
    private JPanel pnlCards;
    /**
     * The container to hold the components.
     */
    private JPanel container;
    /**
     * The card layout that controls pnlCards.
     */
    private CardLayout cardLayout;
    /**
     * The stylesheet.
     */
    private Resources res = new Resources();

    /**
     * Constructs a panel of HistoryView.
     */
    public HistoryView() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10,25,10,10));
        setBackground(res.lightGray);

        container = res.createPnlRounded(700,700, res.white, res.lightGray);
        container.setLayout(new BorderLayout());
        add(container, BorderLayout.CENTER);

        cardLayout = new CardLayout();

        pnlCards = new JPanel(cardLayout);
        pnlCards.setBackground(res.red);
        pnlCards.setPreferredSize(new Dimension(600,500));
        container.add(pnlCards, BorderLayout.CENTER);

        btnPrev = res.createBtnIconOnly(res.iconLeftArrow, 30,30);
        container.add(btnPrev, BorderLayout.WEST);

        btnNext = res.createBtnIconOnly(res.iconRightArrow, 30,30);
        container.add(btnNext, BorderLayout.EAST);

        this.setPreferredSize(new Dimension(750, 700));
    }

    /**
     * The panel that contains the booking information.
     */
    public class RecordPanel extends JPanel {
        /**
         * The label for date of booking.
         */
        private JLabel lblDate;
        /**
         * The label for area of parking.
         */
        private JLabel lblArea;
        /**
         * The label for type of vehicle.
         */
        private JLabel lblType;
        /**
         * The label for vehicle of the user.
         */
        private JLabel lblVehicle;
        /**
         * The label for parking spot.
         */
        private JLabel lblSpot;
        /**
         * The label for check in time.
         */
        private JLabel lblCheckIn;
        /**
         * The label for check out time.
         */
        private JLabel lblCheckOut;
        /**
         * The duration of the booking in hours.
         */
        private JLabel lblDuration;

        /**
         * Constructs a panel of RecordPanel.
         */
        public RecordPanel() {
            setLayout(new BorderLayout());

            JPanel container = res.createPnlRounded(600,500,res.lightGray, res.white);
            container.setLayout(new GridBagLayout());
            add(container, BorderLayout.CENTER);

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(10,10,10,10);
            gbc.fill = GridBagConstraints.BOTH;

            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridwidth = 3;
            gbc.ipadx = 100;
            gbc.ipady = 10;

            // center
            gbc.gridx = 0;
            gbc.gridy = 0;
            lblDate = res.createLblH1("Info", res.eerieBlack);
            container.add(lblDate, gbc);

            gbc.gridy = 1;
            JSeparator separator = new JSeparator();
            separator.setPreferredSize(new Dimension(600,1));
            separator.setForeground(res.eerieBlack);
            container.add(separator, gbc);

            // left column
            gbc.gridx = 1;
            gbc.gridy = 2;
            JLabel lblAreaLabel = res.createLblP("Parking Area:", res.eerieBlack);
            container.add(lblAreaLabel, gbc);

            gbc.gridy = 3;
            JLabel lblTypeLabel = res.createLblP("Parking Type:", res.eerieBlack);
            container.add(lblTypeLabel, gbc);

            gbc.gridy = 4;
            JLabel lblVehicleLabel = res.createLblP("Vehicle:", res.eerieBlack);
            container.add(lblVehicleLabel, gbc);

            gbc.gridy = 5;
            JLabel lblSpotLabel = res.createLblP("Parking Spot", res.eerieBlack);
            container.add(lblSpotLabel, gbc);

            gbc.gridy = 6;
            JLabel lblCheckInLabel = res.createLblP("Check-In:", res.eerieBlack);
            container.add(lblCheckInLabel, gbc);

            gbc.gridy = 7;
            JLabel lblCheckOutLabel = res.createLblP("Check-Out:", res.eerieBlack);
            container.add(lblCheckOutLabel, gbc);

            gbc.gridy = 8;
            JLabel lblDurationLabel = res.createLblP("Duration:", res.eerieBlack);
            container.add(lblDurationLabel, gbc);

            // right column
            gbc.gridx = 1;
            gbc.gridy = 2;
            lblArea = res.createLblP("SLU Maryheights Campus", res.eerieBlack);
            container.add(lblArea, gbc);

            gbc.gridy = 3;
            lblType = res.createLblP("Info", res.eerieBlack);
            container.add(lblArea, gbc);

            gbc.gridy = 4;
            lblVehicle = res.createLblP("Info", res.eerieBlack);
            container.add(lblVehicle, gbc);

            gbc.gridy = 5;
            lblSpot = res.createLblP("Info", res.eerieBlack);
            container.add(lblSpotLabel,  gbc);

            gbc.gridy = 6;
            lblCheckIn = res.createLblP("Info", res.eerieBlack);
            container.add(lblCheckIn, gbc);

            gbc.gridy = 7;
            lblCheckOut = res.createLblP("Info", res.eerieBlack);
            container.add(lblCheckOut, gbc);

            gbc.gridy = 8;
            lblDuration = res.createLblP("Info", res.eerieBlack);;
            container.add(lblDuration, gbc);
        }

        /**
         * Retrieves the current JLabel of lblDate.
         * @return The current lblDate.
         */
        public JLabel getLblDate() {
            return lblDate;
        }

        /**
         * Retrieves the current JLabel of lblType.
         * @return The current lblType.
         */
        public JLabel getLblType() {
            return lblType;
        }

        /**
         * Retrieves the current JLabel of lblVehicle.
         * @return The current lblVehicle.
         */
        public JLabel getLblVehicle() {
            return lblVehicle;
        }

        /**
         * Retrieves the current JLabel of lblSpot.
         * @return The current lblSpot.
         */
        public JLabel getLblSpot() {
            return lblSpot;
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
         * Retrieves the current JLabel fo lblDuration.
         * @return The current lblDuration.
         */
        public JLabel getLblDuration() {
            return lblDuration;
        }
    }
}
