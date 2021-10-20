import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { HomepageComponent } from './homepage.component';


@NgModule({
  declarations: [
    HomepageComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [HomepageComponent]
})
export class HomepageModule { }
