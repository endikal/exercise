import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { CityListComponent } from './city-list/city-list.component';
import { CityService } from './city-service/city.service';
import { CityHomeComponent } from './city-home/city-home.component';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';

@NgModule({
  declarations: [
    AppComponent,
    CityListComponent,
    CityHomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatInputModule,
    MatPaginatorModule,
    MatTableModule
  ],
  providers: [CityService],
  bootstrap: [AppComponent]
})
export class AppModule { }
