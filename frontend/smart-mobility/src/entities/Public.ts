import {Leg} from './Leg';
import {Stop} from './Stop';

export class Public extends Leg {
  line: string;
  destination: Location;
  departureStop: Stop;
  arrivalStop: Stop;
  intermediateStops: Stop[];
  message: string;
}
