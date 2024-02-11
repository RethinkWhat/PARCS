package server.model;

import java.sql.Time;

record TimeRange(Time startTime, Time endTime) {
    public TimeRange(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Time endTime() {
        return endTime;
    }

    @Override
    public Time startTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return startTime + "-" + endTime;
    }
}
