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
  error: string;

  constructor(@Inject(UserController) private userController: UserController,  private _router: Router) {
    this.title = "Login Page";
    this.userController = userController;
  }

  async authenticateUser(){
    let isAuthenticated = await this.userController.authenticateUser(this.username, this.password);

    if(isAuthenticated){
      this._router.navigate(['/'])
      .then(() => {
        window.location.reload();
      });
    }else{
      this.error = "Failed to login. Please try again and ensure the password is correct.";
    }
  }
}
