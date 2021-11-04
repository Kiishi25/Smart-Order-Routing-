import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../models/Order';

@Injectable()
export class OrderService {
  httpClient: HttpClient;

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
  }

  async cancelOrder(orderId: any) {
    let res: Boolean = false;

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      })

    };

    var url = `http://localhost:8080/cancelOrder/${orderId}`;

    let response = await this.httpClient.put(url, {}, httpOptions).toPromise();
    if (response) {
      res = true;
    }

    return res;
  }

  async executeOrder(username: String) {
    let res: Boolean = false;

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      })

    };

    var url = `http://localhost:8080/executeOrder/${username}`;

    let response = await this.httpClient.post(url, {}, httpOptions).toPromise();
    if (response) {
      res = true;
    }

    return res;
  }

  public async getOrdersByUsername(username: String) {

    let res: Order[] = null;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      })

    };

    var url = `http://localhost:8080/orders/${username}`;

    let response = await this.httpClient.get(url, httpOptions).toPromise();
    if (response) {
      res = response as Order[];
    }

    return res;
  }

  public async createOrder(buyOrSell, type, priceLimit, shareQuantity, username, instrumentCode) {

    let res: Boolean = false;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      })
    };

    var url = 'http://localhost:8080/order';

    var body = {
      orderBook: {
          instrument: {
            code: instrumentCode
          }
      },
      buyOrSell,
      type,
      shareQuantity,
      priceLimit,
      user: {
          username
      }
    };

    let response = await this.httpClient.post(url, body, httpOptions).toPromise();
    if (response) {
      res = response as Boolean;
    }

    return res;
  }

}
