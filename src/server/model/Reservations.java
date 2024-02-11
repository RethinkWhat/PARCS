package server.model;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;

public class Reservations {

    /** Holds the date of the reservations */
    private Date date;

    /** StartTime,Endtime : Rickardo
     *    Map of start and finish of time to username
     */
    private HashMap<TimeRange, String> TimeAndUserMap;

    public Reservations(Date date) {
        this.date = date;
        TimeAndUserMap = new HashMap<>();
    }



    /**
     * Getter for the date
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for the date
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Method to get the start and end of a reservation and who reserved the spot
     * @return TimeAndUserMap
     */
    public HashMap<TimeRange, String> getTimeAndUserMap() {
        return TimeAndUserMap;
    }

    /**
     * Method to set the start and end of a reservation and who reserved the spot
     * @param timeAndUserMap : HashMap<HashMap<Time, Time>, String>
     */
    public void setTimeAndUserMap(HashMap<TimeRange, String> timeAndUserMap) {
        TimeAndUserMap = timeAndUserMap;
    }

    /**
     * Method to add a reservation
     * @param startTime : Time
     * @param endTime : Time
     * @param reserver : String
     */
    public void addReservation(Time startTime, Time endTime, String reserver) {
        TimeAndUserMap.put(new TimeRange(startTime,endTime), reserver);
    }

    /**
     * Method to add a reservation
     * @param timeRange : TimeRange
     * @param reserver : String
     */
    public void addReservation(TimeRange timeRange, String reserver) {
        TimeAndUserMap.put(timeRange,reserver);
    }
}
