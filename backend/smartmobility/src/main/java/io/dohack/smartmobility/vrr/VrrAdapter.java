package io.dohack.smartmobility.vrr;

import de.schildbach.pte.VrrProvider;
import de.schildbach.pte.dto.*;
import io.dohack.smartmobility.vrr.model.TravelType;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

@Component
public class VrrAdapter {
  private final VrrProvider vrrProvider = new VrrProvider();

  public SuggestLocationsResult suggestLocations(
      CharSequence constraint, Set<LocationType> types, int maxLocations) {
    try {
      return vrrProvider.suggestLocations(constraint, types, maxLocations);
    } catch (IOException e) {
      throw new VrrException();
    }
  }

  public QueryTripsResult queryTrips(
      Location from,
      @Nullable Location via,
      Location to,
      Date date,
      TravelType travelType,
      TripOptions options) {
    try {
      return vrrProvider.queryTrips(from, via, to, date, travelType.type, options);
    } catch (IOException e) {
      throw new VrrException();
    }
  }
}
