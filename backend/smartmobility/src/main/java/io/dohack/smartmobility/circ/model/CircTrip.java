package io.dohack.smartmobility.circ.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CircTrip {
    private double price;
    private int duration;
    private double distance;
    private int scooterDuration;
    private int walkToScooterDuration;
    private int walkToDestinationDuration;
    private double walkToScooterDistance;
    private double scooterDistance;
    private double walkToDestinationDistance;
    private GpsLocation returningPoint;
    private Address start;
    private Address end;
}
