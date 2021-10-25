import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { TradeSortComponent, TradeComponent } from './trade/trade.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { OrderBookSortComponent, OrderBookComponent } from './orderBook/orderBook.component';
import { ContactComponent } from './contact-us/contact.component';
import { TradeHistorySortComponent, TradeHistoryComponent } from './tradeHistory/tradeHistory.component';
import { AppRoutingModule } from './app-routing.module';

import { OrderBookService } from './services/orderBook.service';
import { UserService } from '../app/services/user.service';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { HttpClientModule } from '@angular/common/http';
import { UserController } from '../app/controllers/user.controller';


@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    TradeComponent,
    OrderBookComponent,
    TradeHistoryComponent,
    LoginComponent,
    RegisterComponent,
    ContactComponent,
    OrderBookSortComponent,
    TradeSortComponent,
    TradeHistorySortComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [
    OrderBookService,
    UserService,
    UserController
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
