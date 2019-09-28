import {Step} from './Step';

export class Trip {
    route: [Step];
    abfahrt: string;
    ankunft: string;
    duration: number;
    network: string;
    price: number;
}
