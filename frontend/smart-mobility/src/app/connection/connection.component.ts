import {Component, OnInit} from '@angular/core';
import {Trip} from '../../entities/vrr/Trip';
import {faChevronDown, faChevronRight} from '@fortawesome/free-solid-svg-icons';
import {environment} from '../../environments/environment';
import {SearchService} from '../service/search.service';
import {TravelConnection} from '../../entities/travelConnection';
import {TripCirc} from '../../entities/circ/TripCirc';
import {Observable, of} from 'rxjs';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {
  tripResult: TravelConnection;
  trips: Trip[] = [];

  tripCirc=  new TripCirc();

  arrowRight = faChevronRight;
  arrowDown = faChevronDown;

  isSelectedIndex = -1;
  isSelected = false;

  selectedRequest: Trip;

  constructor(private search: SearchService) {

  }

  ngOnInit() {
    if (!environment.backend) {
      this.trips = [
        {
          from: null,
          to: null,
          legs: [],
          fares: [],
          numChanges: 3,
          duration: 45,
          firstPublicLegTime: null,
          lastPublicLegTime: null,
        }
      ];
    } else {
      this.search.travelVrr.subscribe(res => {
        this.tripResult = res;
        this.trips = res.trips;
      });

      this.search.travelCirc.subscribe((res: TripCirc) => {
        console.log(res);
        this.tripCirc.distance = Math.round(res.distance);
        this.tripCirc.duration = Math.round(res.duration);
        this.tripCirc.price = res.price;
        this.tripCirc.start.name = res.start.name.split(',')[0];
        this.tripCirc.end.name = res.end.name.split(',')[0];
      });

    }
  }

  onItem(entry: Trip, index: number) {
    this.isSelected = !this.isSelected;
    if (this.isSelected) {
      this.selectedRequest = entry;
      this.isSelectedIndex = index;
    } else {
      if (this.selectedRequest === entry) {
        this.selectedRequest = null;
        this.isSelectedIndex = -1;
      } else {
        this.isSelected = !this.isSelected;
        this.selectedRequest = entry;
        this.isSelectedIndex = index;
      }

    }
  }


}
