package client.view.application_pages;

import utilities.Resources;

import javax.swing.*;
import java.awt.*;

/**
 * The
 */
public class TimerViewPage extends JPanel {
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
    public TimerViewPage() {
        setLayout(new BorderLayout());
        setBackground(res.lightGray);

        JLabel lblTitle = res.createLblH1("Current Ticket", res.eerieBlack);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.CENTER);

        container = res.createPnlRounded(1100,700,res.white, res.lightGray);
        container.setLayout(new GridLayout(0,2));
        add(container, BorderLayout.CENTER);

        pnlTimer = new TimerPanel();
        container.add(pnlTimer);

        this.setPreferredSize(new Dimension(1100,700));
    }

    /**
     * The panel to hold the timer.
     */
    public class TimerPanel extends JPanel {
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
        private JLabel lblHours;

        /**
         * Constructs a panel of TicketInfoPanel.
         */
        public TicketInfoPanel() {
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10,10,10,10);

            JLabel lblVehicleLabel;
        }
    }
}
