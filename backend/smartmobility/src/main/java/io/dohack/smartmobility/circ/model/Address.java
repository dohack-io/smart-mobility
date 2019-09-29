package io.dohack.smartmobility.circ.model;

public class Address {
    GPSLocation gpsLocation;
    String name;

    @Override
    public String toString() {
        return "Address{" +
                "gpsLocation=" + gpsLocation +
                ", name='" + name + '\'' +
                '}';
    }

    public Address(GPSLocation gpsLocation, String name) {
        this.gpsLocation = gpsLocation;
        this.name = name;
    }

    public Address() {
    }

    public GPSLocation getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(GPSLocation gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
