package io.dohack.smartmobility.circ.model;

public class ReturningPoint {
    double distance;
    GPSLocation gpsLocation;

    public ReturningPoint(double distance, GPSLocation gpsLocation) {
        this.distance = distance;
        this.gpsLocation = gpsLocation;
    }

    @Override
    public String toString() {
        return "ReturningPoint{" +
                "distance=" + distance +
                ", gpsLocation=" + gpsLocation +
                '}';
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public GPSLocation getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(GPSLocation gpsLocation) {
        this.gpsLocation = gpsLocation;
    }
}
