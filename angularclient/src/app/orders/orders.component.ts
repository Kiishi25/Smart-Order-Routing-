import { Component, Directive, EventEmitter, Input, Output, QueryList, ViewChildren } from '@angular/core';
import { OrderController } from '../controllers/order.controller';
import { Order } from '../models/Order';

export type SortColumn = keyof Order | '';
export type SortDirection = 'asc' | 'desc' | '';
const rotate: {[key: string]: SortDirection} = { 'asc': 'desc', 'desc': '', '': 'asc' };

const compare = (v1: string | number, v2: string | number) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;
let ORDERS: Order[];

export interface SortEvent {
  column: SortColumn;
  direction: SortDirection;
}

@Directive({
  selector: 'th[sortable]',
  host: {
    '[class.asc]': 'direction === "asc"',
    '[class.desc]': 'direction === "desc"',
    '(click)': 'rotate()'
  }
})
export class OrderSortComponent {

  @Input() sortable: SortColumn = '';
  @Input() direction: SortDirection = '';
  @Output() sort = new EventEmitter<SortEvent>();

  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({column: this.sortable, direction: this.direction});
  }
}

@Component({
  selector: 'orders-root',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrderComponent {
  title: String;
  orders: Order[];
  orderController: OrderController;

  page = 1;
  pageSize = 10;
  collectionSize;
  isOrderDeleted;

  constructor(orderController: OrderController) {
    this.orderController = orderController;
    var componentInstance = this;
    let user = JSON.parse(localStorage['user']);
    orderController.getOrdersByUsername(user['username'])
    .then(function(response){
      if(response){
       componentInstance.orders = response;
       componentInstance.collectionSize = response.length;
       ORDERS = response;
      }
    })
    this.title = "Orders";
  }

  @ViewChildren(OrderSortComponent) headers: QueryList<OrderSortComponent>;

  onSort({column, direction}: SortEvent) {
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });
  }

  async refreshOrders() {
    this.orders = ORDERS
      .map((trade, i) => ({id: i+1, ...trade}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);

  }

  async cancelOrder(orderId){

    let res = await this.orderController.cancelOrder(orderId);

    // Order deleted successfully
    if(res){
      await this.refreshOrders();
      this.isOrderDeleted = true;
    }else{
      this.isOrderDeleted = false;
    }
  }
}
