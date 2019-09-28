package io.dohack.smartmobility.vrr.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TravelType {
  DEPARTURE(true),
  ARRIVAL(false);

  public final boolean type;
}
