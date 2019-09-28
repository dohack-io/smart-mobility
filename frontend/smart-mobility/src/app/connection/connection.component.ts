import {Component, OnInit} from '@angular/core';
import {ConnectionService} from '../service/connection.service';
import {TripResult} from '../entities/TripResult';
import {Trip} from '../entities/Trip';
import {faChevronDown, faChevronRight} from '@fortawesome/free-solid-svg-icons';
import {Leg} from '../entities/Leg';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {
  tripResult: TripResult;
  trips: Trip[];
  arrowRight = faChevronRight;
  arrowDown = faChevronDown;

  isSelectedIndex = -1;
  isSelected = false;

  selectedRequest: Trip;

  constructor(private connect: ConnectionService) {
  }

  ngOnInit() {

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
