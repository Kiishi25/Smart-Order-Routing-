import { Component } from '@angular/core';

@Component({
  selector: 'order-root',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent {
  title: String;

  constructor() {
    this.title = "Orders";
  }
}
