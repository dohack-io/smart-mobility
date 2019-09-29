import {Component, OnInit} from '@angular/core';
import {TripResult} from '../../entities/TripResult';
import {Trip} from '../../entities/Trip';
import {faChevronDown, faChevronRight} from '@fortawesome/free-solid-svg-icons';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {
  tripResult: TripResult;
  trips: Trip[] = [];
  arrowRight = faChevronRight;
  arrowDown = faChevronDown;

  isSelectedIndex = -1;
  isSelected = false;

  selectedRequest: Trip;

  constructor() {

  }

  ngOnInit() {
    if (!environment.backend) {
      this.trips = [
        {
          from: null,
          to: null,
          legs: [],
          prices: [],
          numChanges: 3,
          duration: 45,
          firstPublic: null,
          lastPublicLeg: null,
        }
      ];
    } else {
      this.trips = this.tripResult.trips;
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
