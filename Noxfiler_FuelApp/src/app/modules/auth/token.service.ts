import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private tokenKey: string ="";

  constructor(private route : Router) { }

  loginStatus():boolean {
    let token = localStorage.getItem(this.tokenKey);
    if (token == undefined || token == "") {
      return false;
    }
    return true;
  }

  storeToken(token: string) {
    this.tokenKey = token;
    localStorage.setItem("AUTH_TOKEN", token);
  }


}
