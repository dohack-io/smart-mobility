package io.dohack.smartmobility.circ.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturningPoint {
    private double distance;
    private GpsLocation gpsLocation;
}
