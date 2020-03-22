import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CityResponse } from '../city-model/city-response';

@Injectable()
export class CityService {

  private citiesUrl: string;

  constructor(private http: HttpClient) {
    this.citiesUrl = 'http://localhost:8080/api/cities';
  }

  public findAll(name: string, page: number, size: number) {
    let params = new HttpParams()
    .set('page', page.toString())
    .set('size', size.toString());

    if(name) {
      params = params.set('name', name);
    }

    return this.http.get<CityResponse>(this.citiesUrl, { params: params });
  }
}
