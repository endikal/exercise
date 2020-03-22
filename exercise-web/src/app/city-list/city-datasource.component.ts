import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {Observable, BehaviorSubject, of} from "rxjs";
import {City} from "../city-model/city";
import {CityService} from "../city-service/city.service";
import {catchError, finalize, map} from "rxjs/operators";


export class CityDataSource implements DataSource<City> {

  private citySubject = new BehaviorSubject<City[]>([]);
  private totalSize = new BehaviorSubject<number>(0);

  public totalSize$ = this.totalSize.asObservable();

  constructor(private cityService: CityService) {}

  connect(collectionViewer: CollectionViewer): Observable<City[]> {
    return this.citySubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.citySubject.complete();
    this.totalSize.complete();
  }

  loadCities(filter = null, pageIndex = 0, pageSize = 5) {
    this.totalSize.next(0);

    this.cityService.findAll(filter,
      pageIndex, pageSize)
      .pipe(
        map(cityResponse => {
          this.totalSize.next(cityResponse.pagination.totalNumber);
          return cityResponse.cities;
        }),
        catchError(() => of([]))
      )
      .subscribe(cities => this.citySubject.next(cities));
    }
  }
