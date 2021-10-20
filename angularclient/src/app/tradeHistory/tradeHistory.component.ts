import { Component } from '@angular/core';

@Component({
  selector: 'tradeHistory-root',
  templateUrl: './tradeHistory.component.html',
  styleUrls: ['./tradeHistory.component.css']
})
export class TradeHistoryComponent {
  title: String;

  constructor() {
    this.title = "Trade History";
  }
}
