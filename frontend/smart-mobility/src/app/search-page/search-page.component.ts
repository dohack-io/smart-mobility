import {Component, OnInit} from '@angular/core';
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
  date: Date;
  time: Time;
  requested: boolean;

  constructor(private router: Router, private search: SearchService) {
  }

  ngOnInit() {
    this.requested = false;
  }

  onSearch() {
    this.requested = true;

    this.search.search(this.start, this.destination, this.date);
    this.router.navigateByUrl('/connection');

  }
}
