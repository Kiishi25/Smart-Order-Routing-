import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { MarketComponent, MarketSortComponent } from './market/market.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ContactComponent } from './contact-us/contact.component';
import { HistorySortComponent, HistoryComponent } from './history/history.component';
import { AppRoutingModule } from './app-routing.module';


import { OrderBookService } from './services/orderBook.service';
import { UserService } from '../app/services/user.service';
import { OrderService } from '../app/services/order.service';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { HttpClientModule } from '@angular/common/http';
import { UserController } from '../app/controllers/user.controller';
import { OrderController } from '../app/controllers/order.controller';
import { LogoutComponent } from './logout/logout.component';

import {
	IgxFinancialChartModule,
	IgxLegendModule
 } from "igniteui-angular-charts";
import { FinancialDataService } from "./services/financial-data.service";
import { FinancialChartMultipleDataComponent } from './market/financial-chart-multiple-data/financial-chart-multiple-data.component';
import { OrderComponent } from './orders/orders.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    HistoryComponent,
    LoginComponent,
    RegisterComponent,
    ContactComponent,
    HistorySortComponent,
    MarketComponent,
    MarketSortComponent,
    LogoutComponent,
    FinancialChartMultipleDataComponent,
    OrderComponent
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
    OrderService,
    UserService,
    UserController,
    OrderController,
    FinancialDataService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
