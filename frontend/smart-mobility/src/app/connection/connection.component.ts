import {Component, OnInit} from '@angular/core';
import {MatExpansionModule} from '@angular/material';
import {Connection} from '../entities/Connection';
import {ConnectionService} from '../service/connection.service';
import {Step} from '../entities/Step';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {

  connections: Connection[];

  constructor(private connect: ConnectionService) {
  }

  ngOnInit() {

    const con: Connection[] = [
      {
        Route: [new Step()],
        provider: 'vrr',
        duration: '30',
        start: '14:23',
        arrival: '15:34',
        price: 5.4,
        steps: 0
      },
      {
        Route: [new Step()],
        provider: 'vrr',
        duration: '30',
        start: '14:23',
        arrival: '15:34',
        price: 5.4,
        steps: 0
      },
      {
        Route: [new Step()],
        provider: 'vrr',
        duration: '30',
        start: '14:23',
        arrival: '15:34',
        price: 5.4,
        steps: 0
      }, {
        Route: [new Step()],
        provider: 'vrr',
        duration: '30',
        start: '14:23',
        arrival: '15:34',
        price: 5.4,
        steps: 0
      }, {
        Route: [new Step()],
        provider: 'vrr',
        duration: '30',
        start: '14:23',
        arrival: '15:34',
        price: 5.4,
        steps: 0
      },
      {
        Route: [new Step()],
        provider: 'vrr',
        duration: '30',
        start: '14:23',
        arrival: '15:34',
        price: 5.4,
        steps: 0
      }, {
        Route: [new Step()],
        provider: 'vrr',
        duration: '30',
        start: '14:23',
        arrival: '15:34',
        price: 5.4,
        steps: 0
      },
      {
        Route: [new Step()],
        provider: 'vrr',
        duration: '30',
        start: '14:23',
        arrival: '15:34',
        price: 5.4,
        steps: 0
      },
      {
        Route: [new Step()],
        provider: 'vrr',
        duration: '30',
        start: '14:23',
        arrival: '15:34',
        price: 5.4,
        steps: 0
      },
    ];
    this.connections = con;
  }
}
