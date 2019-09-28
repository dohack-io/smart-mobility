import {Component, OnInit} from '@angular/core';
import {MatExpansionModule} from '@angular/material';
import {Connection} from '../entities/Connection';
import {ConnectionService} from '../service/connection.service';
import {Step} from '../entities/Step';
import {Trip} from '../entities/Trip';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {

  connections: Trip[];

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

  onItem(i: number) {

  }
}
