import {Component, Input, OnInit} from '@angular/core';
import {Time} from '@angular/common';
import {Router} from '@angular/router';
import {SearchService} from '../service/search.service';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.scss']
})
export class SearchPageComponent implements OnInit {

  start = '';
  destination = '';
  @Input() date: string;
  @Input() time: string;
  requested: boolean;

  constructor(private router: Router, private search: SearchService) {
  }

  ngOnInit() {
    this.requested = false;
  }

  onSearch() {
    this.requested = true;

    this.search.search(this.start, this.destination, this.changeTime(this.time), 'DEPARTURE');
    this.router.navigateByUrl('/connection');
  }

  changeTime(time: string): Date {
    const minute = time.split(':');
    const dateS = new Date('09-29-2019').getTime();
    const sum = dateS +  +minute[0] * 3600000 + +minute[1] * 60000;
    return new Date(sum);
  }
}
