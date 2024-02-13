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
     * Format for time following the format: "hh:mm:ss"
     */
    private SimpleDateFormat timeFormat;


    /**
     * Format for date following the format: "mm/dd/yy"
     */
    private SimpleDateFormat dateFormat;


    /**
     * Default constructor for dateTime
     */
    public DateTime() {
        timeFormat = new SimpleDateFormat("hh:mm");
        dateFormat = new SimpleDateFormat("mm/dd/yy");
    }

    public String getDateTime() {
        Date currentTime = Calendar.getInstance().getTime();
        return dateFormat.format(currentTime);// + ", " //+
                //timeFormat.format(currentTime);
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

    public String createTime(int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);

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

    }
}
