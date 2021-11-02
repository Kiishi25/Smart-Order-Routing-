import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Order } from '../models/Order';


@Injectable()
export class OrderBookService {

  httpClient: HttpClient;

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
  }

  public async getAll(): Promise<Order[]> {

    let res: Order[] = null;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      })
    };

    var url = 'http://localhost:8080/orderBook';

    let response = await this.httpClient.get(url, httpOptions).toPromise();
    if (response) {
      res = response as Order[];
    }

    return res;
  }

}
