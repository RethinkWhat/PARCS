package client.view.application_pages;

import javax.swing.*;

/**
 * TODO: Documentation
 */
public class TimerViewPage extends JPanel {
    /**
     * The panel for the timer
     */
    private TimerPanel pnlTimer;
    /**
     * The panel for information.
     */
    private InfoPanel pnlInfo;

    /**
     * Constructs a panel of TimerViewPatch.
     */
    public TimerViewPage() {

    }

    /**
     *
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

    public class InfoPanel extends JPanel {

    }
}
