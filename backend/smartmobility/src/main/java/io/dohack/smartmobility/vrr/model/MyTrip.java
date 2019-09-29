package io.dohack.smartmobility.vrr.model;

import de.schildbach.pte.dto.Fare;
import de.schildbach.pte.dto.Location;
import de.schildbach.pte.dto.Trip;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MyTrip {
  private Location from;
  private Location to;
  private List<Trip.Leg> legs;
  private List<Fare> fares;
  private int numChanges;
  private long duration;
  private Date firstPublicLegTime;
  private Date lastPublicLegTime;

  public MyTrip(Trip trip) {
    this.from = trip.from;
    this.to = trip.to;
    this.legs = trip.legs;
    this.fares = trip.fares;
    this.numChanges = trip.numChanges;
    this.duration = trip.getDuration();
    this.firstPublicLegTime = trip.getFirstPublicLegDepartureTime();
    this.lastPublicLegTime = trip.getLastPublicLegArrivalTime();
  }
}
