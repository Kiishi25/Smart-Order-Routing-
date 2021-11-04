import { Component, Inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderController } from '../../controllers/order.controller';
import { Order } from '../../models/Order';
import { User } from '../../models/User';

@Component({
  selector: 'order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderFormComponent {
  title: string;
  order: Order;
  instrumentCode: string;

  buyOrSell = ['BUY', 'SELL'];
  orderTypes = ['MARKET', 'LIMIT', 'HIDDEN', 'TIMED'];

  constructor(@Inject(OrderController) private orderController: OrderController, private _router: Router, public _route: ActivatedRoute) {
    this.title = "Create Order";
    this.orderController = orderController;

    this.order = {} as Order;
    this.instrumentCode = this._route.snapshot.paramMap.get('code');
  }

  async submitOrderForm() {
    let userStr = localStorage.getItem('user');
    let user = userStr ? JSON.parse(userStr) as User : undefined;
    let username = user.username;

    let res = await this.orderController.createOrder(this.order.buyOrSell, 'MARKET', this.order.priceLimit, this.order.shareQuantity, username, this.instrumentCode);

    // Order created successfully
    if(res) {
      this._router.navigate(['/orders']);
    }
  }
}
