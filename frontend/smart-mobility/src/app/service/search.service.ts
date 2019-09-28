import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {TripResult} from '../entities/TripResult';
import {Trip} from '../entities/Trip';
import {TravelConnection} from '../entities/travelConnection';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) {

  }

  search(start: string, destination: string, date: Date) {

    const data = {
      start,
      destination,
      date
    };

    const travel: TravelConnection = new TravelConnection();

    this.http.post<any>(environment.path + '/trip', {data}).subscribe((res: TripResult) => {

      travel.network = res.network;
      travel.from = res.from;
      travel.to = res.to;
      travel.trips = res.trips;
    });
    return travel;
  }
}
