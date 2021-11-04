import { Component, Directive, EventEmitter, Inject, Input, Output, QueryList, ViewChildren } from '@angular/core';
import { HistoryController } from '../controllers/history.controller';
import { History } from '../models/History';
import { Order } from '../models/Order';
import { User } from '../models/User';

export type SortColumn = keyof History | '';
export type SortDirection = 'asc' | 'desc' | '';
const rotate: {[key: string]: SortDirection} = { 'asc': 'desc', 'desc': '', '': 'asc' };

const compare = (v1: string | number | Order, v2: string | number | Order) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;
let historyList: History[] = [];

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
export class HistorySortComponent {

  @Input() sortable: SortColumn = '';
  @Input() direction: SortDirection = '';
  @Output() sort = new EventEmitter<SortEvent>();

  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({column: this.sortable, direction: this.direction});
  }
}

@Component({
  selector: 'history-root',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent {
  title: String;
  history: History[];

  page = 1;
  pageSize = 10;
  collectionSize;

  constructor(@Inject(HistoryController) private historyController: HistoryController) {
    // Preserve MarketComponent's instance so it can be accessed inside the then function of the loadInstruments' promise
    var historyComponent = this;
    this.title = "History";
    let userStr = localStorage.getItem('user');
    let user = userStr ? JSON.parse(userStr) as User : undefined;
    let username = user.username;

    this.historyController.getAll(username).then(function (list) {
      console.log(`Result: ${JSON.stringify(list)}`);
      historyComponent.history = list;
      historyComponent.collectionSize = list.length;
      historyList = list;
    });

    this.refreshHistory();
  }

  @ViewChildren(HistorySortComponent) headers: QueryList<HistorySortComponent>;

  onSort({column, direction}: SortEvent) {
    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    // sorting history
    if (direction === '' || column === '') {
      this.history = historyList;
    } else {
      this.history = [...historyList].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  refreshHistory() {
    this.history = historyList
      .map((history, i) => ({id: i+1, ...history}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }
}
