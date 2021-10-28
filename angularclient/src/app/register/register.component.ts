import { Component, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { UserController } from '../controllers/user.controller';

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent {
  title: String;
  name: string;
  email: string;
  username: string;
  password: string;
  isUserCreated: boolean;
  errorMessage: string;

  constructor(@Inject(UserController) private userController: UserController,  private _router: Router) {
    this.title = "register Page";
    this.userController = userController;
  }

  async registerUser(){
    let user = await this.userController.registerUser(this.name, this.email, this.username, this.password);
    
    if(user && user.fullName){
      this.isUserCreated = true;
    }else{
      this.isUserCreated = false;
    }

  }
}