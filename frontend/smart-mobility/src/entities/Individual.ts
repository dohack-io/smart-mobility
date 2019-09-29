import {Leg} from './Leg';

export class Individual extends Leg {
  type: string;
  departureTime: Date; // TODO
  arrivalTime: Date; // TODO
  min: number;
  distance: number;
}
