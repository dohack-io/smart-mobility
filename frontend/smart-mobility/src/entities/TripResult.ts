import {Trip} from './Trip';
import {Location} from './Location';

export class TripResult {
  networkId: string;
  trips: Trip[];
  from: Location;
  to: Location;
}
