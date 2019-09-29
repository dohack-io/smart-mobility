import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SearchPageComponent} from './search-page/search-page.component';
import {ConnectionComponent} from './connection/connection.component';


const routes: Routes = [
  {path: 'search', component: SearchPageComponent, children: [
      {path: 'connection', component: ConnectionComponent},
    ]},

  {path: '', redirectTo: 'search', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppRoutingModule {
}
