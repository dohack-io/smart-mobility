package io.dohack.smartmobility.circ.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Step {
    private double price;
    private int duration;
    private Date startTime;
    private Date endTime;
    private String type;
    private double arrivalLongitude;
    private double arrivalLatitude;
    private double departureLongitude;
    private double departureLatitude;
    private VrrDetails vrrDetails;
    private CircDetails circDetails;
}
