package io.dohack.smartmobility.circ.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class NominatimDomain {
    String lat;
    String lon;
    String display_name;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public NominatimDomain() {
    }

    @Override
    public String toString() {
        return "NominatimDomain{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", display_name='" + display_name + '\'' +
                '}';
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }


}

