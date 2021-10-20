import { Component } from '@angular/core';

@Component({
  selector: 'homepage-root',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {
  title: String;

  constructor() {
    this.title = "Homepage";
  }
}
