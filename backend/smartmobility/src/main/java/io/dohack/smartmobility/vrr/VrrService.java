package io.dohack.smartmobility.vrr;

import de.schildbach.pte.dto.Location;
import de.schildbach.pte.dto.QueryTripsResult;
import io.dohack.smartmobility.vrr.model.LocationRequest;
import io.dohack.smartmobility.vrr.model.TripRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@RequiredArgsConstructor
public class VrrService {

  private final VrrRepository vrrRepository;

  public QueryTripsResult queryTrips(TripRequest tripRequest) {
    var fromStation = getLocation(tripRequest.getFrom());
    var toStation = getLocation(tripRequest.getTo());
    return vrrRepository.queryTrips(
        fromStation, null, toStation, tripRequest.getDate(), tripRequest.getTravelType(), null);
  }

  private Location getLocation(LocationRequest locationRequest) {
    var suggestedLocations =
        vrrRepository.suggestLocations(
            locationRequest.getLocation(), EnumSet.of(locationRequest.getLocationType()), 0);
    return suggestedLocations.getLocations().get(0);
  }
}
