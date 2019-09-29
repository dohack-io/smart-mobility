package io.dohack.smartmobility.vrr.model;

import de.schildbach.pte.NetworkId;
import de.schildbach.pte.dto.Location;
import de.schildbach.pte.dto.QueryTripsResult;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TripResult {
  private NetworkId networkId;
  private List<MyTrip> trips;
  private Location from;
  private Location via;
  private Location to;

  public TripResult(QueryTripsResult result) {
    if (result.header != null) {
      this.networkId = result.header.network;
    }
    this.trips = result.trips.stream().map(MyTrip::new).collect(Collectors.toList());
    this.from = result.from;
    this.via = result.via;
    this.to = result.to;
  }
}
