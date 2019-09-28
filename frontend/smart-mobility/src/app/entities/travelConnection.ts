import {Location} from './Location';
import {Trip} from './Trip';

export class TravelConnection {
  network: string;
  from: Location;
  to: Location;
  trips: Trip[];

}
