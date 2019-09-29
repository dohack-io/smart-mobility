package io.dohack.smartmobility.circ.model;

public class Journey {
    double distance;
    int duration;

    public Journey(double distance, int duration) {
        this.distance = distance;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "distance=" + distance +
                ", duration=" + duration +
                '}';
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
