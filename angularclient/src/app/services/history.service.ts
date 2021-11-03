import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { History } from '../models/History';


@Injectable()
export class HistoryService {

  httpClient: HttpClient;

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
  }

  public async getAll(): Promise<History[]> {

    let res: History[] = null;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      })
    };

    var url = 'http://localhost:8080/history';

    let response = await this.httpClient.get(url, httpOptions).toPromise();
    if (response) {
      res = response as History[];
    }

    return res;
  }

}
