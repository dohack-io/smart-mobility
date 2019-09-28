import {Component, OnInit} from '@angular/core';
import {MatExpansionModule} from '@angular/material';
import {Connection} from '../entities/Connection';
import {ConnectionService} from '../service/connection.service';
import {Step} from '../entities/Step';
import {Trip} from '../entities/Trip';
import {faChevronDown, faChevronRight} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {

  connections: Trip[];
  arrowRight = faChevronRight;
  arrowDown = faChevronDown;

  isSelectedIndex = -1;
  isSelected = false;

  selectedRequest: Trip;

  constructor(private connect: ConnectionService) {
  }

  ngOnInit() {

    const con: Trip[] = [
      {
        route: [new Step()],
        abfahrt: new Date().toLocaleTimeString(),
        ankunft: new Date().toLocaleTimeString(),
        duration: 5,
        network: 'vrr',
        price: 5.4,
      },
      {
        route: [new Step()],
        abfahrt: new Date().toLocaleTimeString(),
        ankunft: new Date().toLocaleTimeString(),
        duration: 5,
        network: 'vrr',
        price: 5.4,
      },
      {
        route: [new Step()],
        abfahrt: new Date().toLocaleTimeString(),
        ankunft: new Date().toLocaleTimeString(),
        duration: 5,
        network: 'vrr',
        price: 5.4,
      },
      {
        route: [new Step()],
        abfahrt: new Date().toLocaleTimeString(),
        ankunft: new Date().toLocaleTimeString(),
        duration: 5,
        network: 'vrr',
        price: 5.4,
      },
      {
        route: [new Step()],
        abfahrt: new Date().toLocaleTimeString(),
        ankunft: new Date().toLocaleTimeString(),
        duration: 5,
        network: 'vrr',
        price: 5.4,
      },
      {
        route: [new Step()],
        abfahrt: new Date().toLocaleTimeString(),
        ankunft: new Date().toLocaleTimeString(),
        duration: 5,
        network: 'vrr',
        price: 5.4,
      },
      {
        route: [new Step()],
        abfahrt: new Date().toLocaleTimeString(),
        ankunft: new Date().toLocaleTimeString(),
        duration: 5,
        network: 'vrr',
        price: 5.4,
      },
    ];
    this.connections = con;
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
