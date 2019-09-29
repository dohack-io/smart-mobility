import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {TripResult} from '../../entities/vrr/TripResult';
import {TripCirc} from '../../entities/circ/TripCirc';

@Injectable({
  providedIn: 'root'
})
export class SearchService {


  travelVrr = new EventEmitter<TripResult>();
  travelCirc = new EventEmitter<TripCirc>();

  constructor(private http: HttpClient) {

  }

  searchVrr(start: string, destination: string, dt: string, travelType: string) {

    const data = {
      from: {
        locationType: 'ADDRESS',
        location: start
      },
      to: {
        locationType: 'ADDRESS',
        location: destination
      },
      date: dt,
      travelType
    };
    this.http.post<TripResult>(environment.path + '/trip', data).subscribe((res: TripResult) => {
      this.travelVrr.emit(res);
      return true;
    });
  }

  searchCirc(start: string, destination: string, dt: string, travelType: string) {
    this.http.get<TripCirc>(environment.path + '/circTrip?start=' + start + '&end=' + destination).subscribe((res: TripCirc) => {
      this.travelCirc.emit(res);
      return true;
    });
  }
}
