import {Leg} from './Leg';
import {Fare} from './Fare';

export class Trip {
  from: Location;
  to: Location;
  legs: Leg[];
  fares: Fare[];
  numChanges: number;
  duration: number;
  firstPublicLegTime: Date;
  lastPublicLegTime: Date;
}
