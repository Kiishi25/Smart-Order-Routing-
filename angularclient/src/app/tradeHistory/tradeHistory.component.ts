import { Component, Directive, EventEmitter, Input, Output, QueryList, ViewChildren } from '@angular/core';

interface TradeHistory {
  id: number;
  order: string;
  qty: number;
  price: number;
  ts: string;
}

const TRADE_HISTORY: TradeHistory[] = [
  {
    id: 1,
    order: 'BTC-USD',
    qty: 50,
    price: 30000,
    ts: '2021-11-05 15:13:33'
  },
  {
    id: 2,
    order: 'BTC-EUR',
    qty: 600,
    price: 29000,
    ts: '2021-11-05 15:13:33'
  },
  {
    id: 3,
    order: 'BTC-GBP',
    qty: 1000,
    price: 22000,
    ts: '2021-11-05 15:13:33'
  },
  {
    id: 4,
    order: 'ETH-USD',
    qty: 500,
    price: 43141,
    ts: '2021-11-05 15:13:33'
  },
  {
    id: 5,
    order: 'ETH-EUR',
    qty: 2000,
    price: 40132,
    ts: '2021-11-05 15:13:33'
  },
  {
    id: 6,
    order: 'ETH-GBP',
    qty: 1400,
    price: 34039,
    ts: '2021-11-05 15:13:33'
  },
  {
    id: 7,
    order: 'XHL-USD',
    qty: 5,
    price: 29183,
    ts: '2021-11-05 15:13:33'
  },
  {
    id: 8,
    order: 'XHL-EUR',
    qty: 2000,
    price: 27314,
    ts: '2021-11-05 15:13:33'
  },
  {
    id: 9,
    order: 'XHL-GBP',
    qty: 800,
    price: 22019,
    ts: '2021-11-05 15:12:34'
  },
  {
    id: 10,
    order: 'NIO-USD',
    qty: 10000,
    price: 109888,
    ts: '2021-11-05 12:13:33'
  },
  {
    id: 11,
    order: 'NIO-EUR',
    qty: 30,
    price: 100232,
    ts: '2021-11-05 13:13:33'
  },
  {
    id: 12,
    order: 'NIO-GBP',
    qty: 150,
    price: 91002,
    ts: '2021-11-05 10:15:33'
  }
];

export type SortColumn = keyof TradeHistory | '';
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
export class TradeHistorySortComponent {

  @Input() sortable: SortColumn = '';
  @Input() direction: SortDirection = '';
  @Output() sort = new EventEmitter<SortEvent>();

  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({column: this.sortable, direction: this.direction});
  }
}

@Component({
  selector: 'tradeHistory-root',
  templateUrl: './tradeHistory.component.html',
  styleUrls: ['./tradeHistory.component.css']
})
export class TradeHistoryComponent {

  title: String;
  tradeHistories: TradeHistory[];

  page = 1;
  pageSize = 10;
  collectionSize = TRADE_HISTORY.length;

  constructor() {
    this.title = "Trade History";
    this.tradeHistories = TRADE_HISTORY;

    this.refreshTradeHistories();
  }

  @ViewChildren(TradeHistorySortComponent) headers: QueryList<TradeHistorySortComponent>;

  onSort({column, direction}: SortEvent) {
    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    // sorting tradeHistories
    if (direction === '' || column === '') {
      this.tradeHistories = TRADE_HISTORY;
    } else {
      this.tradeHistories = [...TRADE_HISTORY].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  refreshTradeHistories() {
    this.tradeHistories = TRADE_HISTORY
      .map((tradeHistory, i) => ({id: i+1, ...tradeHistory}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }
}
