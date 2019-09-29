import {Leg} from './Leg';
import {Price} from './Price';

export class Trip {
  from: Location;
  to: Location;
  legs: Leg[];
  prices: Price[];
  numChanges: number;
  duration: number;
  firstPublic: Date;
  lastPublicLeg: Date;
}
