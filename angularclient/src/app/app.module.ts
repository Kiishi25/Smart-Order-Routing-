import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { TradeSortComponent, TradeComponent } from './trade/trade.component';
import { MarketComponent, MarketSortComponent } from './market/market.component';
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
import { LogoutComponent } from './logout/logout.component';

import { 
	IgxFinancialChartModule,
	IgxLegendModule
 } from "igniteui-angular-charts";
import { FinancialDataService } from "./services/financial-data.service";
import { FinancialChartMultipleDataComponent } from './market/financial-chart-multiple-data/financial-chart-multiple-data.component';

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
    TradeHistorySortComponent,
    MarketComponent,
    MarketSortComponent,
    LogoutComponent,
    FinancialChartMultipleDataComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    HttpClientModule,
    IgxFinancialChartModule,
    IgxLegendModule
  ],
  providers: [
    OrderBookService,
    UserService,
    UserController,
    FinancialDataService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
