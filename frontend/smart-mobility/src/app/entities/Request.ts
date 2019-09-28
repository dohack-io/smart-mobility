<<<<<<< HEAD
export class Request {
  start: string;
  destination: string;
  date: Date;

=======
import {EnumValue} from '@angular/compiler-cli/src/ngtsc/partial_evaluator';

export class Request {
  from: {
    locationType: string,
    location: string
  };
  to: {
    locationType: string,
    location: string
  };
  date: Date;
  travelType: string;
>>>>>>> feature/einbindung
}
