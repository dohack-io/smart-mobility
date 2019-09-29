import {Location} from './vrr/Location';
import {Trip} from './vrr/Trip';

export class TravelConnection {
  networkId: string;
  from: Location;
  to: Location;
  trips: Trip[];

}
