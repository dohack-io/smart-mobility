package io.dohack.smartmobility.vrr;

import io.dohack.smartmobility.vrr.model.TripRequest;
import io.dohack.smartmobility.vrr.model.TripResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class VrrController {

  private final VrrService vrrService;

  @PostMapping("/trip")
  public TripResult getTripByAddress(@RequestBody TripRequest tripRequest) {
    return vrrService.queryTrips(tripRequest);
  }
}
