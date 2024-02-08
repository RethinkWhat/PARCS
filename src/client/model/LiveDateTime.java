package client.model;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LiveDateTime {
    private SimpleDateFormat timeFormat;
    private SimpleDateFormat dayFormat;
    private SimpleDateFormat dateFormat;

    public LiveDateTime() {
        timeFormat = new SimpleDateFormat("hh:mm a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("MMMMM dd, yyyy");
    }

    public SimpleDateFormat getTimeFormat() {
        return timeFormat;
    }

    public SimpleDateFormat getDayFormat() {
        return dayFormat;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }
}
