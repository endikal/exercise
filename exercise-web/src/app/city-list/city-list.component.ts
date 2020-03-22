import { AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { ActivatedRoute} from "@angular/router";
import { City } from '../city-model/city';
import { CityService } from '../city-service/city.service';
import { MatPaginator } from "@angular/material/paginator";
import { fromEvent, of as observableOf} from "rxjs";
import { debounceTime, distinctUntilChanged, tap} from 'rxjs/operators';
import { MatTableDataSource} from '@angular/material/table';
import { CityDataSource } from './city-datasource.component';

@Component({
  selector: 'app-city-list',
  styleUrls: ['./city-list.component.less'],
  templateUrl: './city-list.component.html'
})
export class CityListComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['id', 'name'];
  dataSource : CityDataSource;

  totalResults = 0;
  filterName = null;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild('input') input: ElementRef;


  constructor(private cityService: CityService) {}

  ngOnInit() {
    this.dataSource = new CityDataSource(this.cityService);
    this.dataSource.loadCities();
  }

  ngAfterViewInit() {
    fromEvent(this.input.nativeElement,'keyup')
    .pipe(
      debounceTime(150),
      distinctUntilChanged(),
      tap(() => {
        this.paginator.pageIndex = 0;
        this.refreshDataTable();
      })
    )
    .subscribe();

    this.paginator.page
    .pipe(
      tap(() => this.refreshDataTable())
    )
    .subscribe();
  }

  refreshDataTable() {
    this.dataSource.loadCities(
      this.input.nativeElement.value,
      this.paginator.pageIndex,
      this.paginator.pageSize);
    }
  }
