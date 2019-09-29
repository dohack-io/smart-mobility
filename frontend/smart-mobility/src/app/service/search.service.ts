import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {TripResult} from '../../entities/TripResult';
import {Trip} from '../../entities/Trip';
import {TravelConnection} from '../../entities/travelConnection';
import {Request} from '../../entities/Request';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  travel: TravelConnection;

  constructor(private http: HttpClient) {

  }

  search(start: string, destination: string, date: Date, travelType: string) {

    const data: Request = {
      from: {
        locationType: 'ADDRESS',
        location: start
      },
      to: {
        locationType: 'ADDRESS',
        location: destination
      },
      date,
      travelType
  };

    this.http.post<any>(environment.path + '/trip', data).subscribe((res: TripResult) => {

      this.travel.network = res.network;
      this.travel.from = res.from;
      this.travel.to = res.to;
      this.travel.trips = res.trips;
    });
  }
}
