import { Component, Directive, EventEmitter, Input, Output, QueryList, ViewChildren } from '@angular/core';
import { OrderBook } from '../models/OrderBook';

const ORDER_BOOK: OrderBook[] = [];

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
export class OrderBookSortComponent {

  @Input() sortable: SortColumn = '';
  @Input() direction: SortDirection = '';
  @Output() sort = new EventEmitter<SortEvent>();

  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({column: this.sortable, direction: this.direction});
  }
}

@Component({
  selector: 'orderBook-root',
  templateUrl: './orderBook.component.html',
  styleUrls: ['./orderBook.component.css']
})
export class OrderBookComponent {

  title: String;
  orderBook: OrderBook[];

  page = 1;
  pageSize = 10;
  collectionSize = ORDER_BOOK.length;

  constructor() {
    this.title = "OrderBook";
    this.orderBook = ORDER_BOOK;

    this.refreshOrderBook();
  }

  @ViewChildren(OrderBookSortComponent) headers: QueryList<OrderBookSortComponent>;

  onSort({column, direction}: SortEvent) {
    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    // sorting orderBook
    if (direction === '' || column === '') {
      this.orderBook = ORDER_BOOK;
    } else {
      this.orderBook = [...ORDER_BOOK].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  refreshOrderBook() {
    this.orderBook = ORDER_BOOK
      .map((orderBook, i) => ({id: i+1, ...orderBook}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }
}
