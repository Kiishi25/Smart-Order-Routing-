import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { OrderBook } from '../models/OrderBook';


@Injectable()
export class OrderBookService {

  httpClient: HttpClient;

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
  }

  public async getAll(): Promise<OrderBook[]> {

    let res: OrderBook[] = null;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      })
    };

    var url = 'http://localhost:8080/orderBook';

    let response = await this.httpClient.get(url, httpOptions).toPromise();
    if (response) {
      res = response as OrderBook[];
    }

    return res;
  }

  public async createOrder(buyOrSell, orderType, priceLimit, quantity, username, orderBookId) {

    let res: Boolean = false;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      })
    };

    var url = 'http://localhost:8080/order';

    var body = {
      "buyOrSell": buyOrSell,
      "type": orderType,
      "priceLimit": priceLimit,
      "shareQuantity": quantity,
      "user": {
        "username": username
      },
      "orderBook": {
        "orderBookID": orderBookId
      }
    };

    let response = await this.httpClient.post(url, body, httpOptions).toPromise();
    if (response) {
      res = response as Boolean;
    }

    return res;
  }

}
