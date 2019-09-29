import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {TripResult} from '../../entities/TripResult';

@Injectable({
  providedIn: 'root'
})
export class SearchService {


  travel = new EventEmitter<TripResult>();

  constructor(private http: HttpClient) {

  }

  search(start: string, destination: string, dt: string, travelType: string) {

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
      this.travel.emit(res);
      return true;
    });

  }
}
