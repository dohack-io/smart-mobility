package io.dohack.smartmobility.circ.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VrrDetails {
    private String busLine;
    private String company;
    private String direction;
    private int duration;
    private String departureStop;
    private String arrivalStop;
}
