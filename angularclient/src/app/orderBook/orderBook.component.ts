import { Component, Directive, EventEmitter, Input, Output, QueryList, ViewChildren, Inject } from '@angular/core';
import { OrderBookController } from '../controllers/orderBook.controller';
import { OrderBook } from '../models/OrderBook';
import { FormBuilder } from '@angular/forms';
import { User } from '../models/User';

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

  @ViewChildren(OrderBookSortComponent) headers: QueryList<OrderBookSortComponent>;

  title: String;
  orderBooks: OrderBook[];
  collectionSize: Number;
  orderForm;
  isOrderCreated = false;
  error: String;

  page = 1;
  pageSize = 10;
  // collectionSize = BUY_ORDERS.length;

  constructor(@Inject(OrderBookController) private orderBookController: OrderBookController, private formBuilder: FormBuilder) {
    this.title = "Instruments";
    this.orderBookController = orderBookController;
    this.orderBooks = [];
    this.collectionSize = this.orderBooks.length;

    this.refreshOrderBooks();

    this.orderForm = this.formBuilder.group({
      orderType: '',
      inputQty: '',
      priceLimit: '',
      buysellradio: '',
      orderBook: ''
    });
  }

  async onSort({column, direction}: SortEvent) {
    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    let temp = (await this.orderBookController.getAll());
    // sorting orderBooks
    if (direction === '' || column === '') {
      this.orderBooks = temp;
    } else {
      this.orderBooks = [...temp].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  async refreshOrderBooks() {
    let temp = (await this.orderBookController.getAll());
    this.orderBooks = temp
      .map((orderBook, i) => ({id: i+1, ...orderBook}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
    this.collectionSize = temp
      .length;
  }

  onSubmit() {
    
    let componentInstance = this;
    console.warn('Your order has been submitted', this.orderForm.value);

    let user = JSON.parse(localStorage['user']);
   this.orderBookController.createOrder(this.orderForm.controls.buysellradio.value, this.orderForm.controls.orderType.value, this.orderForm.controls.priceLimit.value, this.orderForm.controls.inputQty.value, user['username'], this.orderForm.controls.orderBook.value)
   .then(function(response){
     if(response){
      componentInstance.isOrderCreated = true;
      componentInstance.error='';
     }else{
      componentInstance.error = 'Could not create order. Please try again';
      componentInstance.isOrderCreated = false;
     }
   })


    this.orderForm.reset();
  }
}
