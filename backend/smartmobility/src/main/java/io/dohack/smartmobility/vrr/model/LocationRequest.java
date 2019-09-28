package io.dohack.smartmobility.vrr.model;

import de.schildbach.pte.dto.LocationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationRequest {
  private LocationType locationType;
  private String location;
}
