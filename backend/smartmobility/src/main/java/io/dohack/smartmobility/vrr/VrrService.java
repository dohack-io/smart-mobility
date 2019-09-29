package io.dohack.smartmobility.vrr;

import de.schildbach.pte.dto.Location;
import io.dohack.smartmobility.vrr.model.LocationRequest;
import io.dohack.smartmobility.vrr.model.TripRequest;
import io.dohack.smartmobility.vrr.model.TripResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@RequiredArgsConstructor
public class VrrService {

  private final VrrAdapter vrrAdapter;

  public TripResult queryTrips(TripRequest tripRequest) {
    var fromStation = getLocation(tripRequest.getFrom());
    var toStation = getLocation(tripRequest.getTo());
    var queryResult =
        vrrAdapter.queryTrips(
            fromStation, null, toStation, tripRequest.getDate(), tripRequest.getTravelType(), null);
    return new TripResult(queryResult);
  }

  private Location getLocation(LocationRequest locationRequest) {
    var suggestedLocations =
        vrrAdapter.suggestLocations(
            locationRequest.getLocation(), EnumSet.of(locationRequest.getLocationType()), 0);
    return suggestedLocations.getLocations().get(0);
  }
}
