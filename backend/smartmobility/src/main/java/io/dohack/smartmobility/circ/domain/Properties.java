package io.dohack.smartmobility.circ.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
    ArrayList<Segments> segments;

    public Properties() {
    }

    @Override
    public String toString() {
        return "Properties{" +
                "segments=" + segments +
                '}';
    }

    public ArrayList<Segments> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<Segments> segments) {
        this.segments = segments;
    }
}
