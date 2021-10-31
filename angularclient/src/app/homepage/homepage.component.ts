import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, UrlSegment } from '@angular/router';
import { User } from '../models/User';

@Component({
  selector: 'homepage-root',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent{
  title: String;
  name: String;
  isUserAuthenticated: boolean;

  constructor(private route: ActivatedRoute) {
    this.title = "Homepage";
    let userStr = localStorage.getItem('user');
    let user = userStr ? JSON.parse(userStr) as User : undefined;
    this.name = user?.fullName;

    if(localStorage['user']){
      this.isUserAuthenticated = true;
    }

    console.log('Name: ' + this.name);
  }

}
