package client.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateTime Object
 * Will be used as the object to store when storing date and time reservations.xml
 */
public class DateTime {
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
     * The time
     */
    private volatile String time;

    /**
     * Constructs an object of LiveDate Time with default values.
     */
    public DateTime() {
        timeFormat = new SimpleDateFormat("hh:mm:ss");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("MM/dd/yy");
    }

    public String getDateTime() {
        Date currentTime = Calendar.getInstance().getTime();
        return dateFormat.format(currentTime) + ", " +
                timeFormat.format(currentTime);
    }

    public String createDateTime(int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);

        return dateFormat.format(cal.getTime()) + "," +
                timeFormat.format(cal.getTime());
    }

    public String createDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        return dateFormat.format(cal.getTime());
    }

    public String createDate(String date) {
        Calendar cal = Calendar.getInstance();
        String[] dateValues = date.split("/");
        cal.set(Calendar.YEAR, Integer.valueOf(dateValues[0]));
        cal.set(Calendar.MONTH, Integer.valueOf(dateValues[1]));
        cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dateValues[2]));

        return dateFormat.format(cal.getTime());
    }

    public String createTime(int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);

        return timeFormat.format(cal.getTime());
    }

    public String createTime(String time) {
        Calendar cal = Calendar.getInstance();
        String[] timeValues = time.split(":");
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeValues[0]));
        cal.set(Calendar.MINUTE, Integer.valueOf(timeValues[0]));
        cal.set(Calendar.SECOND, Integer.valueOf(timeValues[0]));

        return timeFormat.format(cal.getTime());
    }


    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime.createDateTime(2024,9,1,12,30,2));
        System.out.println(dateTime.createTime(12,9,1));
    }
}
