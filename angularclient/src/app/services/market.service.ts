import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Instrument } from '../models/Instrument';

@Injectable({
  providedIn: 'root'
})
export class MarketService {

  httpClient: HttpClient;

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
  }  

  public async loadInstruments(){
    let instruments: Instrument[] = null;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      })
    };

    var url = 'http://localhost:8080/instruments';
    
    let response = await this.httpClient.get(url, httpOptions).toPromise();
    
    if (response) {
      console.log("performed instruments request");
      
      instruments = response as Instrument[];
    }

    return instruments;
  }
  
}
