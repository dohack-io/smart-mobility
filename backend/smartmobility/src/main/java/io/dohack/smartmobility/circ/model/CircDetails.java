package io.dohack.smartmobility.circ.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CircDetails {
    private int energyLevel;
    private double distance;
}
