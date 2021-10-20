import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { TradeComponent } from './trade.component';


@NgModule({
  declarations: [
    TradeComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [TradeComponent]
})
export class TradeModule { }
