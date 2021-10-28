import { Component, Directive, EventEmitter, Inject, Input, Output, QueryList, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';
import { UserController } from '../controllers/user.controller';

interface Market {
    code: string;
    company: string;
    price: string;
    open: string;
    change: string;
    high: string;
    low: string;
    volume: number;
}

const MARKET: Market[] = [
    {
    code: "AAC",
    company: "Australian Agricultural Company",
    price: "$1.38",
    change: "-0.01",
    open: "$1.39",
    high: "$1.39",
    low: "$1.38",
    volume: 9.39
    },
    {
    code: "AAD",
    company: "Ardent Leisure Group",
    price: "$1.15",
    open: "$1.14",
    change: "+0.02",
    high: "$1.15",
    low: "$1.13",
    volume: 56.431
    },
    {
    code: "AAX",
    company: "Ausenco Limited",
    price: "$4.00",
    open: "$4.01",
    change: "-0.04",
    high: "$4.05",
    low: "$14.00",
    volume: 90.641
    },
    {
    code: "ABC",
    company: "Adelaide Brighton Limited",
    price: "$3.00",
    open: "$2.98",
    change: "+0.06",
    high: "$3.00",
    low: "$2.96",
    volume: 862.518
    },
    {
    code: "ABP",
    company: "Abarcus Property Group",
    price: "$1.91",
    open: "$1.92",
    change: "0.00",
    high: "$1.93",
    low: "$1.90",
    volume: 595.701
    },
    {
    code: "ABY",
    company: "Aditya Birla Minerals Limited",
    price: "$0.77",
    open: "$0.76",
    change: "+0.02",
    high: "$0.77",
    low: "$0.76",
    volume: 54.567
    },
    {
    code: "ACR",
    company: "Acrux Limited",
    price: "$3.71",
    open: "$3.70",
    change: "+0.01",
    high: "$3.72",
    low: "$3.68",
    volume: 191.373
    },
    
];

export type SortColumn = keyof Market | '';
export type SortDirection = 'asc' | 'desc' | '';
const rotate: { [key: string]: SortDirection } = { 'asc': 'desc', 'desc': '', '': 'asc' };

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

export class MarketSortComponent {

    @Input() sortable: SortColumn = '';
    @Input() direction: SortDirection = '';
    @Output() sort = new EventEmitter<SortEvent>();
  
    rotate() {
      this.direction = rotate[this.direction];
      this.sort.emit({column: this.sortable, direction: this.direction});
    }
  }

@Component({
    selector: 'market',
    templateUrl: './market.component.html',
    styleUrls: ['./market.component.css']
})

export class MarketComponent {
    title: String;
  markets: Market[];

  page = 1;
  pageSize = 10;
  collectionSize = MARKET.length;

  constructor() {
    this.title = "Market";
    this.markets = MARKET;

   
  }

  @ViewChildren(MarketSortComponent) headers: QueryList<MarketSortComponent>;

  onSort({column, direction}: SortEvent) {
    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    // sorting Markets
    if (direction === '' || column === '') {
      this.markets = MARKET;
    } else {
      this.markets = [...MARKET].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  refreshMarkets() {
    this.markets = MARKET
      .map((market, i) => ({id: i+1, ...market}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }
}