import { Component, Directive, EventEmitter, Input, Output, QueryList, ViewChildren, Inject } from '@angular/core';
import { OrderBookController } from '../controllers/orderBook.controller';
import { OrderBook } from '../models/OrderBook';

// const BUY_ORDERS: OrderBook[] = [
//   {
//     orderBookID: 1,
//     instrumentName: 'BTC-USD',
//     marketValue: 30000,
//   },
//   {
//     orderBookID: 2,
//     instrumentName: 'BTC-EUR',
//     marketValue: 29000,
//   },
//   {
//     orderBookID: 3,
//     instrumentName: 'BTC-GBP',
//     marketValue: 22000,
//   },
//   {
//     orderBookID: 4,
//     instrumentName: 'ETH-USD',
//     marketValue: 43141,
//   },
//   {
//     orderBookID: 5,
//     instrumentName: 'ETH-EUR',
//     marketValue: 40132,
//   },
//   {
//     orderBookID: 6,
//     instrumentName: 'ETH-GBP',
//     marketValue: 34039,
//   },
//   {
//     orderBookID: 7,
//     instrumentName: 'XHL-USD',
//     marketValue: 29183,
//   },
//   {
//     orderBookID: 8,
//     instrumentName: 'XHL-EUR',
//     marketValue: 27314,
//   },
//   {
//     orderBookID: 9,
//     instrumentName: 'XHL-GBP',
//     marketValue: 22019,
//   },
//   {
//     orderBookID: 10,
//     instrumentName: 'NIO-USD',
//     marketValue: 109888,
//   },
//   {
//     orderBookID: 11,
//     instrumentName: 'NIO-EUR',
//     marketValue: 100232,
//   },
//   {
//     orderBookID: 12,
//     instrumentName: 'NIO-GBP',
//     marketValue: 91002,
//   }
// ];

// const SELL_ORDERS: OrderBook[] = [
//   {
//     orderBookID: 1,
//     instrumentName: 'BTC-USD',
//     marketValue: 35000,
//   },
//   {
//     orderBookID: 2,
//     instrumentName: 'BTC-EUR',
//     marketValue: 31000,
//   },
//   {
//     orderBookID: 3,
//     instrumentName: 'BTC-GBP',
//     marketValue: 24000,
//   },
//   {
//     orderBookID: 4,
//     instrumentName: 'ETH-USD',
//     marketValue: 45222,
//   },
//   {
//     orderBookID: 5,
//     instrumentName: 'ETH-EUR',
//     marketValue: 41345,
//   },
//   {
//     orderBookID: 6,
//     instrumentName: 'ETH-GBP',
//     marketValue: 32972,
//   },
//   {
//     orderBookID: 7,
//     instrumentName: 'XHL-USD',
//     marketValue: 31928,
//   },
//   {
//     orderBookID: 8,
//     instrumentName: 'XHL-EUR',
//     marketValue: 29615,
//   },
//   {
//     orderBookID: 9,
//     instrumentName: 'XHL-GBP',
//     marketValue: 25616,
//   },
//   {
//     orderBookID: 10,
//     instrumentName: 'NIO-USD',
//     marketValue: 111223,
//   },
//   {
//     orderBookID: 11,
//     instrumentName: 'NIO-EUR',
//     marketValue: 105626,
//   },
//   {
//     orderBookID: 12,
//     instrumentName: 'NIO-GBP',
//     marketValue: 97828,
//   }
// ];

export type SortColumn = keyof OrderBook | '';
export type SortDirection = 'asc' | 'desc' | '';
const rotate: {[key: string]: SortDirection} = { 'asc': 'desc', 'desc': '', '': 'asc' };

const compare = (v1: string | number, v2: string | number) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

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
  selector: 'order-root',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent {

  @ViewChildren(OrderSortComponent) headers: QueryList<OrderSortComponent>;

  title: String;
  orders: OrderBook[];
  collectionSize: Number;

  page = 1;
  pageSize = 10;
  // collectionSize = BUY_ORDERS.length;

  constructor(@Inject(OrderBookController) private orderBookController: OrderBookController) {
    this.title = "Instruments";
    this.orderBookController = orderBookController;
    this.orders = [];
    this.collectionSize = this.orders.length;

    this.refreshOrders();
  }

  async onSort({column, direction}: SortEvent) {
    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    // sorting orders
    if (direction === '' || column === '') {
      this.orders = (await this.orderBookController.getAll());
    } else {
      this.orders = [...(await this.orderBookController.getAll())].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  async refreshOrders() {
    this.orders = (await this.orderBookController.getAll())
    // this.orders = BUY_ORDERS
      .map((order, i) => ({id: i+1, ...order}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }
}
