import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { TradeComponent } from './trade/trade.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { OrderComponent } from './order/order.component';
import { ContactComponent } from './contact-us/contact.component';
import { TradeHistoryComponent } from './tradeHistory/tradeHistory.component';
import { AppRoutingModule } from './app-routing.module';

import { TradeService } from '../service/trade.service';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    TradeComponent,
    OrderComponent,
    TradeHistoryComponent,
    LoginComponent,
    RegisterComponent,
    ContactComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [TradeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
