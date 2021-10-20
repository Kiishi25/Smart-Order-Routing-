import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomepageComponent } from './homepage/homepage.component';
import { TradeComponent } from './trade/trade.component';
import { TradeHistoryComponent } from './tradeHistory/tradeHistory.component';
import { OrderComponent } from './order/order.component';

const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'trades', component: TradeComponent },
  { path: 'trade/history', component: TradeHistoryComponent },
  { path: 'orders', component: OrderComponent }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
