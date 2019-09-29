package io.dohack.smartmobility.circ.model;

import java.util.Date;

public class Step {
    double price;
    int duration;
    Date startTime;
    Date endTime;
    String type;
    double arrivalLongitude;
    double arrivalLatitude;
    double departureLongitude;
    double departureLatitude;
    VrrDetails vrrDetails;
    CircDetails circDetails;

    public Step () {

    }

}
