import { Component } from '@angular/core';
import {faMapMarkedAlt, faSearch, faSubway} from '@fortawesome/free-solid-svg-icons';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'smart-mobility';

  faSearch = faSearch;
  faMapMarkedAlt = faMapMarkedAlt;
  faSubway = faSubway;

  constructor(private router: Router) {

  }

  onSearch() {
    this.router.navigateByUrl('/search');
    // window.location="/search";
  }
}
