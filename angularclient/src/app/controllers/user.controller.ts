import { Injectable } from "@angular/core";
import { User } from "../models/User";
import { UserService } from "../services/user.service";

@Injectable({
    providedIn: 'root',
  })
export class UserController{

    userService : UserService;

    constructor(userService : UserService){
        this.userService = userService;
    }

    public async authenticateUser(username: string, password: string ){
        var user = await this.userService.authenticateUser(username, password);
        if(user){
             console.log(`User authenticated: ${user.username}`);
             localStorage.setItem('user', JSON.stringify(user));
             return true;
        }else
            return false;
    }
    public async registerUser(name: string, email: string, username: string, password: string): Promise<User>{
        
        var user = await this.userService.registerUser(name, email, username, password);
        if(user){
             console.log(`User authenticated: ${user.username}`);
             localStorage.setItem('user', JSON.stringify(user));
        }

        return user;
    }
}