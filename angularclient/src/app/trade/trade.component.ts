import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'trade-root',
  templateUrl: './trade.component.html',
  styleUrls: ['./trade.component.css']
})
export class TradeComponent {
  title: String;

  constructor(
    private route: ActivatedRoute
  ) {
    this.title = "Trades";
  }
}
