package model;

public class InRideModel {
    String time, duration;

    public InRideModel(String time, String duration) {
        this.time = time;
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
