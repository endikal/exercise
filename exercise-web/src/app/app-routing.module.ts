import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CityHomeComponent } from './city-home/city-home.component';
import { CityListComponent } from './city-list/city-list.component';

const routes: Routes = [
  { path: '', component: CityHomeComponent },
  { path: 'cities', component: CityListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
