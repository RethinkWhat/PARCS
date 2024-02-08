package client.model;

import java.text.SimpleDateFormat;

/**
 * Template for LiveDateTime object.
 * LiveDateTime contains the different formats for specifying the time, date, and day of the week.
 */
public class LiveDateTime {
    /**
     * The format for the time (HH:MM AM/PM MARKER)
     */
    private SimpleDateFormat timeFormat;
    /**
     * The format for the day that specifies the whole text.
     */
    private SimpleDateFormat dayFormat;
    /**
     * The format for date in MMMM dd, YYYY format (e.g., February 19, 2024).
     */
    private SimpleDateFormat dateFormat;

    /**
     * Constructs an object of LiveDate Time with default values.
     */
    public LiveDateTime() {
        timeFormat = new SimpleDateFormat("hh:mm a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("MMMMM dd, yyyy");
    }

    /**
     * Retrieves the current format for time.
     * @return The current format for time.
     */
    public SimpleDateFormat getTimeFormat() {
        return timeFormat;
    }

    /**
     * Retrieves the current format for day.
     * @return The current format for day.
     */
    public SimpleDateFormat getDayFormat() {
        return dayFormat;
    }

    /**
     * Retrieves the current format for date.
     * @return The current format for date.
     */
    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }
}
