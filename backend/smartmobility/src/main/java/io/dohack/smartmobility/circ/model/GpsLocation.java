package io.dohack.smartmobility.circ.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GpsLocation {
    private double latitude;
    private double longitude;
}
