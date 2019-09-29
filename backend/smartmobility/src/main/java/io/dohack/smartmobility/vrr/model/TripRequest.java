package io.dohack.smartmobility.vrr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripRequest {
  private LocationRequest from;
  private LocationRequest to;
  private Date date;
  private TravelType travelType;
}
