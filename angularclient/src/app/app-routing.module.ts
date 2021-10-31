import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomepageComponent } from './homepage/homepage.component';
import { TradeComponent } from './trade/trade.component';
import { TradeHistoryComponent } from './tradeHistory/tradeHistory.component';
import { OrderBookComponent } from './orderBook/orderBook.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ContactComponent } from './contact-us/contact.component';
import { MarketComponent } from './market/market.component';
import { LogoutComponent } from './logout/logout.component';

const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'trades', component: TradeComponent },
  { path: 'trade/history', component: TradeHistoryComponent },
  { path: 'orders', component: OrderBookComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'market', component: MarketComponent },
  { path: 'contact-us', component: ContactComponent }
]

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
