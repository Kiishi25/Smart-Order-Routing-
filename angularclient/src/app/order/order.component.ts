import { Component, Directive, EventEmitter, Input, Output, QueryList, ViewChildren } from '@angular/core';

interface Order {
  id: number;
  instrument: string;
  price: number;
}

const BUY_ORDERS: Order[] = [
  {
    id: 1,
    instrument: 'BTC-USD',
    price: 30000,
  },
  {
    id: 2,
    instrument: 'BTC-EUR',
    price: 29000,
  },
  {
    id: 3,
    instrument: 'BTC-GBP',
    price: 22000,
  },
  {
    id: 4,
    instrument: 'ETH-USD',
    price: 43141,
  },
  {
    id: 5,
    instrument: 'ETH-EUR',
    price: 40132,
  },
  {
    id: 6,
    instrument: 'ETH-GBP',
    price: 34039,
  },
  {
    id: 7,
    instrument: 'XHL-USD',
    price: 29183,
  },
  {
    id: 8,
    instrument: 'XHL-EUR',
    price: 27314,
  },
  {
    id: 9,
    instrument: 'XHL-GBP',
    price: 22019,
  },
  {
    id: 10,
    instrument: 'NIO-USD',
    price: 109888,
  },
  {
    id: 11,
    instrument: 'NIO-EUR',
    price: 100232,
  },
  {
    id: 12,
    instrument: 'NIO-GBP',
    price: 91002,
  }
];

const SELL_ORDERS: Order[] = [
  {
    id: 1,
    instrument: 'BTC-USD',
    price: 35000,
  },
  {
    id: 2,
    instrument: 'BTC-EUR',
    price: 31000,
  },
  {
    id: 3,
    instrument: 'BTC-GBP',
    price: 24000,
  },
  {
    id: 4,
    instrument: 'ETH-USD',
    price: 45222,
  },
  {
    id: 5,
    instrument: 'ETH-EUR',
    price: 41345,
  },
  {
    id: 6,
    instrument: 'ETH-GBP',
    price: 32972,
  },
  {
    id: 7,
    instrument: 'XHL-USD',
    price: 31928,
  },
  {
    id: 8,
    instrument: 'XHL-EUR',
    price: 29615,
  },
  {
    id: 9,
    instrument: 'XHL-GBP',
    price: 25616,
  },
  {
    id: 10,
    instrument: 'NIO-USD',
    price: 111223,
  },
  {
    id: 11,
    instrument: 'NIO-EUR',
    price: 105626,
  },
  {
    id: 12,
    instrument: 'NIO-GBP',
    price: 97828,
  }
];

export type SortColumn = keyof Order | '';
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

  title: String;
  orders: Order[];

  page = 1;
  pageSize = 10;
  collectionSize = BUY_ORDERS.length;

  constructor() {
    this.title = "Orders";
    this.orders = BUY_ORDERS;

    this.refreshOrders();
  }

  @ViewChildren(OrderSortComponent) headers: QueryList<OrderSortComponent>;

  onSort({column, direction}: SortEvent) {
    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    // sorting orders
    if (direction === '' || column === '') {
      this.orders = BUY_ORDERS;
    } else {
      this.orders = [...BUY_ORDERS].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  refreshOrders() {
    this.orders = BUY_ORDERS
      .map((order, i) => ({id: i+1, ...order}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }
}
