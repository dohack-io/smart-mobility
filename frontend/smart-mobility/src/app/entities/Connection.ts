import {Time} from '@angular/common';

export class Connection {
  Route: Steps [{
    price: number,
    duration: Time;
    start: string;
    destination: string,
    type: string,
    details: {
      vrr?: {
        busnumber: number,
        direction: string,
        // ...
      }
      circ?: {
        distance: number;
        battery: number;
      }
    }
  }];
  provider: string;
  duration: string;
  price: number;
  steps?: number;
}
