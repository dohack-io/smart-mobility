import { Component } from '@angular/core';
import {faMapMarkedAlt, faSearch, faSubway} from '@fortawesome/free-solid-svg-icons';


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
}
