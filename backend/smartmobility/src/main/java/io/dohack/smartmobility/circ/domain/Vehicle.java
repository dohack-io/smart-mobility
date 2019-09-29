package io.dohack.smartmobility.circ.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {
    String id;
    Location location;
    int energyLevelInPercent;
    String deepLink;
    String imageLink;
    String scooterId;

    public Vehicle() {
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", location=" + location +
                ", energyLevelInPercent=" + energyLevelInPercent +
                ", deepLink='" + deepLink + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", scooterId='" + scooterId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getEnergyLevelInPercent() {
        return energyLevelInPercent;
    }

    public void setEnergyLevelInPercent(int energyLevelInPercent) {
        this.energyLevelInPercent = energyLevelInPercent;
    }

    public String getDeepLink() {
        return deepLink;
    }

    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getScooterId() {
        return scooterId;
    }

    public void setScooterId(String scooterId) {
        this.scooterId = scooterId;
    }
}
