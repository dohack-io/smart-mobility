import {Time} from '@angular/common';
import {Step} from './Step';

export class Connection {
  Route: [Step];
  provider: string;
  duration: string;
  start: string;
  arrival: string;
  price: number;
  steps: number;
}
