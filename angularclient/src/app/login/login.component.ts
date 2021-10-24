import { Component, Inject } from '@angular/core';
import { UserController } from '../controllers/user.controller';
import { Router } from '@angular/router';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  title: String;
  username: string;
  password: string;

  constructor(@Inject(UserController) private userController: UserController,  private _router: Router) {
    this.title = "Login Page";
    this.userController = userController;
  }

  async authenticateUser(){
    let isAuthenticated = await this.userController.authenticateUser(this.username, this.password);

    if(isAuthenticated){
      this._router.navigateByUrl("/");
    }
  }
}
