package io.dohack.smartmobility.circ.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Segments {
    double distance;
    double duration;
    // steps

    public Segments() {
    }

    @Override
    public String toString() {
        return "segments{" +
                "distance=" + distance +
                ", duration=" + duration +
                '}';
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
}
