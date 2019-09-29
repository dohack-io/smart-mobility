import {Component, OnInit} from '@angular/core';
import {Trip} from '../../entities/Trip';
import {faChevronDown, faChevronRight} from '@fortawesome/free-solid-svg-icons';
import {environment} from '../../environments/environment';
import {SearchService} from '../service/search.service';
import {TravelConnection} from '../../entities/travelConnection';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {
  tripResult: TravelConnection;
  trips: Trip[] = [];
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
      this.search.travel.subscribe( res => {
        this.tripResult = res;
        this.trips = res.trips;
        console.log(res.trips[1]);
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
