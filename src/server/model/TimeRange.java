package server.model;


import java.sql.Time;

record TimeRange(String startTime, String endTime) {
    public TimeRange(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String endTime() {
        return endTime;
    }

    @Override
    public String startTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return startTime + "-" + endTime;
    }
}
