import { Injectable } from "@angular/core";
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
             localStorage['user'] = user;
             return true;
        }else
            return false;
    }
}