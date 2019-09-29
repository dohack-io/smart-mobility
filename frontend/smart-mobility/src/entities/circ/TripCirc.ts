import {Location} from '../vrr/Location';
import {GpsLocation} from './GpsLocation';

export class TripCirc {
  price: number;
  duration: number;
  distance: number;
  scooterDuration: number;
  walktoScooterDuration: number;
  walkToDestinationDuration: number;
  walktoScooterDistance: number;

  returningPoint: GpsLocation;

  start: {
    gpsLocation: GpsLocation;
    name: string;
  };

  end: {
    gpsLocation: GpsLocation;
    name: string;
  };
}
