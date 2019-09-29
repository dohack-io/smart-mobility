import {Location} from './Location';
import {Trip} from './Trip';

export class TravelConnection {
  networkId: string;
  from: Location;
  to: Location;
  trips: Trip[];

}
