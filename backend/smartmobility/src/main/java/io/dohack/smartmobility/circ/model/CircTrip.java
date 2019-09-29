package io.dohack.smartmobility.circ.model;

public class CircTrip {
    double price;
    int duration;
    double distance;
    int scooterDuration;
    int walkToScooterDuration;
    int walkToDestinationDuration;
    double walkToScooterDistance;
    double scooterDistance;
    double walkToDestinationDistance;
    GPSLocation returningPoint;
    Address start;
    Address end;

    public CircTrip() {
    }

    @Override
    public String toString() {
        return "CircTrip{" +
                "price=" + price +
                ", duration=" + duration +
                ", distance=" + distance +
                ", scooterDuration=" + scooterDuration +
                ", walkToScooterDuration=" + walkToScooterDuration +
                ", walkToDestinationDuration=" + walkToDestinationDuration +
                ", walkToScooterDistance=" + walkToScooterDistance +
                ", scooterDistance=" + scooterDistance +
                ", walkToDestinationDistance=" + walkToDestinationDistance +
                ", returningPoint=" + returningPoint +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getScooterDuration() {
        return scooterDuration;
    }

    public void setScooterDuration(int scooterDuration) {
        this.scooterDuration = scooterDuration;
    }

    public int getWalkToScooterDuration() {
        return walkToScooterDuration;
    }

    public void setWalkToScooterDuration(int walkToScooterDuration) {
        this.walkToScooterDuration = walkToScooterDuration;
    }

    public int getWalkToDestinationDuration() {
        return walkToDestinationDuration;
    }

    public void setWalkToDestinationDuration(int walkToDestinationDuration) {
        this.walkToDestinationDuration = walkToDestinationDuration;
    }

    public double getWalkToScooterDistance() {
        return walkToScooterDistance;
    }

    public void setWalkToScooterDistance(double walkToScooterDistance) {
        this.walkToScooterDistance = walkToScooterDistance;
    }

    public double getScooterDistance() {
        return scooterDistance;
    }

    public void setScooterDistance(double scooterDistance) {
        this.scooterDistance = scooterDistance;
    }

    public double getWalkToDestinationDistance() {
        return walkToDestinationDistance;
    }

    public void setWalkToDestinationDistance(double walkToDestinationDistance) {
        this.walkToDestinationDistance = walkToDestinationDistance;
    }

    public GPSLocation getReturningPoint() {
        return returningPoint;
    }

    public void setReturningPoint(GPSLocation returningPoint) {
        this.returningPoint = returningPoint;
    }

    public Address getStart() {
        return start;
    }

    public void setStart(Address start) {
        this.start = start;
    }

    public Address getEnd() {
        return end;
    }

    public void setEnd(Address end) {
        this.end = end;
    }
}
