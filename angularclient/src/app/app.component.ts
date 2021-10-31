import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, UrlSegment } from '@angular/router';
import { User } from './models/User';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title: String;

  user: User;

  constructor(
    private route: ActivatedRoute
  ) {
    this.title = "Home Page";

    this.user = localStorage['user'];
  }
}
