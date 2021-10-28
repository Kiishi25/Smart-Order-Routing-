import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { User } from '../models/User';


@Injectable()
export class UserService {

  httpClient: HttpClient;

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
  }

  public async authenticateUser(username: string, password: string): Promise<User> {

    let user: User = null;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      })
    };

    var isAuthenticated = false;

    var url = 'http://localhost:8080/login';
    var body = {
      'username': username,
      'password': password
    };

    let response = await this.httpClient.post(url, body, httpOptions).toPromise();
    if (response && response['username'] === username) {
      console.log("performed login request");
      user = response as User;
      isAuthenticated = true;
    }

    return user;
  }

  public async registerUser(name: string, email: string, username: string, password: string){

    let user: User = null;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      })
    };

    var url = 'http://localhost:8080/register';
    var body = {
      'fullName': name,
      'email': email,
      'username': username,
      'password': password,
   
    };

    let response = await this.httpClient.post(url, body, httpOptions).toPromise();
    if (response && response['username'] === username) {
      console.log("performed registration request");
      user = response as User;
    }

    return user;
  }

}
