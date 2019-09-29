import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {SearchService} from '../service/search.service';
import {faHospitalSymbol, faMapMarkedAlt, faSearch, faSubway} from '@fortawesome/free-solid-svg-icons';
import {TripResult} from '../../entities/vrr/TripResult';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.scss']
})
export class SearchPageComponent implements OnInit, OnChanges {

  start = 'Adessoplatz';
  destination = 'Hoevelstrasse';
  date: Date = new Date();
  time = '12:12';
  requested = false;

  req: TripResult;

  faSearch = faSearch;
  faMapMarkedAlt = faMapMarkedAlt;
  faSubway = faSubway;
  faLogo = faHospitalSymbol;


  constructor(private router: Router, private search: SearchService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    console.log(this.route.snapshot.paramMap.keys);
    this.requested = false;
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.route.snapshot.paramMap);
    if (this.route.snapshot.paramMap.get('search')) {
      this.requested = false;
    }

  }

  onRequest() {
    this.requested = true;
    const boolVrr = this.search.searchVrr(this.start, this.destination, this.changeTime(this.time).toJSON(), 'DEPARTURE');
    const boolCirc = this.search.searchCirc(this.start, this.destination, this.changeTime(this.time).toJSON(), 'DEPARTURE')
    this.router.navigateByUrl('/search/connection');

  }



  changeTime(time: string): Date {
    const minute = time.split(':');
    const dateS = new Date('09-29-2019').getTime();
    const sum = dateS + +minute[0] * 3600000 + +minute[1] * 60000;
    return new Date(sum);
  }

  onSearch() {
    this.router.navigateByUrl('/search');
  }
}
