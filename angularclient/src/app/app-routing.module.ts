import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomepageComponent } from './homepage/homepage.component';
import { HistoryComponent } from './history/history.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ContactComponent } from './contact-us/contact.component';
import { MarketComponent } from './market/market.component';
import { LogoutComponent } from './logout/logout.component';
import { OrderComponent } from './orders/orders.component';

const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'history', component: HistoryComponent },
  { path: 'orders', component: OrderComponent },
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
