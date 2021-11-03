import { Component, Directive, EventEmitter, Inject, Input, Output, QueryList, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';
import { MarketController } from '../controllers/market.controller';
import { UserController } from '../controllers/user.controller';
import { Instrument } from '../models/Instrument';

export type SortColumn = keyof Instrument | '';
export type SortDirection = 'asc' | 'desc' | '';

const rotate: { [key: string]: SortDirection } = { 'asc': 'desc', 'desc': '', '': 'asc' };

const compare = (v1: string | number | String, v2: string | number | String) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;
let marketsList: Instrument[];

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

export class MarketSortComponent {

  @Input() sortable: SortColumn = '';
  @Input() direction: SortDirection = '';
  @Output() sort = new EventEmitter<SortEvent>();

  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({ column: this.sortable, direction: this.direction });
  }
}

@Component({
  selector: 'market',
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.css']
})

export class MarketComponent {
  title: String;
  markets: Instrument[];

  page = 1;
  pageSize = 10;
  collectionSize;

  constructor(@Inject(MarketController) private marketController: MarketController) {
    // Preserve MarketComponent's instance so it can be accessed inside the then function of the loadInstruments' promise
    var marketComponent = this;
    this.title = "Market";

    this.marketController.loadInstruments().then(function (list) {
      console.log(`Result: ${JSON.stringify(list)}`);
      marketComponent.markets = list;
      marketComponent.collectionSize = list.length;
      marketsList = list;
    });

  }

  @ViewChildren(MarketSortComponent) headers: QueryList<MarketSortComponent>;

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    // sorting market
    if (direction === '' || column === '') {
      this.markets = marketsList;
    } else {
      this.markets = [...marketsList].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  refreshMarkets() {
    this.markets = marketsList
      .map((market, i) => ({ id: i + 1, ...market }))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }
}
