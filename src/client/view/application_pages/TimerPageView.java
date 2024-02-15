package client.view.application_pages;

import utilities.Resources;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The TimerPageView displays the current booking of the user.
 */
public class TimerPageView extends JPanel {
    /**
     * The panel that holds different components.
     */
    private JPanel container;
    /**
     * The panel for the timer
     */
    private TimerPanel pnlTimer;
    /**
     * The panel for information.
     */
    private TicketInfoPanel pnlTicketInfo;
    /**
     * The stylesheet.
     */
    private Resources res = new Resources();

    /**
     * Constructs a panel of TimerViewPatch.
     */
    public TimerPageView() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20,20,20,20));
        setBackground(res.lightGray);

        container = res.createPnlRounded(1100,700,res.white, res.white);
        container.setPreferredSize(new Dimension(1100,700));
        container.setBorder(new EmptyBorder(20,20,20,20));
        container.setLayout(new BorderLayout());
        add(container, BorderLayout.CENTER);

        JLabel lblTitle = res.createLblH1("Current Ticket", res.eerieBlack);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(lblTitle, BorderLayout.NORTH);

        pnlTimer = new TimerPanel();
        container.add(pnlTimer, BorderLayout.WEST);

        pnlTicketInfo = new TicketInfoPanel();
        container.add(pnlTicketInfo, BorderLayout.EAST);

        this.setPreferredSize(new Dimension(1100,700));
    }

    /**
     * The panel to hold the timer.
     */
    public class TimerPanel extends JPanel {
        /**
         * The timer to delay UI components.
         */
        private Timer swingTimer;
        /**
         * The button for the end timer.
         */
        private JButton btnEndTimer;
        /**
         * The hour of the timer object.
         */
        private int hour;
        /**
         * The minute of the timer object.
         */
        private int minute;
        /**
         * The second of the timer object.
         */
        private int second;
        /**
         * The radius of the timer, representing a countdown.
         */
        private static final int CIRCLE_RADIUS = 152;
        /**
         * The starting angle of the countdown timer.
         */
        private static final int ARC_START_ANGLE = 90;
        /**
         * The extent of the arc.
         */
        private int arcExtent;
        /**
         * The components inside the timer.
         */
        double init;
        /**
         * The current time of the timer.
         */
        double current;

        /**
         * Constructs a panel of TimerPanel.
         */
        public TimerPanel() {
            setBackground(res.white);
            arcExtent = 360;
            hour = 1;
            minute = 0;
            second = 0;
            init = hour * 3600 + minute * 60 + second;
            current = init;

            swingTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTime();
                    repaint();
                }
            });
            swingTimer.start();

            this.setPreferredSize(new Dimension(550,650));
        }

        /**
         * Updates the time left.
         */
        private void updateTime() {
            if (hour == 0 && minute == 0 && second == 0) {
                swingTimer.stop();
                setVisible(false);
            } else if (minute == 0 && second == 0) {
                hour--;
                minute = 59;
                second = 59;
            } else if (second == 0) {
                minute--;
                second = 59;
            } else {
                second--;
            }
        }
    }

    /**
     * The panel to hold the ticket information of the booking.
     */
    public class TicketInfoPanel extends JPanel {
        /**
         * The label of the vehicle parked.
         */
        private JLabel lblVehicle;
        /**
         * The label of the parking type.
         */
        private JLabel lblParkingType;
        /**
         * The label of the parking spot.
         */
        private JLabel lblParkingSpot;
        /**
         * The label of booked parking.
         */
        private JLabel lblDate;
        /**
         * The label of the parking duration in hours.
         */
        private JLabel lblDuration;
        /**
         * The label for the time-in and time-out of the booking.
         */
        private JLabel lblTime;

        /**
         * Constructs a panel of TicketInfoPanel.
         */
        public TicketInfoPanel() {
            setLayout(new GridBagLayout());
            setBackground(res.white);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10,10,10,10);

            gbc.gridwidth = 1;
            gbc.ipadx = 50;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.WEST;

            // left column
            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel lblParkAreaLabel = res.createLblP("Parking Area:", res.eerieBlack);
            add(lblParkAreaLabel, gbc);

            gbc.gridy = 1;
            JLabel lblVehicleLabel = res.createLblP("Vehicle:", res.eerieBlack);
            add(lblVehicleLabel, gbc);

            gbc.gridy = 2;
            JLabel lblParkingTypeLabel = res.createLblP("Parking Type:", res.eerieBlack);
            add(lblParkingTypeLabel, gbc);

            gbc.gridy = 3;
            JLabel lblParkingSpotLabel = res.createLblP("Parking Spot:", res.eerieBlack);
            add(lblParkingSpotLabel, gbc);

            gbc.gridy = 4;
            JLabel lblDateLabel = res.createLblP("Date:", res.eerieBlack);
            add(lblDateLabel, gbc);

            gbc.gridy = 5;
            JLabel lblDurationLabel = res.createLblP("Duration:", res.eerieBlack);
            add(lblDurationLabel, gbc);

            gbc.gridy = 6;
            JLabel lblTimeLabel = res.createLblP("Time:", res.eerieBlack);
            add(lblTimeLabel, gbc);

            // right column
            gbc.gridwidth = 1;

            gbc.gridx = 1;
            gbc.gridy = 0;
            JLabel lblParkingArea = res.createLblP("SLU Maryheights Campus", res.eerieBlack);
            add(lblParkingArea, gbc);

            gbc.gridy = 1;
            lblVehicle = res.createLblP("Info", res.eerieBlack);
            add(lblVehicle, gbc);

            gbc.gridy = 2;
            lblParkingType = res.createLblP("Info", res.eerieBlack);
            add(lblParkingType, gbc);

            gbc.gridy = 3;
            lblParkingSpot = res.createLblP("Info", res.eerieBlack);
            add(lblParkingSpot, gbc);

            gbc.gridy = 4;
            lblDate = res.createLblP("Info", res.eerieBlack);
            add(lblDate, gbc);

            gbc.gridy = 5;
            lblDuration = res.createLblP("Info", res.eerieBlack);
            add(lblDuration, gbc);

            gbc.gridy = 6;
            lblTime = res.createLblP("Info", res.eerieBlack);
            add(lblTime, gbc);

            this.setPreferredSize(new Dimension(550,650));
        }

        /**
         * Retrieves the current JLabel of lblVehicle.
         * @return The current lblVehicle.
         */
        public JLabel getLblVehicle() {
            return lblVehicle;
        }

        /**
         * Retrieves the current JLabel of lblParkingType.
         * @return The current lblParkingType.
         */
        public JLabel getLblParkingType() {
            return lblParkingType;
        }

        /**
         * Retrieves the current JLabel of lblParkingSpot.
         * @return The current lblParkingSpot.
         */
        public JLabel getLblParkingSpot() {
            return lblParkingSpot;
        }

        /**
         * Retrieves the current JLabel of lblDate.
         * @return The current lblDate.
         */
        public JLabel getLblDate() {
            return lblDate;
        }

        /**
         * Retrieves the current JLabel of lblDuration.
         * @return The current lblDuration.
         */
        public JLabel getLblDuration() {
            return lblDuration;
        }

        /**
         * Retrieves the current JLabel of lblTime.
         * @return The current lblTime.
         */
        public JLabel getLblTime() {
            return lblTime;
        }
    }

    /**
     * Retrieves the current JPanel of pnlTimer.
     * @return The current pnlTimer.
     */
    public TimerPanel getPnlTimer() {
        return pnlTimer;
    }

    /**
     * Retrieves the current JPanel of pnlTicketInfo.
     * @return The current pnlTicketInfo.
     */
    public TicketInfoPanel getPnlTicketInfo() {
        return pnlTicketInfo;
    }
}
