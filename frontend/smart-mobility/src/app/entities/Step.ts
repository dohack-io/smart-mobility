import {Time} from '@angular/common';

export class Step {
  price: number;
  duration: Time;
  start: string;
  destination: string;
  type: string;
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
  };
}
