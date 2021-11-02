import { Component, Inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderController } from '../controllers/order.controller';
import { Order } from '../models/Order';
import { User } from '../models/User';

@Component({
  selector: 'orderBook',
  templateUrl: './orderBook.component.html',
  styleUrls: ['./orderBook.component.css']
})
export class OrderBookComponent {
  title: string;
  orderBook: Order;
  instrumentCode: string;

  buyOrSell = ['BUY', 'SELL'];
  orderTypes = ['MARKET', 'LIMIT', 'HIDDEN', 'TIMED'];

  constructor(@Inject(OrderController) private orderController: OrderController, private _router: Router, public _route: ActivatedRoute) {
    this.title = "OrderBook";
    this.orderController = orderController;

    this.orderBook = {} as Order;
    this.instrumentCode = this._route.snapshot.paramMap.get('code');
  }

  async submitOrderBook() {
    let userStr = localStorage.getItem('user');
    let user = userStr ? JSON.parse(userStr) as User : undefined;
    let username = user.username;

    let res = await this.orderController.createOrder(this.orderBook.buyOrSell, this.orderBook.orderType, this.orderBook.priceLimit, this.orderBook.shareQuantity, username, this.instrumentCode);

    // Order created successfully
    if(res) {
      this._router.navigate(['/orders']);
    }
  }
}
