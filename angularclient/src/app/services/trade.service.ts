import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@Angular/common/http';
import { Trade } from '../models/trade';
import { Observable } from 'rxjs';

@Injectable()
export class TradeService {

  private tradesUrl: string;

  constructor(private http: HttpClient) {
    this.tradesUrl = 'http://localhost:8080/trades';
  }

  public findAll(): Observable<Trade[]> {
    return this.http.get<Trade[]>(this.tradesUrl);
  }

  public save(trade: Trade) {
    return this.http.post<Trade>(this.tradesUrl, trade);
  }
}
