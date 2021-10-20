import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { TradeHistoryComponent } from './tradeHistory.component';


@NgModule({
  declarations: [
    TradeHistoryComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [TradeHistoryComponent]
})
export class TradeHistoryModule { }
