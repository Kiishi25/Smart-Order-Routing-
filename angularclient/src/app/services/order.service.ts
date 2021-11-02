import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../models/Order';

@Injectable()
export class OrderService {
  
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

  httpClient: HttpClient;

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
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

}