import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

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

    this.http.post<any>(environment.path + '/trip', {data}).subscribe( data => {

    });
  }
}
