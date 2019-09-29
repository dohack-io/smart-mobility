import {Trip} from './Trip';
import {Location} from './Location';
import {Header} from './Header';

export class TripResult {
  network: string;
  trips: Trip[];
  from: Location;
  to: Location;
}
