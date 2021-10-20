import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { OrderComponent } from './order.component';


@NgModule({
  declarations: [
    OrderComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [OrderComponent]
})
export class OrderModule { }
