import { Component, Directive, EventEmitter, Input, Output, QueryList, ViewChildren } from '@angular/core';

interface Trade {
  id: number;
  order: string;
  qty: number;
  price: number;
  status: string;
}

const TRADES: Trade[] = [
  {
    id: 1,
    order: 'BTC-USD',
    qty: 50,
    price: 30000,
    status: 'New'
  },
  {
    id: 2,
    order: 'BTC-EUR',
    qty: 4000,
    price: 29000,
    status: 'Partially Filled'
  },
  {
    id: 3,
    order: 'BTC-GBP',
    qty: 300,
    price: 22000,
    status: 'Partially Filled'
  },
  {
    id: 4,
    order: 'ETH-USD',
    qty: 60,
    price: 43141,
    status: 'Partially Filled'
  },
  {
    id: 5,
    order: 'ETH-EUR',
    qty: 2300,
    price: 40132,
    status: 'Partially Filled'
  },
  {
    id: 6,
    order: 'ETH-GBP',
    qty: 1300,
    price: 34039,
    status: 'Partially Filled'
  },
  {
    id: 7,
    order: 'XHL-USD',
    qty: 100,
    price: 29183,
    status: 'Partially Filled'
  },
  {
    id: 8,
    order: 'XHL-EUR',
    qty: 15,
    price: 27314,
    status: 'New'
  },
  {
    id: 9,
    order: 'XHL-GBP',
    qty: 500,
    price: 22019,
    status: 'Partially Filled'
  },
  {
    id: 10,
    order: 'NIO-USD',
    qty: 2300,
    price: 109888,
    status: 'Partially Filled'
  },
  {
    id: 11,
    order: 'NIO-EUR',
    qty: 1400,
    price: 100232,
    status: 'Partially Filled'
  },
  {
    id: 12,
    order: 'NIO-GBP',
    qty: 5000,
    price: 91002,
    status: 'New'
  }
];

export type SortColumn = keyof Trade | '';
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
export class TradeSortComponent {

  @Input() sortable: SortColumn = '';
  @Input() direction: SortDirection = '';
  @Output() sort = new EventEmitter<SortEvent>();

  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({column: this.sortable, direction: this.direction});
  }
}

@Component({
  selector: 'trade-root',
  templateUrl: './trade.component.html',
  styleUrls: ['./trade.component.css']
})
export class TradeComponent {

  title: String;
  trades: Trade[];

  page = 1;
  pageSize = 10;
  collectionSize = TRADES.length;

  constructor() {
    this.title = "Trades";
    this.trades = TRADES;

    this.refreshTrades();
  }

  @ViewChildren(TradeSortComponent) headers: QueryList<TradeSortComponent>;

  onSort({column, direction}: SortEvent) {
    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    // sorting trades
    if (direction === '' || column === '') {
      this.trades = TRADES;
    } else {
      this.trades = [...TRADES].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  refreshTrades() {
    this.trades = TRADES
      .map((trade, i) => ({id: i+1, ...trade}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }
}
